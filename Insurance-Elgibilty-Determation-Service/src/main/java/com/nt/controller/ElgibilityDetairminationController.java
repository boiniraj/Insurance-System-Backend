package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.ElgibilityDetailsOutput;
import com.nt.service.IElgibilityService;
@RestController
@RequestMapping("/elgibility-api")
@CrossOrigin
public class ElgibilityDetairminationController{
	@Autowired
	private IElgibilityService service;
	
	@PostMapping("/citizenElgibility/{caseNo}")
	public ResponseEntity<ElgibilityDetailsOutput> citizenelgibilitycheck(@PathVariable Integer caseNo)
	{
		ElgibilityDetailsOutput elgibility= service.elgibilityCtizen(caseNo);
		return new ResponseEntity<ElgibilityDetailsOutput>(elgibility,HttpStatus.OK);
	}

}
