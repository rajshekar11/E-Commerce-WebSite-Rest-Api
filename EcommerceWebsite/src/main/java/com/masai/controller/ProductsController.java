package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ProductException;
import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductService pser;
	
	 @PostMapping("/addProd")
	    public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product ) throws ProductException{
	    	
	    	Product addedProduct = pser.addProduct(product);
	    	
	    	return new ResponseEntity<Product>(addedProduct, HttpStatus.CREATED);
	    	
	    }
	    
	    @GetMapping("/getAll")
	    public ResponseEntity<List<Product>> getAllProductsHandler() throws ProductException{
	    	
	    	List<Product> allProducts = pser.viewAllProducts();
	    	
	    	return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
	    	
	    }
	    
	    @PutMapping("/update")
	    public ResponseEntity<Product> updateProductHandler(@RequestBody Product product) throws ProductException{
	    	
	    	Product updatedProduct = pser.updateProduct(product);
	    	
	    	return new ResponseEntity<Product>(updatedProduct, HttpStatus.ACCEPTED);
	    	
	    }
	    
	    @GetMapping("/getById/{productId}")
	    public ResponseEntity<Product> getProductHandler(@PathVariable("productId") Integer productId) throws ProductException{
	    	
	    	Product exixtingProduct = pser.viewProduct(productId);
	    	
	    	return new ResponseEntity<Product>(exixtingProduct, HttpStatus.OK);
	    	
	    }
	    
	    @GetMapping("/products/category")
	    public ResponseEntity<List<Product>> getProductByCategory(@RequestBody Category category) throws ProductException{
	    	
	    	List<Product> categorywiseProducts = pser.findByCategory(category);
	    	
	    	return new ResponseEntity<List<Product>>(categorywiseProducts, HttpStatus.OK);
	    	
	    }
	    
	    
	    @DeleteMapping("/delete/{productId}")
	    public ResponseEntity<Product> deleteProductHandler(@PathVariable("productId") Integer productId) throws ProductException{
	    	
	    	Product deletedProduct = pser.removeProduct(productId);
	    	
	    	return new ResponseEntity<Product>(deletedProduct, HttpStatus.OK);
	    	
	    }
	
}
