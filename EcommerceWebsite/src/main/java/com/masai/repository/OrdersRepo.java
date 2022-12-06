package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Cart;
import com.masai.model.Orders;
import com.masai.model.User;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {

	public Orders findByUser(User user);
}
