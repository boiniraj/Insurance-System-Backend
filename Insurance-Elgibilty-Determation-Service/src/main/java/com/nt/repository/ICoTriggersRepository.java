package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CoTriggersEntity;

public interface ICoTriggersRepository extends JpaRepository<CoTriggersEntity, Integer> {
	public CoTriggersEntity findByCaseNo(Integer caseNo);

}
