package com.nt.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    private IElgibilityDeterminationRepository elgibilityRepo;

    @Autowired
    private ICoTriggersRepository coTriggerRepo;

    @Override
    public ElgibilityDetailsOutput elgibilityCtizen(Integer caseNo) {
    	
        DcCaseEntity caseEntity = caseRepo.findById(caseNo)
                .orElseThrow(() -> new IllegalArgumentException("Case not found for caseNo: " + caseNo));

        PlanEntity planEntity = planRepo.findById(caseEntity.getPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Plan not found for planId: " + caseEntity.getPlanId()));

        CitizenAppRegistrationEntity citizenEntity = citizenRepo.findById(caseEntity.getAppId())
                .orElseThrow(() -> new IllegalArgumentException("Citizen not found for appId: " + caseEntity.getAppId()));

        int citizenAge = Period.between(citizenEntity.getDob(), LocalDate.now()).getYears();

        ElgibilityDetailsOutput elgibilityOutput = applyPlanConditions(
                planEntity.getPlanName(), caseNo, citizenAge,
                citizenEntity.getBankName(), citizenEntity.getAccountNo()
        );

        elgibilityOutput.setHolderName(citizenEntity.getFullName());

        // ✅ Check for existing eligibility record
        Optional<ElgibilityDetailsEntity> existingRecordOpt = elgibilityRepo.findByCaseNo(caseNo);
        ElgibilityDetailsEntity elgEntity;
        
        if (existingRecordOpt.isPresent()) {
            // Update existing record
            elgEntity = existingRecordOpt.get();
            BeanUtils.copyProperties(elgibilityOutput, elgEntity);
        } else {
            // Create new record
            elgEntity = new ElgibilityDetailsEntity();
            BeanUtils.copyProperties(elgibilityOutput, elgEntity);
            elgEntity.setHolderAdharNo(citizenEntity.getAdharNo());
        }
        elgEntity.setCaseNo(caseNo); // ensure caseNo is set/updated
        elgibilityRepo.save(elgEntity);

        // ✅ Similarly avoid duplicate triggers
        if (!coTriggerRepo.existsByCaseNo(caseNo)) {
            CoTriggersEntity coTrigger = new CoTriggersEntity();
            coTrigger.setCaseNo(caseNo);
            coTrigger.setTriggerStatus("Pending");
            coTriggerRepo.save(coTrigger);
        }

        return elgibilityOutput;
    }

    private ElgibilityDetailsOutput applyPlanConditions(String planName, Integer caseNo, Integer citizenAge, String bankName, Long accNo) {
        ElgibilityDetailsOutput output = new ElgibilityDetailsOutput();
        output.setPlanName(planName);
        output.setCaseNo(caseNo);
        output.setBankName(bankName);
        output.setAccNo(accNo);

        Optional<DcIncomeEntity> incomeOpt = incomeRepo.findByCaseNo(caseNo);
        double holderIncome = incomeOpt.map(DcIncomeEntity::getEmpIncome).orElse(0.0);
        double holderPropIncome = incomeOpt.map(DcIncomeEntity::getPropertyIncome).orElse(0.0);

        switch (planName.toUpperCase()) {
            case "SNAP (SUPPLEMENTAL NUTRITION ASSISTANCE PROGRAM)":
                if (holderIncome <= 100000.0) approvePlan(output, 9000.0);
                else denyPlan(output, "Citizen income is higher");
                break;

            case "CCAP (CHILD CARE ASSISTANCE PROGRAM)":
                List<DcChildEntity> childEntities = childRepo.findByCaseNo(caseNo);
                boolean kidsCount = !childEntities.isEmpty();
                boolean kidsAge = childEntities.stream().allMatch(c -> Period.between(c.getDob(), LocalDate.now()).getYears() <= 16);
                if (holderIncome <= 100000.0 && kidsCount && kidsAge) approvePlan(output, 10000.0);
                else denyPlan(output, "CCAP rules not satisfied");
                break;

            case "MEDICAID":
                if (holderIncome <= 100000.0 && holderPropIncome == 0.0) approvePlan(output, 8000.0);
                else denyPlan(output, "MEDAID rules not satisfied");
                break;

            case "MEDICARE":
                if (citizenAge >= 65) approvePlan(output, 9000.0);
                else denyPlan(output, "MEDCARE rules not satisfied");
                break;

            case "CAJW (CITIZEN ASSISTANCE FOR JOBLESS YOUTH)":
                Optional<DcEducationEntity> eduOpt = educationRepo.findByCaseNo(caseNo);
                if (eduOpt.isPresent() && holderIncome == 0 && eduOpt.get().getPassOutYear() < LocalDate.now().getYear()) {
                    approvePlan(output, 10000.0);
                } else {
                    denyPlan(output, "CAJW rules not satisfied");
                }
                break;

            case "QHP (QUALIFIED HEALTH PLAN)":
                if (holderIncome > 100000.0) approvePlan(output, 12000.0);
                else denyPlan(output, "QHP rules not satisfied");
                break;

            default:
                denyPlan(output, "Invalid plan name");
        }
        return output;
    }

    private void approvePlan(ElgibilityDetailsOutput output, double amount) {
        output.setPlanStatus("Approved");
        output.setBeneficiaryAmt(amount);
        output.setStartDate(LocalDate.now());
        output.setEndDate(LocalDate.now().plusYears(15));
    }

    private void denyPlan(ElgibilityDetailsOutput output, String reason) {
        output.setPlanStatus("Denied");
        output.setDenialReason(reason);
    }
}
