package com.masai.service;

import com.masai.exception.LoginException;
import com.masai.model.UserDTO;

public interface UserLoginService {
	public String logIntoAccount(UserDTO userDTO);

	public String logOutAccount(String key);

	public boolean isLoggedIn(Integer userId) throws LoginException;

	public boolean isLoggedInByUUID(String uuid) throws LoginException;
}