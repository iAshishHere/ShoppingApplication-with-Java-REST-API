package com.shoppingapp.pojos;


	/*
	 * CartDetails is a POJO which has information which is needed to maintain in the cart.
	 * 
	 */

public class CartDetails {
	
	private String username;
	private int productID;
	private String productName;
	private double price;
	private int quantity;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
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
