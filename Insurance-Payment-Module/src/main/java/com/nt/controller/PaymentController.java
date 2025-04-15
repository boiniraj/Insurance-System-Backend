package com.nt.controller;

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

import com.nt.entity.PaymentsEntity;
import com.nt.model.PaymentInputs;
import com.nt.service.PaymentService;
@RestController
@RequestMapping("/Payments-api")
@CrossOrigin
public class PaymentController {
	@Autowired
	private PaymentService service;
	@PostMapping("/savePayments")
	public ResponseEntity<PaymentsEntity> registerPayment(@RequestBody PaymentInputs inputs)
	{
		      PaymentsEntity pt=service.registerPayment(inputs);
		return new ResponseEntity<PaymentsEntity>(pt,HttpStatus.CREATED);
	}
	@GetMapping("/showdata/{transactionId}")
	public ResponseEntity<PaymentsEntity> showAllData(@PathVariable String  transactionId)
	{
		PaymentsEntity ent3=service.showDetals(transactionId);
		return new ResponseEntity<PaymentsEntity>(ent3,HttpStatus.OK);
	}

}
