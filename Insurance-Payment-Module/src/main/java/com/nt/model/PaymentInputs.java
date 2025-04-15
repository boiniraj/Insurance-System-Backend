package com.nt.model;

import lombok.Data;

@Data
public class PaymentInputs {
	
	private Integer caseNo;
	private Long accNo;
	private String bankName;
	private Double beneficiaryAmt;
	
	

}
