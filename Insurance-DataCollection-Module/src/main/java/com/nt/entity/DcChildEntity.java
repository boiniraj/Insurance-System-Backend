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
@Table(name="CHILD_Entity")
public class DcChildEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer childId;
	@Column
	private  Integer caseNo;
	@Column
	private LocalDate dob;
	@Column
	private Long adharNo;
}
