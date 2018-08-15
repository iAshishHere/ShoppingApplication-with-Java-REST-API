package com.application.OnlineShopping;

import com.shoppingapp.dao.UserDao;
import com.shoppingapp.pojos.UserDetails;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

	/*
	 * This class deals with all the operation related to User
	 *
	 */

@Path("verifyuser")
public class UserService {
	
	/*
	 * doValidation function is validating username and respective password from the data base
	 *
	 */
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserDetails doValidation(UserDetails userDetails)
	{
		UserDao userDao = new UserDao();
		return userDao.readUserDetails(userDetails);
		
	}
}
