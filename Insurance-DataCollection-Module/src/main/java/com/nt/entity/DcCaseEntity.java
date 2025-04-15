package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="CASE_ENTITY")
public class DcCaseEntity {

	@Id
	@SequenceGenerator(name="gen1_seq",sequenceName="app_seq1",initialValue=5001,allocationSize=1)
	@GeneratedValue(generator="gen1_seq",strategy=GenerationType.SEQUENCE)
	private Integer caseNo;
	@Column
	private Integer appId;
	@Column
	private Integer PlanId;

}
