package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private Integer addressId;
	private String streetNo;
	private String buildingName;
	private String city;
	private String state;
	private String pincode;
	
	public Address(String streetNo, String buildingName, String city, String state, String pincode) {
		this.streetNo = streetNo;
		this.buildingName = buildingName;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	
	
}
