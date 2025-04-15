package com.nt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.ChildInputs;
import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.bindings.DcSummaryReport;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.entity.DcCaseEntity;
import com.nt.entity.DcChildEntity;
import com.nt.entity.DcEducationEntity;
import com.nt.entity.DcIncomeEntity;
import com.nt.entity.PlanEntity;
import com.nt.repository.ICaseRepository;
import com.nt.repository.IChildRepository;
import com.nt.repository.ICitizenRegistrationRepositoty;
import com.nt.repository.IEducationRepository;
import com.nt.repository.IIncomeRepository;
import com.nt.repository.IPlanEntityRepository;
import com.nt.utils.EmailUtils;

@Service
public class DcMgmtServiceClass implements IDcMgmtService{
	@Autowired
   private ICitizenRegistrationRepositoty citizenRepository;
	@Autowired
	private ICaseRepository caseRepository;
	@Autowired
	private IPlanEntityRepository planRepository;
	@Autowired
	private IIncomeRepository incomeRepository;
	@Autowired
	private IEducationRepository educationRepository;
	@Autowired
	private IChildRepository childRepository;
	@Autowired
	private EmailUtils emaiUtils;
	
	
	@Override
	public Integer generateCaseNo(Integer appId) {
	Optional<CitizenAppRegistrationEntity> optcitizenapp= citizenRepository.findById(appId);
	if(optcitizenapp.isPresent())
	{
		DcCaseEntity caseEntity=new DcCaseEntity();
		caseEntity.setAppId(appId);
		DcCaseEntity ec=caseRepository.save(caseEntity);
		    String subject="This is Your CaseNo ";
		    
		    String body ="Hai "+ optcitizenapp.get().getFullName()+",<br><br>"+
		    "This is Your CaseNo:"+ec.getCaseNo()+"<br><br>"+
		    		"Use for Insurance Registration Process <br><br>"+
		        "Thank You..";
		    try {
		    emaiUtils.sendEmailMessage(optcitizenapp.get().getEmail(), subject, body);
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
		        
		return ec.getCaseNo();
		
		//Save the Object
		
		
	}
	return 0;
	}//Generate CaseNo
	public List<PlanEntity> showAllData(){
		return planRepository.findAll();
	}
	

	@Override
	public List<String> showAllPlanNames() {
	     List<PlanEntity> planentity=planRepository.findAll();
	     List<String> planNames=planentity.stream().map(plan-> plan.getPlanName()).toList();
		return planNames;
	}//show plans
	@Override
	public Integer savePlanSelection(PlanSelectionInputs plan) {
        Optional<DcCaseEntity> optCaseEntity = caseRepository.findById(plan.getCaseNo());

        if (optCaseEntity.isPresent()) {
            DcCaseEntity caseEntity = optCaseEntity.get();
            caseEntity.setPlanId(plan.getPlanId());
            caseRepository.save(caseEntity);
            return caseEntity.getCaseNo();
        }

        return 0; // Case number not found
    }

	@Override
	public Integer saveIncome(IncomeInputs income) {
	    Optional<DcCaseEntity> caseOpt = caseRepository.findById(income.getCaseNo());

	    if (caseOpt.isEmpty()) {
	        throw new RuntimeException("Error: Case number not found!"); 
	    }

	    // Check if income details already exist for the given case number
	    Optional<DcIncomeEntity> existingIncome = incomeRepository.findByCaseNo(income.getCaseNo());

	    if (existingIncome.isPresent()) {
	        throw new RuntimeException("Error: Income details already exist!"); 
	    }

	    DcIncomeEntity incomeEntity = new DcIncomeEntity();
	    BeanUtils.copyProperties(income, incomeEntity);
	    incomeRepository.save(incomeEntity);
	    
	    return income.getCaseNo();
	}


	
	
	@Override
	public Integer saveEducation(EducationInputs education) {
	    if (education == null) {
	        throw new RuntimeException("Error: Education details cannot be empty!");
	    }

	    // Validate if case number exists
	    Optional<DcCaseEntity> caseOpt = caseRepository.findById(education.getCaseNo());
	    if (caseOpt.isEmpty()) {
	        throw new RuntimeException("Error: Case number not found!");
	    }

	    // Check if education details already exist for this case number
	    Optional<DcEducationEntity> existingEducation = educationRepository.findByCaseNo(education.getCaseNo());
	    if (existingEducation.isPresent()) {
	        throw new RuntimeException("Error: Education details already exist for this case number!");
	    }

	    DcEducationEntity educationEntity = new DcEducationEntity();
	    BeanUtils.copyProperties(education, educationEntity);
	    educationRepository.save(educationEntity);

	    return education.getCaseNo();
	}


	@Override
	public Integer saveChild(List<ChildInputs> childList) {
	    if (childList == null || childList.isEmpty()) {
	        throw new RuntimeException("Error: Child data cannot be empty!");
	    }

	    Integer caseNo = childList.get(0).getCaseNo();

	    // Check if the case number exists
	    Optional<DcCaseEntity> caseOpt = caseRepository.findById(caseNo);
	    if (caseOpt.isEmpty()) {
	        throw new RuntimeException("Error: Case number not found!");
	    }

	    for (ChildInputs childData : childList) {
	        // Check if Aadhaar number already exists for the case
	        Optional<DcChildEntity> existingChild = childRepository.findByCaseNoAndAdharNo(caseNo, childData.getAdharNo());

	        if (existingChild.isPresent()) {
	            throw new RuntimeException("Error: Aadhaar number already exists for this case!");
	        }

	        DcChildEntity childEntity = new DcChildEntity();
	        BeanUtils.copyProperties(childData, childEntity);
	        childRepository.save(childEntity);
	    }

	    return caseNo;
	}//save child

	@Override
	public DcSummaryReport citizenReport(Integer caseNo) {
	    if (caseNo == null) {
	        throw new RuntimeException("Error: Case number is required!");
	    }

	    // Retrieve case details
	    Optional<DcCaseEntity> optCase = caseRepository.findById(caseNo);
	    if (optCase.isEmpty()) {
	        throw new RuntimeException("Error: Case number not found!");
	    }

	    DcCaseEntity caseEntity = optCase.get();
	    Integer planId = caseEntity.getPlanId();
	    Integer appId = caseEntity.getAppId();

	    // Retrieve plan name
	    String planName = null;
	    String planDescription = null;
	    Optional<PlanEntity> planEntity = planRepository.findById(planId);
	    if (planEntity.isPresent()) {
	        planName = planEntity.get().getPlanName();
	        planDescription = planEntity.get().getPlanDescription();
	    }

	    // Retrieve citizen details
	    Optional<CitizenAppRegistrationEntity> citizenOpt = citizenRepository.findById(appId);
	    CitizenAppRegistrationInputs citizenInputs = new CitizenAppRegistrationInputs();
	    if (citizenOpt.isPresent()) {
	        BeanUtils.copyProperties(citizenOpt.get(), citizenInputs);
	    }

	    // Retrieve income details
	    Optional<DcIncomeEntity> incomeOpt = incomeRepository.findByCaseNo(caseNo);
	    IncomeInputs incomeInputs = new IncomeInputs();
	    incomeOpt.ifPresent(income -> BeanUtils.copyProperties(income, incomeInputs));

	    // Retrieve education details
	    Optional<DcEducationEntity> educationOpt = educationRepository.findByCaseNo(caseNo);
	    EducationInputs eduInputs = new EducationInputs();
	    educationOpt.ifPresent(education -> BeanUtils.copyProperties(education, eduInputs));

	    // Retrieve child details
	    List<DcChildEntity> childEntities = childRepository.findByCaseNo(caseNo);
	    List<ChildInputs> childData = new ArrayList<>();
	    for (DcChildEntity child : childEntities) {
	        ChildInputs childInput = new ChildInputs();
	        BeanUtils.copyProperties(child, childInput);
	        childData.add(childInput);
	    }

	    // Prepare report
	    DcSummaryReport report = new DcSummaryReport();
	    report.setPlanName(planName);
	    report.setPlanDescription(planDescription);
	    report.setCitizeninputs(citizenInputs);
	    report.setIncomeinputs(incomeInputs);
	    report.setEduinputs(eduInputs);
	    report.setChilddata(childData);
	    
	    return report;
	}

}
