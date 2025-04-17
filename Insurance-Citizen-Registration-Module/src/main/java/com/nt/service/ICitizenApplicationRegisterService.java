package com.nt.service;

import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.entity.CitizenAppRegistrationEntity;

public interface ICitizenApplicationRegisterService
{
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs);
	public CitizenAppRegistrationEntity findbyid(Integer appId);
	public String deleterUser(Integer id);
}
