package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Cart;
import com.masai.model.User;

public interface CartRepo extends JpaRepository<Cart, Integer> {

	public Cart findByUser(User user);
}
