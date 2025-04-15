package com.nt.service;

import java.util.List;

import com.nt.bindings.PlanDataInputs;
import com.nt.entity.PlanEntity;

public interface IPlanService {
	public String registerPlan(PlanDataInputs planInputs);
	public PlanEntity updatePlan(PlanDataInputs planInput);
	public String deletePlan(Integer planId);
	public List<String> showAllPlans();
	public PlanEntity showplanById(Integer planId);
	public String changePlanStatus(Integer planId,String activeSw);
	public  List<PlanEntity> showAll();
}
