package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DcEducationEntity;


public interface IEducationRepository extends JpaRepository<DcEducationEntity,Integer> {
	//public DcEducationEntity findByCaseNo(int caseNo);
	Optional<DcEducationEntity>findByCaseNo(int caseNo);
}
