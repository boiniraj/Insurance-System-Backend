package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nt.entity.DcIncomeEntity;

public interface IIncomeRepository extends JpaRepository<DcIncomeEntity, Integer> {
   //public DcIncomeEntity findByCaseNo(Integer caseNo);
   public Optional<DcIncomeEntity>  findByCaseNo(Integer caseNo);// Method name matches the entity field
}
