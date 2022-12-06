package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CartException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.model.Address;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Orders;
import com.masai.model.ProductDTO;
import com.masai.model.User;
import com.masai.repository.CartRepo;
import com.masai.repository.CurrentUserRepo;
import com.masai.repository.OrdersRepo;
import com.masai.repository.UserRepo;

@Service
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	private OrdersRepo oRepo;
	
	@Autowired
	CurrentUserRepo curep;
	
	@Autowired
	UserRepo urep;
	
	@Autowired
	CartRepo crep;

	@Override
	public Orders addOrder(Orders order, String key) throws OrderException, CartException, LoginException {
		CurrentUserSession user = curep.findByUuid(key);
		 
		 if( user !=null ) {
			 
			 Integer userId = user.getUserId();
			 
			 Optional<User> ourCustomer = urep.findById(userId);
			 
			 Address addr = ourCustomer.get().getAddress();
			 
			 
			 Orders currOrder = new Orders();
			 
			 currOrder.setOrderDate(LocalDate.now());
			 currOrder.setOrderAddress(new Address(addr.getStreetNo(), addr.getBuildingName(), addr.getCity(), addr.getState(), addr.getPincode()));
			 
			 currOrder.setUser(ourCustomer.get());
			 currOrder.setOrderStatus("Order confirmed");
			 
			 List<ProductDTO> list = crep.findByUser(ourCustomer.get()).getProducts();
			 
			 if( list.size() < 1) {
				 throw new CartException("Add product to the cart first...");
			 }
			 
			 List<ProductDTO> productList = new ArrayList<>();

			 Double total = 0.0 ;
			 
			 for(ProductDTO proDto : list) {
				 
				 productList.add(proDto);
				 
				 total += (proDto.getPrice() * proDto.getQuantity()) ;
				 
			 }
			 
			 currOrder.setTotal(total);	
			 currOrder.setPList(productList); 
			 
			 Cart customerCart = crep.findByUser(ourCustomer.get()) ;
			 
			 customerCart.setProducts(new ArrayList<>());
			 
			 crep.save(customerCart);
			 
			 return oRepo.save(currOrder);
			 
		 }
		 else {
			 throw new LoginException("Login first");
		 }
		 
	}

	@Override
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders removeOrder(Integer oriderId) throws OrderException {
		Optional<Orders> opt1=  oRepo.findById(oriderId);
		
		if(opt1.isPresent()) {
			oRepo.delete(opt1.get());
			return opt1.get();
		}
		else {
			throw new OrderException("No order found");
		}
	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		Optional<Orders> opt1=  oRepo.findById(orderId);
		
		if(opt1.isPresent()) {
			return opt1.get();
		}
		else {
			throw new OrderException("No order found");
		}
	}

	@Override
	public Orders viewOrderByUserId(Integer userid) throws OrderException {
		Optional<User> opt1= urep.findById(userid);
		if(opt1.isPresent()) {
			User user=opt1.get();
			Orders o= oRepo.findByUser(user);
			return o;
		}
		throw new OrderException("User does not exists");
	}

}
