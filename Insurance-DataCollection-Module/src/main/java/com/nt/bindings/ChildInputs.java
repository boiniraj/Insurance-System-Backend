package com.nt.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChildInputs {
	private Long adharNo;
	private LocalDate dob;
	private Integer caseNo;
}
