package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
	public Optional<User> findByMobile(String string);
	List<User> findByUserId(Integer userId);
	
}