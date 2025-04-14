package com.nt.bindings;

import java.math.BigInteger;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount
{
	private Integer userId;
	private String name;
	private String email;
	private String password;

}
