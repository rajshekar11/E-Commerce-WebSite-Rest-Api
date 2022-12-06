package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CartException;
import com.masai.exception.LoginException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Cart;
import com.masai.model.ProductDTO;
import com.masai.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Integer productId , 
    									  @RequestParam Integer quantity,
    									  @RequestParam Integer userId) throws CartException, UserException, ProductException{

        Cart cartItem = cartService.addProductToCart(productId, quantity, userId) ;

        return new ResponseEntity<>(cartItem, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/getByID/{cardId}")
    public ResponseEntity<Cart> addToCart(@PathVariable("cardId") Integer cardId) throws CartException{

        Cart cartItem = cartService.getCartByID(cardId);

        return new ResponseEntity<>(cartItem, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/allproducts")
    public ResponseEntity<List<ProductDTO>> getAllProductInCart(@RequestParam String key) throws CartException, LoginException {
    	
    	List<ProductDTO> list = cartService.viewAllProducts(key) ;
    	
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
    
    @DeleteMapping("/products/remove") 
    public ResponseEntity<List<ProductDTO>> removeAProductFromCart(@RequestParam Integer productId , 
    									      		   @RequestParam Integer userId) throws CartException, ProductException, UserException {
    	
    	List<ProductDTO> list =cartService.removeProductFromCart(productId, userId);
    	
    	return new ResponseEntity<>(list, HttpStatus.OK);
    	
    }
    
    
}
