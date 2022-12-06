package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CartException;
import com.masai.exception.LoginException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Product;
import com.masai.model.ProductDTO;
import com.masai.model.User;
import com.masai.repository.CartRepo;
import com.masai.repository.CurrentUserRepo;
import com.masai.repository.ProductDTOrepo;
import com.masai.repository.ProductsRepo;
import com.masai.repository.UserRepo;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepo cartRepo;

	@Autowired
	UserRepo urepo;

	@Autowired
	ProductsRepo prep;

	@Autowired
	ProductDTOrepo productDtoRepo;
	
	@Autowired
	CurrentUserRepo currentuserrep;
	
	@Autowired
	ProductService productService;
	
	@Override
	public Cart addProductToCart(Integer productId, int quantity, Integer userId) throws CartException, UserException, ProductException {

		
		User currentUser = urepo.findById(userId).get();
		
		if(currentUser == null) {
			throw new UserException("Please provide currect user id") ;
		}
		
		Optional<Product> optProduct = prep.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		
		Product currentProduct = optProduct.get();
		
		Cart userCart = cartRepo.findByUser(currentUser);
		
		if(userCart == null) { 
			
			userCart = new Cart();
			
			userCart.setUser(currentUser);
			
			List<ProductDTO> list = userCart.getProducts();
			
			ProductDTO productDto = new ProductDTO();
			
			productDto.setProductId(currentProduct.getProductId());
			productDto.setProductName(currentProduct.getProductName());
			productDto.setPrice(currentProduct.getPrice());
			productDto.setColor(currentProduct.getColor());
			productDto.setManufacturer(currentProduct.getManufacturer());
			productDto.setQuantity(quantity);
			
			list.add(productDto);
		
			cartRepo.save(userCart) ;
			prep.save(currentProduct);
			
			return userCart;
				
		}
		else {
			
			List<ProductDTO> list = userCart.getProducts();
			
			ProductDTO productDto = new ProductDTO();
			
			productDto.setProductId(currentProduct.getProductId());
			productDto.setProductName(currentProduct.getProductName());
			productDto.setPrice(currentProduct.getPrice());
			productDto.setColor(currentProduct.getColor());
			productDto.setManufacturer(currentProduct.getManufacturer());
			productDto.setQuantity(quantity);
			
			currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
			
			list.add(productDto);
			
			
			cartRepo.save(userCart) ;
			prep.save(currentProduct);
			 
			return userCart;
			 
		}
	}

	@Override
	public List<ProductDTO> removeProductFromCart(Integer productId, Integer userId)
			throws CartException, ProductException, UserException {
		User currentUser = urepo.findById(userId).get();
		
		if(currentUser == null) {
			throw new LoginException("User doesnot exists") ;
		}
		
		Optional<Product> optProduct = prep.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		
		Product currentProduct = optProduct.get();
		
		Cart userCart = cartRepo.findByUser(currentUser);
		
		if(userCart != null) {
			List<ProductDTO> list = userCart.getProducts();
			
			boolean flag = false;

			
			for(int i = 0; i < list.size(); i++) {
				
				ProductDTO productdto = list.get(i);
				
				if(productdto.getProductId() == productId) {
					
					
					productDtoRepo.deleteById(productdto.getId());
					
					flag = true;
					prep.save(currentProduct);
					
					list.remove(i);
					break;
				}
			}
			
			if(!flag) {
				throw new ProductException("There is no product with id :"+productId);
			}
			
			userCart.setProducts(list);
			cartRepo.save(userCart);
			
			return list;
		}
		else {
			throw new ProductException("The cart is empty") ;
		}
	
	}

	@Override
	public List<ProductDTO> viewAllProducts(String key) throws CartException, LoginException {
		CurrentUserSession currentUserse = currentuserrep.findByUuid(key);
		
		User currentuser=urepo.findById(currentUserse.getId()).get();
		
		if(currentuser == null) {
			throw new LoginException("Please login to view all products in the cart") ;
		}
		
		Cart customerCart = cartRepo.findByUser(currentuser);
		
		if(customerCart == null) {
			throw new CartException("You dont have anything in your cart");
		}
		
		return customerCart.getProducts();
	}

	
	@Override
	public Cart getCartByID(Integer cartId) throws CartException {
			
		Optional<Cart> opt= cartRepo.findById(cartId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		
		throw new CartException("There is no cart by id: "+cartId);
	}
	
}
