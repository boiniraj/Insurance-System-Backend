package com.nt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.service.CitizenAppRegisterImplclass;

@RestController
@RequestMapping("/citizen-api")
public class CitizenController 
{
	@Autowired
	private CitizenAppRegisterImplclass service;
	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> registerCitizen(@RequestBody CitizenAppRegistrationInputs inputs) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        int appId = service.registerCitizenApplication(inputs);

	        if (appId == -1) {
	            response.put("error", "Email already registered");
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }

	        response.put("message", "Citizen registered successfully");
	        response.put("appId", appId);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);

	    } catch (Exception e) {
	        response.put("error", e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}//save
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteCitizen(@PathVariable Integer id)
	{ 
		try {
	           service.deleterUser(id);
			 return new ResponseEntity<String>("Citizen Deleted with Id"+id,HttpStatus.CREATED); 
		}//end try
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}//end catch
		
	}

}
