package com.nt.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.ChildInputs;
import com.nt.bindings.DcSummaryReport;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;
import com.nt.entity.PlanEntity;
import com.nt.services.IDcMgmtService;

@RestController
@RequestMapping("/DataCollection-api")
@CrossOrigin
public class DataCollectionController {
    
    @Autowired
    private IDcMgmtService service;
    
    @PostMapping("/generateCaseNo/{appId}")
    public ResponseEntity<?> saveAppidCase(@PathVariable Integer appId) {
        Integer caseNo = service.generateCaseNo(appId);

        if (caseNo == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Invalid Application Id: " + appId);
        }

        return ResponseEntity.ok(caseNo);
    }
    
    @GetMapping("/plans-data")
    public ResponseEntity<List<PlanEntity>> showData()
    {
    	List <PlanEntity> listPlanData=service.showAllData();
    	return new ResponseEntity<List <PlanEntity>>(listPlanData,HttpStatus.OK);
    }
    
    @GetMapping("/showplanNames")
    public ResponseEntity<List<String>> showPlanNames() {
        List<String> plans = service.showAllPlanNames();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }
    
    
    @PostMapping("/savePlan")
    public ResponseEntity<Integer> savePlan(@RequestBody PlanSelectionInputs plan) {
        Integer caseNo = service.savePlanSelection(plan);
        if (caseNo > 0) {
            return ResponseEntity.ok(caseNo);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
        }
    }
    
    @PostMapping("/saveIncome")
    public ResponseEntity<Integer> saveIncome(@RequestBody IncomeInputs income) {
        Integer caseNo = service.saveIncome(income);
        return new ResponseEntity<>(caseNo, HttpStatus.CREATED);
    }
    
    @PostMapping("/saveEducation")
    public ResponseEntity<Integer> saveEducation(@RequestBody EducationInputs education) {
        Integer caseNo = service.saveEducation(education);  // Corrected method name
        return new ResponseEntity<>(caseNo, HttpStatus.CREATED);
    }
    
    @PostMapping("/saveChild")
    public ResponseEntity<?> saveChild(@RequestBody List<ChildInputs> child) {
        try {
            Integer caseNo = service.saveChild(child);
            return new ResponseEntity<>(caseNo, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();  // This will show the exact issue
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping("/citizenReport/{caseNo}")
    public ResponseEntity<DcSummaryReport> showAllData(@PathVariable Integer caseNo)
    { 
    	DcSummaryReport report=service.citizenReport(caseNo);
    	return new ResponseEntity<DcSummaryReport>(report,HttpStatus.OK);
    }
}
