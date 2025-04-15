package com.nt.bindings;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PlanDataInputs {
	private Integer planId;
	private String planName;
	private String activeSw;
	private String planDescription;
	private LocalDate startDate;
	private LocalDate endDate;
	
}
