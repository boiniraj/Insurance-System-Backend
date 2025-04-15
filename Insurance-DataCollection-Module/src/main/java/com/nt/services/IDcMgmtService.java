package com.nt.services;

import java.util.List;

import com.nt.bindings.ChildInputs;
import com.nt.bindings.DcSummaryReport;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;
import com.nt.entity.PlanEntity;

public interface IDcMgmtService {
	public Integer generateCaseNo(Integer appId);
	public List<PlanEntity> showAllData();
	public List<String> showAllPlanNames();
	public Integer savePlanSelection(PlanSelectionInputs plan);
	public Integer saveIncome(IncomeInputs income );
	public Integer saveEducation(EducationInputs education);
	public Integer saveChild(List<ChildInputs> child);
	public DcSummaryReport citizenReport(Integer caseNo);
}
