package com.nt.bindings;

import java.util.List;

import lombok.Data;

@Data
public class DcSummaryReport
{
	private CitizenAppRegistrationInputs citizeninputs;
	private IncomeInputs incomeinputs;
	private List<ChildInputs >childdata;
	private EducationInputs eduinputs;
	private String planName;
	private String planDescription;
}
