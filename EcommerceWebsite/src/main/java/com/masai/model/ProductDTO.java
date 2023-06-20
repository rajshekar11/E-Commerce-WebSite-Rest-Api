package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private Integer productId;
	private String productName;
	private Double price;
	private String color;
	private String manufacturer;
	private Integer quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Order order;
	
}
