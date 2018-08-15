package com.application.OnlineShopping;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shoppingapp.dao.CartDao;
import com.shoppingapp.pojos.CartDetails;


/*
 * CartService class deals with all the services related to cart
 * 
 */


@Path("cart")
public class CartService {
	
	/*To-Understand-Functions 
	 * 
	 * addToCart function is to perform action on cart database whenever a user will add some product into his cart
	 * increaseQuantity and decreaseQuantity function is used whenever a user will increase/ decrease a quantity of a product inside a cart
	 * removeProduct is a function which removes the particular product form the cart
	 * getUserCart returns all the product and details which were present inside user's cart
	 */
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public CartDetails addToCart(CartDetails cartDetails) {
		CartDao cartDao = new CartDao();
		return cartDao.addItemsToCart(cartDetails);
	}
	
	@GET
	@Path("incresequantity")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String increaseQuantity(
			@MatrixParam("UserName") String userName,
			@MatrixParam("ProductID") int productID,
			@MatrixParam("Quantity") int quantity
			) {
		CartDao cartDao = new CartDao();
		
		return cartDao.increaseProductQuantityFromCart(userName,productID,quantity);	
	}
	
	@GET
	@Path("decreasequantity")
	@Produces(MediaType.TEXT_PLAIN)
	public String decreaseQuantity(
			@MatrixParam("UserName") String userName,
			@MatrixParam("ProductID") int productID,
			@MatrixParam("Quantity") int quantity
			) {
		CartDao cartDao = new CartDao();
		return cartDao.decreaseProductQuantityFromCart(userName,productID,quantity);
	}
	
	
	@GET
	@Path("removeproduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeProduct(
			@MatrixParam("ProductID") int productID
			) {
		CartDao cartDao = new CartDao();
		return cartDao.removeProductQuantityFromCart(productID);		
	}
	
	@GET
	@Path("getusercart")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<CartDetails> getUserCart(
			@MatrixParam("UserName") String UserName
			) {
		CartDao cartDao = new CartDao();
		return cartDao.getUserCartDetails(UserName);	
		
	}
	
}
