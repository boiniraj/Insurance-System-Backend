package com.nt.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ELIGIBILITY_DETAIRMATIONS")
public class ElgibilityDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer edTrackId;
	@Column
	private Integer caseNo;
	@Column(length=30)
	private String  holderName;
	private Long holderAdharNo;
	@Column(length=30)
	private String planName;
	@Column(length=30)
	private String planStatus;
	@Column
	private LocalDate startDate;
	@Column
	private LocalDate endDate;
	@Column
	private Double beneficiaryAmt;
	@Column(length=20)
	private String bankName;
	@Column
	private Long accNo;
	@Column()
	private String denialReason;

}
