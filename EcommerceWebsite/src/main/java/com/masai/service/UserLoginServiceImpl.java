package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.model.UserDTO;
import com.masai.repository.UserRepo;
import com.masai.repository.UserSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserRepo UserDao;
	@Autowired
	private UserSessionRepo UserSessionRepo;

	@Override
	public String logIntoAccount(UserDTO userDTO) {
		Optional<User> opt = UserDao.findByMobile(userDTO.getMobile());

		if (!opt.isPresent()) {
			return "Please enter valid Mobile number!";
		}

		User user1 = opt.get();
		Integer userId = user1.getUserId();
		Optional<CurrentUserSession> currUseropt1 = UserSessionRepo.findByUserId(userId);

		if (currUseropt1.isPresent()) {
			return "User already logged in with this number.";
		}

		if (user1.getPassword().equals(userDTO.getPassword())) {

			 Random random = new Random();
			String key = random.ints(97, 122 + 1)
				      .limit(6)
				      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				      .toString();
			CurrentUserSession currentUserSession = new CurrentUserSession(userId, key, LocalDateTime.now());

			UserSessionRepo.save(currentUserSession);

			return currentUserSession.toString();
		} else {
			return "Please Enter valid password.";
		}

	}

	@Override
	public String logOutAccount(String key) {
		Optional<CurrentUserSession> currUserOpt = UserSessionRepo.findByUuid(key);

		if (currUserOpt.isPresent()) {
			CurrentUserSession currUser1 = currUserOpt.get();

			UserSessionRepo.delete(currUser1);
			return "User logged out successfully.";
		}
		return "User does not exist, Enter correct uuid";
	}

	@Override
	public boolean isLoggedIn(Integer userId) throws LoginException {
		Optional<CurrentUserSession> opt = UserSessionRepo.findByUserId(userId);
		if (opt.isPresent())
			return true;
		else
			throw new LoginException("LogIn first !! ");
	}

	@Override
	public boolean isLoggedInByUUID(String uuid) throws LoginException {
		Optional<CurrentUserSession> opt = UserSessionRepo.findByUuid(uuid);
		if (opt.isPresent())
			return true;
		else
			throw new LoginException("LogIn first !! ");
	}

}