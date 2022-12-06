package com.masai.service;

import java.util.List;

import com.masai.exception.CartException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.model.Orders;

public interface OrdersService {

	public Orders addOrder(Orders order, String key) throws OrderException, CartException, LoginException;
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException;
	public Orders removeOrder(Integer oriderId) throws OrderException;
	public Orders viewOrder(Integer orderId) throws OrderException;
	public Orders viewOrderByUserId(Integer userid) throws OrderException;
}
