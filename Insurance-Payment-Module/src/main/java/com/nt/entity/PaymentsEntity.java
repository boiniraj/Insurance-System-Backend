package com.nt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Payment_Entity")
public class PaymentsEntity {

    @Id
    private String transactionId;
    @Column(unique=true)
    private Integer caseNo;
    private Long accNo;
    private String bankName;
    private Double beneficiaryAmt;
    private LocalDate date;
    private LocalTime time;

    @PrePersist
    public void generateTransactionId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateTime = LocalDateTime.now().format(formatter);
        this.transactionId = "TXN" + dateTime;  // Added hyphen for better readability

        // Ensure date and time are set at the moment of persistence
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }
}
