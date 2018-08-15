package com.application.OnlineShopping;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shoppingapp.dao.ProductDao;
import com.shoppingapp.pojos.ProductDetails;


/*
 * Product services deals with all the services related to product, such as fetching all the products, updating product quantity 
 * 
 */

@Path("getproduct")
public class ProductService {
	
	/*
	 * fetchProductDetails gives back all the products in a JSON format back to the front end where the request has come from.
	 * 
	 */
	
	@GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<ProductDetails> fetchProductDetails()
	{
		ProductDao productDao = new ProductDao();
		return productDao.readProduct();
	}
	
	public void updateProduct(int id, int quantity) {
		ProductDao productDao = new ProductDao();
		productDao.updateProductQuantity(id,quantity);	
	}

}


