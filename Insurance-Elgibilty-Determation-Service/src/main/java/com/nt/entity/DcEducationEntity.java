package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="EDUCATION_Entity")
public class DcEducationEntity {
	@Id
	private Integer educationId;
	@Column
	private Integer caseNo;
	@Column
	private String highestQualification;
	@Column
	private Integer PassOutYear;
}
