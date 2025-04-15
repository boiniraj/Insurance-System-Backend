package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.PlanDataInputs;
import com.nt.entity.PlanEntity;
import com.nt.service.IPlanService;

@RestController
@RequestMapping("/PlanAdmin-Api")
public class PlanEntityController {
	@Autowired
	private IPlanService service;
	
	@PostMapping("/savePlan")
	public ResponseEntity<String> savePlan(@RequestBody PlanDataInputs planInputs)
	{
          String plan=service.registerPlan(planInputs);
		return new ResponseEntity<String>(plan,HttpStatus.CREATED);
	}
	
	@PutMapping("/updatePlan")
	public ResponseEntity<PlanEntity> updatePlan(@RequestBody PlanDataInputs inputs)
	{
		PlanEntity planen=service.updatePlan(inputs);
		return new ResponseEntity<PlanEntity>(planen,HttpStatus.OK);
	}
	@PostMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deleteByPlan(@PathVariable Integer planId)
	{
		 String planid=service.deletePlan(planId);
		return new ResponseEntity<String>(planid,HttpStatus.OK);
	}
	@GetMapping("/showPlanes")
	public ResponseEntity<List<String>> showAll()
	{
		List<String> planes=service.showAllPlans();
		return new ResponseEntity<List<String>>(planes,HttpStatus.OK);
	}
	@GetMapping("/showById/{planId}")
	public ResponseEntity<PlanEntity> showById(@PathVariable Integer planId)
	{
		PlanEntity entity=service.showplanById(planId);
		return new ResponseEntity<PlanEntity>(entity,HttpStatus.OK);
	}
	@PostMapping("/changePlanStatus{planId}")
	public ResponseEntity<String> changePlanStatus(@PathVariable Integer planId,String activeSw)
	{
		String msg=service.changePlanStatus(planId, activeSw);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@GetMapping("/ShowAllPlanData")
	public ResponseEntity<List<PlanEntity>> showAllPlanes()
	{
		 List<PlanEntity> plan=service.showAll();
		return new ResponseEntity<List<PlanEntity>>(plan,HttpStatus.OK);
	}

}
