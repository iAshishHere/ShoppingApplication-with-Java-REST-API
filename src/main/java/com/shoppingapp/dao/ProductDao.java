package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.pojos.ProductDetails;

	/*
	 * ProductDao is a class which deals with all the operation related to product database such as reading products, fetching products
	 * updating products
	 * 
	 */


public class ProductDao {
	
	Connection connection = null;
    PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	
	
	/*
	 * 
	 * readProduct is a function which reads all the product available in the database and returing all the product list back to the front end
	 * 
	 */

	public List<ProductDetails> readProduct() {
		
		   List<ProductDetails> productDetails = new ArrayList<ProductDetails>();
     
		try {
   
			connection=ConnectionDao.getConnection();
			
        	preparedStatement=connection.prepareStatement("select * from ProductDetails");  
        	resultSet=preparedStatement.executeQuery();
            
           
    		while(resultSet.next())
    		{	
    			ProductDetails productDetail = new ProductDetails();
    			productDetail.setProductID(resultSet.getInt("ProductID"));
    			productDetail.setProductName(resultSet.getString("ProductName"));
    			productDetail.setPrice(resultSet.getDouble("Price"));
    			productDetail.setQuantity(resultSet.getInt("Quantity"));

    			productDetails.add(productDetail);
    			
    		}
    		}catch(Exception e){
    			System.out.println(e);
			}finally {
			    try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
			    try { preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
			    try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
			}  
    		
		return productDetails;
		
	}
	
	/*
	 * 
	 * updateProductQuantity function is updating the quantity of a product whenever a user is purchasing a particular product
	 * 
	 */
	
	public void updateProductQuantity(int productID, int quan) {
		
		try { 
			
		 connection=ConnectionDao.getConnection();
         
         preparedStatement=connection.prepareStatement("update productdetails set Quantity = Quantity-? where ProductID=? AND Quantity>0");
         preparedStatement.setInt(1, quan);
         preparedStatement.setInt(2, productID);
         preparedStatement.executeUpdate();


 		}catch(Exception e){
 			System.out.println(e);
 			
 			
			}finally {
			    try { preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
			    try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
			} 
	}
	
	public void excelToDatabase(List<ProductDetails> detailsList) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con =ConnectionDao.getConnection();
			
			/*
			 * iterating through detailsList to insert all the data of detailsList inside the database
			 */
			
			for (int counter = 0; counter < detailsList.size(); counter++) { 	
	
				ps = con.prepareStatement(
						"insert into productdetails(ProductID,ProductName,Price,Quantity) values(?,?,?,?);");
				
				ps.setInt(1,detailsList.get(counter).getProductID() );
				ps.setString(2,detailsList.get(counter).getProductName());
				ps.setDouble(3,detailsList.get(counter).getPrice());
				ps.setInt(4,detailsList.get(counter).getQuantity());

				ps.executeUpdate();
		      }  
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();}
			
		}
	}
}

