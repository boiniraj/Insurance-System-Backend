package com.nt.rest;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.ActivateUser;
import com.nt.bindings.LoginCredentials;
import com.nt.bindings.RecoverPssword;
import com.nt.bindings.UserAccount;
import com.nt.services.UserImplService;

@RestController
@RequestMapping("/user-api")
@CrossOrigin
public class UserMnagementController
{
	@Autowired
	private UserImplService userService;
	
	 @PostMapping("/Register-User")
	public ResponseEntity<Map<String, String>> saveUser(@RequestBody UserAccount account) {
        Map<String, String> response = new HashMap<>();
        try {
            String msg = userService.registeruser(account);
            response.put("message", msg);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//save	
			
	
	 
	 @PostMapping("/activate-User")
	 public ResponseEntity<Map<String, String>> activateUser(@RequestBody ActivateUser user) {
	        return userService.activateuser(user);
	    }//activate user
	
	
	 @PostMapping("/login-User")
	    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginCredentials credentials) {
	        Map<String, String> response = new HashMap<>();
	        try {
	            String msg = userService.login(credentials);
	            response.put("message", msg);
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.put("message", e.getMessage());
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }//login user
	
	
	@GetMapping("/report")
	public ResponseEntity<?> showUser()
	{
		try {
			List<UserAccount>list=userService.listuser();
			return new ResponseEntity<List<UserAccount>>(list,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}//report user
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> showUserById(@PathVariable Integer id)
	{
		try {
			UserAccount account=userService.UsershowById(id);
			return new ResponseEntity<UserAccount>(account,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}//findById
	
	@GetMapping("/find/{email}/{name}")
	public ResponseEntity<?> showUserEmailAndname(@PathVariable String email,@PathVariable String name)
	{
		try {
			UserAccount account=userService.showUserByEmailAndName(email,name);
			return new ResponseEntity<UserAccount>(account,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}//find email and name
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUserDetails(@RequestBody UserAccount account)
	{
		try {
			String msg=userService.updateUser(account);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}//update user
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteUser(@PathVariable Integer id)
	{
		try {
			String msg=userService.daleteUser(id);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}//delete user
	
	
	@PatchMapping("/change/{id}/{status}")
	public ResponseEntity<String> changeStatus (@PathVariable Integer id,@PathVariable String status)
	{
		try {
			String msg=userService.changeUserStatus(id,status);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}//user change status
	
	@PostMapping("/recoverPassword")
	public ResponseEntity<String> RecoveryPwd (@RequestBody RecoverPssword recover)
	{
		try {
			String msg=userService.recoverPassword(recover);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}//recover
}//end Class
