package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CartException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.model.Orders;
import com.masai.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
    private  OrdersService orderService;
    
    @PostMapping("/addorders")
    public ResponseEntity<Orders> addOrderHandler(@RequestBody Orders order,@RequestParam String key) throws OrderException, CartException, LoginException{
    	
    	Orders addedOrder = orderService.addOrder(order, key);
    	
    	return new ResponseEntity<Orders>(addedOrder, HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/deleteorders/{orderid}")
    public ResponseEntity<Orders> removeOrderHandler(@PathVariable("orderid") Integer orderId) throws OrderException{
    	
    	Orders deletedOrder = orderService.removeOrder(orderId);
    	
    	return new ResponseEntity<Orders>(deletedOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/getorders")
    public ResponseEntity<Orders> viewOrderHandler(@RequestParam Integer orderId) throws OrderException{
    	
    	Orders existingOrder = orderService.viewOrder(orderId);
    	
    	return new ResponseEntity<Orders>(existingOrder, HttpStatus.OK);
    	
    }

    @GetMapping("/allordersByUser")
    public ResponseEntity<Orders> getAllOrdersByUserIdHandler(@RequestParam Integer userId) throws OrderException{
    	    	
    		Orders orders = orderService.viewOrderByUserId(userId);
    	
    	return new ResponseEntity<Orders>(orders, HttpStatus.OK);
    	
    }
}
