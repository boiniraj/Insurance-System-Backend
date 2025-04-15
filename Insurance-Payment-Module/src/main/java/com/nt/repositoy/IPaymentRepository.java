package com.nt.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.PaymentsEntity;

public interface IPaymentRepository extends JpaRepository<PaymentsEntity, String> {
	Optional<PaymentsEntity>findByCaseNo(Integer caseNo);

}
