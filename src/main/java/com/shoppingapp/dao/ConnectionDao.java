package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



	/*
	 * ConnectionDao is a class which deals with connections of database.
	 * It contains static method of type Connection, whenever the object is created of this class, these method is called automatically
	 * 
	 * 
 */


public class ConnectionDao {
	
	/*
	 * This method is called automatically as soon as instance of this class is created, getConnection() method contails basic information
	 * needed for connection such as, localhost, dbName, userName, password.
	 */
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		String hostName="localhost";
		String dbName="Login";
		String userName="root";
		String password="admin";
		
		return getMySQLConnection(hostName, dbName, userName, password);
		
	}
	
	/*
	 * This method is called from getConnection() method and it consists connectionURL and Driver information
	 * 
	 */

	protected static Connection  getMySQLConnection(String hostName,String dbName, String userName,String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		  
	     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	  
	     Connection conn = DriverManager.getConnection(connectionURL, userName,
	             password);
	     return conn;

	}
}
