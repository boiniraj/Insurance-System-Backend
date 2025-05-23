package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.ElgibilityDetailsEntity;

public interface IElgibilityDeterminationRepository extends JpaRepository<ElgibilityDetailsEntity, Integer> {
	 Optional<ElgibilityDetailsEntity> findByCaseNo(Integer caseNo);

}
