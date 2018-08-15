package com.application.OnlineShopping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.shoppingapp.dao.ProductDao;
import com.shoppingapp.pojos.ProductDetails;
import com.shoppingapp.pojos.UserDetails;


	/*
	 * This class receives an CSV file sent by the Admin form the front end which consist of all the newly updated products,
	 * it saves the uploaded file in the local disk and normalize it into the database accordingly
	 * 
	 */

@Path("uploadfile")
public class RecievingExcelByAdmin {


	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public UserDetails uploadFile(
			@FormDataParam("file") InputStream UploadInputStream, 
			@FormDataParam("file") FormDataContentDisposition fileDetails) {
		
		
		saveToDisk(UploadInputStream,fileDetails);
		
		sendExceltoDB(fileDetails.getFileName());
		
		//return "File Uploaded successfully";
		UserDetails ud = new UserDetails();
		ud.setUsername("Ashish");
		ud.setPassword("ashish");
		return ud;
		
		 
	}
	
	/*
	 * Saving the particular file into a local disk, so that while sending it to the database it can fetch it back whenever needed. 
	 * 
	 */

	private void saveToDisk(InputStream uploadedInputStream, FormDataContentDisposition fileDetails) {
	
		
		String uploadFileLocation = "d://upload/" + fileDetails.getFileName();
		
		try {
			OutputStream out = new FileOutputStream(new java.io.File(uploadFileLocation));
			int read =0;
			byte[] bytes = new byte[1024];
			
			out=new FileOutputStream(new java.io.File(uploadFileLocation));
			while((read=uploadedInputStream.read(bytes))!=-1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			
			out.close();
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/*
	 * Sending the received file into the database in a form of array list.
	 * 
	 */
	
	
	private void sendExceltoDB(String fileName) {
		
		
		String filename= "d://upload/" + fileName;
		
		File file = new File(filename);
		
		List<ProductDetails> detailsList = new ArrayList<ProductDetails>();
		try {
			Scanner inputStream = new Scanner(file);
			inputStream.next();
			
			while(inputStream.hasNext()) {
				String data=inputStream.next();
				
				String value[]=data.split(",");
				
				ProductDetails productDetails = new ProductDetails();
	
				productDetails.setProductID(Integer.parseInt(value[0]));
				
				productDetails.setProductName(value[1]);
				
				productDetails.setPrice(Double.parseDouble(value[2]));
				
				productDetails.setQuantity(Integer.parseInt(value[3]));
				
				
				detailsList.add(productDetails);
				
			
			}
			inputStream.close();
			ProductDao d = new ProductDao();
			d.excelToDatabase(detailsList);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
