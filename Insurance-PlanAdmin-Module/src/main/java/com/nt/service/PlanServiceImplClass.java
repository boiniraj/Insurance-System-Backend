package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.PlanDataInputs;
import com.nt.entity.PlanEntity;
import com.nt.repository.IPlanEntityRepository;
@Service
public class PlanServiceImplClass implements IPlanService {
	@Autowired
	private IPlanEntityRepository planRepo;

	@Override
	public String registerPlan(PlanDataInputs planInputs) {
		        PlanEntity planEntity=new PlanEntity();
		        BeanUtils.copyProperties(planInputs, planEntity);
		        PlanEntity saveplan=planRepo.save(planEntity);                  ;
		return " Plan Save with plan Id::"+ saveplan.getPlanId();
	}//Save plan

	@Override
	public PlanEntity updatePlan(PlanDataInputs planInput) {
		Optional<PlanEntity>optPlan=planRepo.findById(planInput.getPlanId());
		
		PlanEntity plan=null;
		if(optPlan.isPresent())
		{
			PlanEntity planentity=optPlan.get();
			BeanUtils.copyProperties(planInput, planentity);
		        plan= planRepo.save(planentity);	
		}
		return plan;
	}

	@Override
	public String deletePlan(Integer planId) {
		   planRepo.deleteById(planId);
		return "This Plan succesfully deleted"+planId;
	}

	@Override
	public List<String> showAllPlans() 
	{ 
	List<PlanEntity> planentity=planRepo.findAll();
    List<String> planNames=planentity.stream().map(plan-> plan.getPlanName()).toList();
	return planNames;
	}

	@Override
	public PlanEntity showplanById(Integer planId) {
		Optional<PlanEntity> optplan=planRepo.findById(planId);
		PlanEntity planentity=null;
	if(optplan.isPresent())
	{
           planentity=optplan.get();
		
	}
	return planentity;
	}
	@Override
	public String changePlanStatus(Integer planId, String activeSw) {
		       Optional<PlanEntity> optStatus=planRepo.findById(planId);
		      
		       if(optStatus.isPresent())
		       {
		    	   PlanEntity plan=optStatus.get();
		    	   plan.setActiveSw(activeSw);
		    	   planRepo.save(plan);
		       }
		return "planStatus changed succus fully";
	}

	@Override
	public List< PlanEntity> showAll() {
	   List< PlanEntity> plan= planRepo.findAll();
		return plan;
	}

}
