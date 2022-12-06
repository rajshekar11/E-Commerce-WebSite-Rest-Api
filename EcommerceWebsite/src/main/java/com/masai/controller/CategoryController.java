package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Category;
import com.masai.repository.CategoryRepo;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepo crep;
	
	@PostMapping("/category")
	public Category addCategory(@RequestBody Category category) {
		return crep.save(category);
	}
}
