package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nt.entity.PlanEntity;

public interface IPlanEntityRepository extends JpaRepository<PlanEntity, Integer> {
}
