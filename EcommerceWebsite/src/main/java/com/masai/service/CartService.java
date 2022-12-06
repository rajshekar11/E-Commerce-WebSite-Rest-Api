package com.masai.service;

import java.util.List;

import com.masai.exception.CartException;
import com.masai.exception.LoginException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Cart;
import com.masai.model.ProductDTO;

public interface CartService {

	public Cart addProductToCart(Integer productId, int quantity, Integer userId)
			throws CartException, UserException, ProductException;
	
	public List<ProductDTO> removeProductFromCart(Integer productId,Integer userId) throws CartException, ProductException, UserException  ;
	
	
	public List<ProductDTO> viewAllProducts(String key)  throws CartException, LoginException;

	public Cart getCartByID(Integer cartId) throws CartException;
}
