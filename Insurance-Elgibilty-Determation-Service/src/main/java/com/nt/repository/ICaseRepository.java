package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DcCaseEntity;

public interface ICaseRepository extends JpaRepository<DcCaseEntity, Integer> {
	

}
