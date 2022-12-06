package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.ProductException;
import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.repository.ProductsRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductsRepo prep;

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		Product addedProduct=prep.save(product);
		
		return addedProduct;
	}
	
	@Override
	public List<Product> viewAllProducts() throws ProductException {
		List<Product> products=prep.findAll();
		if(products.size()>0) {
			return products;
		}
		else {
			throw new ProductException("There are no products at the moment");
		}
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		Optional<Product> opt =   prep.findById(product.getProductId());
		if(opt.isPresent()) {
			return prep.save(product);
		}
		else {
			throw new ProductException("Invalid Product Details");
		}
	}

	@Override
	public Product viewProduct(Integer id) throws ProductException {
		Optional<Product> opt =  prep.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new ProductException("Product does not exist with id :"+id);
		}
	}

	@Override
	public Product removeProduct(Integer pid) throws ProductException {
		Optional<Product> opt =  prep.findById(pid);
		if(opt.isPresent()) {
			prep.delete(opt.get());
			return opt.get();
		}
		else {
			throw new ProductException("Product does not exist with id :"+pid);
		}
	
	
	}

	@Override
	public List<Product> findByCategory(Category category) throws ProductException {
		List<Product> products=prep.findByCategory(category);
		if(products.size()>0) {
			return products;
		}
		else {
			throw new ProductException("There are no products in this category");
		}
	}
	
}
