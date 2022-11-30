
package com.masai.service;

import java.util.List;

import com.masai.model.User;
import com.masai.model.UserSigninDTO;

public interface UserService {

	public User createUser(User user);

	public List<User> allUser();

}