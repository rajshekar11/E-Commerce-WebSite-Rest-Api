package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.ProductDTO;

@Repository
public interface ProductDTOrepo extends JpaRepository<ProductDTO, Integer> {


}
