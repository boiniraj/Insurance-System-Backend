package com.nt.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CitizenAppRegistrationEntity;

public interface ICitizenRegistrationRepositoty extends JpaRepository<CitizenAppRegistrationEntity, Integer>{

}
