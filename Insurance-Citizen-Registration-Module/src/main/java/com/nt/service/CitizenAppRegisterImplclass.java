package com.nt.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.repository.CitizenRegistrationRepositoty;
import com.nt.utils.EmailUtils;
@Service
public class CitizenAppRegisterImplclass implements ICitizenApplicationRegisterService {
 @Autowired
	private CitizenRegistrationRepositoty repository;
   @Autowired
    private EmailUtils emailUtils;
 
   
   public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) {
	    // Check if email already exists
	    Optional<CitizenAppRegistrationEntity> isent = repository.findByEmail(inputs.getEmail());

	    if (isent.isPresent()) {
	        return -1; // Email already exists
	    }

	    // Create new CitizenAppRegistrationEntity and copy properties
	    CitizenAppRegistrationEntity master = new CitizenAppRegistrationEntity();
	    BeanUtils.copyProperties(inputs, master);

	    // Save entity and get the generated application ID
	    int appId = repository.save(master).getAppId();

	    // Prepare email content
	    String subject = "Application ID Confirmation";
	    String body = "Hi " + master.getFullName() + ",<br><br>" +
	                  "This is your Application ID. Please use this for the Case Generation Process: <b>" + appId + "</b>.<br><br>" +
	                  "Thank you!";

	    // Send email
	    try {
	        emailUtils.sendEmailMessage(master.getEmail(), subject, body);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return appId;
	}

	    @Override
	public String deleterUser(Integer id) {
		Optional<CitizenAppRegistrationEntity> master =repository.findById(id);
		if(master.isPresent())
		{
		       repository.deleteById(id);
		       return +id+ ":delete succussfully" ;
		}
		return "delete not ";
		
	}

		@Override
		public CitizenAppRegistrationEntity findbyid(Integer appId) {
			Optional<CitizenAppRegistrationEntity> citizenopt=repository.findById(appId);
			CitizenAppRegistrationEntity citizenentity=null;
			if(citizenopt.isPresent())
			{
				citizenentity=citizenopt.get();
			}
			return citizenentity;
		}

	
}
	





