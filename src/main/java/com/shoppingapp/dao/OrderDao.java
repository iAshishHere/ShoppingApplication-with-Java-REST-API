package com.shoppingapp.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.util.List;

import com.shoppingapp.pojos.OrderDetails;


	/*
	 * OrderDao class deals with all the database operation with Cart such as adding products to cart, 
	 * removing products from the cart, updating cart, etc
	 *  
	 */

public class OrderDao {
	
	/*
	 * confirming function is for writing the order of a particular user in the database
	 * 
	 */

	public List<OrderDetails> ConfirmOrder(List<OrderDetails> orderList) {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con =ConnectionDao.getConnection();
			ProductDao productDao = new ProductDao();
			
			/*
			 * Iterating over orderList (sent from the front-end) in order to store each order of a particular user in the database
			 * 
			 */
			
			for(int counter=0;	counter<orderList.size();	counter++) {
				
				ps = con.prepareStatement(
						"insert into OrderDetails(UserName, ProductID,ProductName,Price,Quantity) values(?,?,?,?,?);");
				ps.setString(1, orderList.get(counter).getUsername());
				ps.setInt(2, orderList.get(counter).getId());
				ps.setString(3,orderList.get(counter).getProductName());
				ps.setDouble(4, orderList.get(counter).getPrice()*orderList.get(counter).getQuantity());
				ps.setInt(5, orderList.get(counter).getQuantity());
				ps.executeUpdate();
				productDao.updateProductQuantity(orderList.get(counter).getId(),orderList.get(counter).getQuantity());
			}

			return orderList;
			

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();}
			
		}
		return orderList;
		
		
	}

}
