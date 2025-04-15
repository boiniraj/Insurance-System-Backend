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
@Table(name = "INCOME_Entity")
public class DcIncomeEntity {

    @Id
    private Integer incomeId;

    @Column(name = "CASE_NO")
    private Integer caseNo;

    @Column(name = "EMP_INCOME")
    private Double empIncome;

    @Column(name = "PROPERTY_INCOME")
    private Double propertyIncome;  // Changed to camel case
}
