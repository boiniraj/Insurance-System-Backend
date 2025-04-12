package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CitizenAppRegistrationEntity;

public interface CitizenRegistrationRepositoty extends JpaRepository<CitizenAppRegistrationEntity, Integer>{
	 Optional<CitizenAppRegistrationEntity> findByEmail(String email);

}
