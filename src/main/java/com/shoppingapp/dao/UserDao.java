package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shoppingapp.pojos.UserDetails;

	
	/*
	 * UserDao is a class which deals with all the operation related to user database such as varifying a user.
	 * 
	 */

public class UserDao {
	
	/*
	 * 
	 * readUserDetails is a function which read the user information form the database and verifies it whether a user is authentic or not
	 * 
	 */
	
	public UserDetails readUserDetails(UserDetails userDetails) {
		
		Connection con = null;
	    PreparedStatement ps = null;
		ResultSet rs = null;

        try {        	
        	con = ConnectionDao.getConnection();
            ps=con.prepareStatement("select Username, Password from LoginDetails where Username=? and Password=?");  
    		ps.setString(1,userDetails.getUsername());  
    		ps.setString(2,userDetails.getPassword());
    		
    		rs=ps.executeQuery();
    		  
    		if(rs.next()) {   			
    			return userDetails; 			
    		}
    		
    		}catch(Exception e){
    			System.out.println(e);
			}finally {
			    try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			    try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
			    try { con.close(); } catch (Exception e) { e.printStackTrace(); }
			}      
    		return null;  
    		} 

}
