package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.model.UserSigninDTO;
import com.masai.repository.UserSessionRepo;
import com.masai.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserSessionRepo UserSessionRepo;

	@Override
	public User createUser(User user) {
		Optional<User> opt = userRepo.findByMobile(user.getMobile());
		if (opt.isPresent()) {
			System.out.println("User already exist");
		}
		return userRepo.save(user);
	}

	@Override
	public List<User> allUser() {
		return userRepo.findAll();
	}

}