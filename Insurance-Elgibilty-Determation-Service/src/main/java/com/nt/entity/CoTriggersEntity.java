package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="CO_TRIGGERS_ENTITY")
public class CoTriggersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer coTriggerId;
	@Column
	private Integer caseNo;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] coNoticePdf;
	@Column
	private String triggerStatus;

}
