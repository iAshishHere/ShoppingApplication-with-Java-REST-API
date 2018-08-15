package com.shoppingapp.pojos;



	/*
	 * OrderDetails is a POJO which has information which is needed to maintain in the orders.
	 * 
	 */
	

public class OrderDetails {
	
	private int id;
	private String username;
	private String productName;
	private double price;
	private int quantity;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
