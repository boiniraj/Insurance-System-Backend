package com.nt.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.ElgibilityDetailsOutput;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.entity.CoTriggersEntity;
import com.nt.entity.DcCaseEntity;
import com.nt.entity.DcChildEntity;
import com.nt.entity.DcEducationEntity;
import com.nt.entity.DcIncomeEntity;
import com.nt.entity.ElgibilityDetailsEntity;
import com.nt.entity.PlanEntity;
import com.nt.repository.ICaseRepository;
import com.nt.repository.IChildRepository;
import com.nt.repository.ICitizenRegistrationRepositoty;
import com.nt.repository.ICoTriggersRepository;
import com.nt.repository.IEducationRepository;
import com.nt.repository.IElgibilityDeterminationRepository;
import com.nt.repository.IIncomeRepository;
import com.nt.repository.IPlanEntityRepository;
@Service
public class ElgibilityImplClass implements IElgibilityService {
	@Autowired
	private IPlanEntityRepository planRepo;
	@Autowired
	private ICaseRepository caseRepo; 
	@Autowired
	private ICitizenRegistrationRepositoty citizenRepo;
	@Autowired
	private IIncomeRepository incomeRepo;
	@Autowired
	private IChildRepository childRepo;
	@Autowired
	private IEducationRepository educationRepo;
	@Autowired
	private  IElgibilityDeterminationRepository elgibilityRepo;
	@Autowired
	private ICoTriggersRepository coTriggerRepo;
	
	
	@Override
	public ElgibilityDetailsOutput elgibilityCtizen(Integer caseNo) {
	    Optional<DcCaseEntity> optCase = caseRepo.findById(caseNo);
	    if (!optCase.isPresent()) {
	        throw new IllegalArgumentException("Case not found for caseNo: " + caseNo);
	    }
	    DcCaseEntity caseEntity = optCase.get();
	    Integer planId = caseEntity.getPlanId();
	    Integer appId = caseEntity.getAppId();

	    Optional<PlanEntity> optPlan = planRepo.findById(planId);
	    if (!optPlan.isPresent()) {
	        throw new IllegalArgumentException("Plan not found for planId: " + planId);
	    }
	    String planName = optPlan.get().getPlanName();

	    Optional<CitizenAppRegistrationEntity> optCitizen = citizenRepo.findById(appId);
	    if (!optCitizen.isPresent()) {
	        throw new IllegalArgumentException("Citizen not found for appId: " + appId);
	    }
	    CitizenAppRegistrationEntity citizenEntity = optCitizen.get();
	    String citizenFullName = citizenEntity.getFullName();
	    long holderAdharNo = citizenEntity.getAdharNo();
	    long accNo = citizenEntity.getAccountNo();
	    String bankName = citizenEntity.getBankName();
	    int citizenAge = Period.between(citizenEntity.getDob(), LocalDate.now()).getYears();

	    ElgibilityDetailsOutput elgibilityOutput = applyPlanConditions(planName, caseNo, citizenAge, bankName, accNo);
	    elgibilityOutput.setHolderName(citizenFullName);

	    ElgibilityDetailsEntity elgEntity = new ElgibilityDetailsEntity();
	    BeanUtils.copyProperties(elgibilityOutput, elgEntity);
	    elgEntity.setHolderAdharNo(holderAdharNo);
	    elgEntity.setCaseNo(caseNo);
	    elgEntity.setAccNo(accNo);
	    elgEntity.setBankName(bankName);
	    elgibilityRepo.save(elgEntity);

	    CoTriggersEntity coTrigger = new CoTriggersEntity();
	    coTrigger.setCaseNo(caseNo);
	    coTrigger.setTriggerStatus("Pending");
	    coTriggerRepo.save(coTrigger);

	    return elgibilityOutput;
	}

	private ElgibilityDetailsOutput applyPlanConditions(String planName, Integer caseNo, Integer citizenAge, String bankName, Long accNo) {
	    ElgibilityDetailsOutput elgibilityOutput = new ElgibilityDetailsOutput();
	    elgibilityOutput.setPlanName(planName);

	    Optional<DcIncomeEntity> incomeEntityOpt = incomeRepo.findByCaseNo(caseNo);
	    double holderIncome = 0, holderPropIncome = 0;
	    if (incomeEntityOpt.isPresent()) {
	        DcIncomeEntity incomeEntity = incomeEntityOpt.get();
	        holderIncome = incomeEntity.getEmpIncome();
	        holderPropIncome = incomeEntity.getPropertyIncome();
	    }

	    switch (planName.toUpperCase()) {
	        case "SNAP":
	            if (holderIncome <= 100000.0) {
	                approvePlan(elgibilityOutput, 9000.0, bankName, accNo,caseNo);
	            } else {
	                denyPlan(elgibilityOutput, "Citizen income is higher");
	            }
	            break;

	        case "CCAP":
	            boolean kidsCountCondition = false;
	            boolean kidsAgeCondition = true;
	            List<DcChildEntity> childEntities = childRepo.findByCaseNo(caseNo);
	            if (!childEntities.isEmpty()) {
	                kidsCountCondition = true;
	                for (DcChildEntity child : childEntities) {
	                    if (Period.between(child.getDob(), LocalDate.now()).getYears() > 16) {
	                        kidsAgeCondition = false;
	                        break;
	                    }
	                }
	            }
	            if (holderIncome <= 100000.0 && kidsCountCondition && kidsAgeCondition) {
	                approvePlan(elgibilityOutput, 10000.0, bankName, accNo,caseNo);
	            } else {
	                denyPlan(elgibilityOutput, "CCAP rules not satisfied");
	            }
	            break;

	        case "MEDAID":
	            if (holderIncome <= 100000.0 && holderPropIncome == 0.0) {
	                approvePlan(elgibilityOutput, 8000.0, bankName, accNo,caseNo);
	            } else {
	                denyPlan(elgibilityOutput, "MEDAID rules not satisfied");
	            }
	            break;

	        case "MEDCARE":
	            if (citizenAge >= 65) {
	                approvePlan(elgibilityOutput, 9000.0, bankName, accNo,caseNo);
	            } else {
	                denyPlan(elgibilityOutput, "MEDCARE rules not satisfied");
	            }
	            break;

	        case "CAJW":
	            Optional<DcEducationEntity> eduEntityOpt = educationRepo.findByCaseNo(caseNo);
	            if (eduEntityOpt.isPresent()) {
	                int passoutYear = eduEntityOpt.get().getPassOutYear();
	                if (holderIncome == 0 && passoutYear < LocalDate.now().getYear()) {
	                    approvePlan(elgibilityOutput, 10000.0, bankName, accNo,caseNo);
	                } else {
	                    denyPlan(elgibilityOutput, "CAJW rules not satisfied");
	                }
	            } else {
	                denyPlan(elgibilityOutput, "Education details not found");
	            }
	            break;

	        default:
	            denyPlan(elgibilityOutput, "Invalid plan name");
	            break;
	    }
	    return elgibilityOutput;
	}

	private void approvePlan(ElgibilityDetailsOutput output, double amount, String bankName, Long accNo, Integer casNo) {
	    output.setPlanStatus("Approved");
	    output.setBeneficiaryAmt(amount);
	    output.setStartDate(LocalDate.now());
	    output.setEndDate(LocalDate.now().plusYears(15));
	    output.setBankName(bankName);
	    output.setAccNo(accNo);
	    output.setCaseNo(casNo);
	    
	}
	private void denyPlan(ElgibilityDetailsOutput output, String reason) {
	    output.setPlanStatus("Denied");
	    output.setDenialReason(reason);
	}
}
	