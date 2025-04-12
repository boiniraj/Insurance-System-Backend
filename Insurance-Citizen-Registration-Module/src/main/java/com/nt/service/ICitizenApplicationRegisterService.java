package com.nt.service;

import com.nt.bindings.CitizenAppRegistrationInputs;

public interface ICitizenApplicationRegisterService
{
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs);
	public String deleterUser(Integer id);
}
