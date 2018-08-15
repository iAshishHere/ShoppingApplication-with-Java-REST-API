package com.application.OnlineShopping;


import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.shoppingapp.dao.OrderDao;
import com.shoppingapp.pojos.OrderDetails;


/*
 * OrderService class is to perform any operation on Order Module such as writing order details into the database.
 * 
 */

@Path("order")
public class OrderService {
	
	/*
	 * makeOrder function which deals with confirming order of a user and update each product with details into database.
	 * 
	 */
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<OrderDetails> makeOrder(List<OrderDetails> orderList) {
		OrderDao orderDao = new OrderDao();
		return orderDao.ConfirmOrder(orderList);
		
	}
}