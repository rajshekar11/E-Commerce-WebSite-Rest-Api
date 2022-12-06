package com.masai.model;




import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private LocalDate orderDate;
	private String orderStatus;
	private Double total;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "User_order",joinColumns = @JoinColumn(name="order_id", referencedColumnName = "orderId"))
	private User user;
	
	@ElementCollection
	@CollectionTable(name="product_order", joinColumns = @JoinColumn(name="order_id", referencedColumnName = "orderId"))
	private List<ProductDTO> pList = new ArrayList<>();
	
	@Embedded
	private Address orderAddress;
	 	
	public Orders(LocalDate orderDate, String orderStatus, User user, List<ProductDTO> pList,
			Address orderAddress) {
		super();
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.user = user;
		this.pList = pList;
		this.orderAddress = orderAddress;
	}
}
