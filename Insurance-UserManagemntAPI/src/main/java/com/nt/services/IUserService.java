package com.nt.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.nt.bindings.ActivateUser;
import com.nt.bindings.LoginCredentials;
import com.nt.bindings.RecoverPssword;
import com.nt.bindings.UserAccount;

public interface IUserService
{
	public String registeruser(UserAccount ua) throws Exception;
	public ResponseEntity<Map<String, String>> activateuser(ActivateUser au);
	public String login(LoginCredentials credentials);
	public List<UserAccount> listuser();
	public UserAccount UsershowById(Integer id);
	public UserAccount showUserByEmailAndName(String email,String name);
	public String updateUser(UserAccount user);
	public String daleteUser(Integer Id);
	public String changeUserStatus(Integer id,String status);
	public String recoverPassword(RecoverPssword recover) throws Exception; 
}
