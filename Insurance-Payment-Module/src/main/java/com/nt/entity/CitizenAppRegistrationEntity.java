package com.nt.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="CITIZEN_REGISTRATIONS")
public class CitizenAppRegistrationEntity
{
	@Id
	@SequenceGenerator(name="gen1_seq",sequenceName="app_seq1",initialValue=10001,allocationSize=1)
	@GeneratedValue(generator="gen1_seq",strategy=GenerationType.SEQUENCE)
	private Integer appId;
	@Column(length=30)
	private String fullName;
	@Column(length=45)
	private String email;
	@Column(length=10)
	private String gender;
	private Long phoneNo;
	private Long adharNo;
	@Column(length=30)
	private String stateName;
	@Column
	private Long accountNo;
	@Column(length=20)
	private String bankName;
	private LocalDate dob;
}
