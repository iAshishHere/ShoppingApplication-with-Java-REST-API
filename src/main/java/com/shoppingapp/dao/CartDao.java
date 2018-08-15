package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.pojos.CartDetails;


	/*
	 * CartDao class deals with database related operation on cart such as add items to cart, update items inside a cart,
	 * remove/delete items from the cart
	 */
	 
		// TO-DO-later
	/*
	 * remove increaseProductQuantityFromCart and decreaseProductQuantityFromCart fucntion and make it as updateProductQuantityFromCart.
	 */



public class CartDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*
	 * addItemsToCart function is for adding item inside a cart, it receives cartDetails list wich consists one or more than one
	 * item and all the items will be stored in database.
	 */
	
	public CartDetails addItemsToCart(CartDetails cartDetails) {
		try {

			con =ConnectionDao.getConnection();
			
			ps = con.prepareStatement(
					"insert into cartdetails(UserName, ProductID,ProductName,Price,Quantity) values(?,?,?,?,?);");
			
			ps.setString(1, cartDetails.getUsername());
			ps.setInt(2, cartDetails.getProductID());
			ps.setString(3,cartDetails.getProductName());
			ps.setDouble(4, cartDetails.getPrice()*cartDetails.getQuantity());
			ps.setInt(5, cartDetails.getQuantity());
			ps.executeUpdate();

			return cartDetails;
			

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();}
			
		}
		return null;
		
	}
	
	/*
	 * increaseProductQuantityFromCart function is used whenever a user hits to increase the quantity of an item form the cart
	 *  
	 */
	
	

	public String increaseProductQuantityFromCart(String userName, int productID, int quantity) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try { 
			
			 con=ConnectionDao.getConnection();
	         
	         ps=con.prepareStatement("update cartdetails set Quantity = Quantity+? where UserName=? AND ProductID=?");
	         ps.setInt(1, quantity);
	         ps.setString(2, userName);
	         ps.setInt(3, productID);
	         ps.executeUpdate();

	         return "success";
	         
	 		}catch(Exception e){
	 			System.out.println(e);
	 			
	 			
				}finally {
				    try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
				    try { con.close(); } catch (Exception e) { e.printStackTrace(); }
				} 
		return "not success";
		
	}
	
	
	/*
	 * decreaseProductQuantityFromCart function is used whenever a user hits to decrease the quantity of an item form the cart
	 *  
	 */

	public String decreaseProductQuantityFromCart(String userName, int productID,int quantity) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try { 
			
			 con=ConnectionDao.getConnection();
	         
	         ps=con.prepareStatement("update cartdetails set Quantity = Quantity-? where UserName=? AND ProductID=?");
	         ps.setInt(1, quantity);
	         ps.setString(2, userName);
	         ps.setInt(3, productID);
	         ps.executeUpdate();

	         return "success";
	         
	 		}catch(Exception e){
	 			System.out.println(e);
	 			
	 			
				}finally {
				    try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
				    try { con.close(); } catch (Exception e) { e.printStackTrace(); }
				} 
		return "not success";
		
	}
	
	/*
	 * removeProductQuantityFromCart function completely removes the product from the cart. It recieves productID as an argument
	 * and based on that it deletes the particular product form the cart.
	 * 
	 */
	
	

	public String removeProductQuantityFromCart(int productID) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try { 			
			 con=ConnectionDao.getConnection();
	         
	         ps=con.prepareStatement("delete from cartdetails where ProductID=?");
	         ps.setInt(1, productID);
	         ps.executeUpdate();

	         return "success";
	         
	 		}catch(Exception e){
	 			System.out.println(e);
	 			
	 			
				}finally {
				    try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
				    try { con.close(); } catch (Exception e) { e.printStackTrace(); }
				} 
		return "not success";
		
	}
	
	
	/*
	 * getUserCartDetails gives all the product available on particular user's cart back to the front end
	 * 
	 */
	
	

	public List<CartDetails> getUserCartDetails(String UserName) {
		
	
		
		List<CartDetails> cartList = new ArrayList<CartDetails>();
		try {
			   
			con=ConnectionDao.getConnection();
			
        	
        	
        	ps=con.prepareStatement("select * from cartdetails where UserName=?;");
        	ps.setString(1, UserName);
        	rs=ps.executeQuery();
        	
        	while(rs.next()) {		
    			CartDetails cartDetail = new CartDetails();
    			cartDetail.setUsername(rs.getString("UserName"));
    			cartDetail.setProductID(rs.getInt("ProductID"));
    			cartDetail.setProductName(rs.getString("ProductName"));
    			cartDetail.setPrice(rs.getDouble("Price"));
    			cartDetail.setQuantity(rs.getInt("Quantity"));
    			cartList.add(cartDetail);
    		}
        	
        	return cartList;
        	
        	
    		}catch(Exception e){
    			System.out.println(e);
			}finally {
			    try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			    try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
			    try { con.close(); } catch (Exception e) { e.printStackTrace(); }
			}  
    		
		return cartList;
		
	}

}
