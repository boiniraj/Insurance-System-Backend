package com.nt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DcChildEntity;

public interface IChildRepository extends JpaRepository<DcChildEntity,Integer> {
	public List< DcChildEntity> findByCaseNo(int caseNo);
	Optional<DcChildEntity>findByCaseNoAndAdharNo(Integer caseNo, Long adharNo);
}
