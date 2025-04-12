package com.nt.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenAppRegistrationInputs 
{
	private String fullName;
	private String email;
	private String gender;
	private Long phoneNo;
	private Long adharNo;
	private String stateName;
	private Long accountNo;
	private String bankName;
	private LocalDate dob;
}
