package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.ProductException;
import com.masai.model.Product;
import com.masai.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prep;

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
			throw new ProductException("Product is not Found");
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
	Product	existingProduct = prep.findById(pid).orElseThrow(()->new ProductException("Product does not exist with id :"+pid));
	
	prep.delete(existingProduct);
	
	return existingProduct ;
	
	
	}
	
}
