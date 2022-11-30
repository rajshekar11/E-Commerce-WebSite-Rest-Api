package com.masai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserSigninDTO {
	
	private String customerName;
	private String email;
	private String address;
	private String mobile;
	private String password;

}