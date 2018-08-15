package com.shoppingapp.pojos;

	
	/*
	 * ProductDetails is a POJO which has information of a products
	 * 
	 */

public class ProductDetails {

	private int productID;
	private String productName;
	private double price;
	private int quantity;
	
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

