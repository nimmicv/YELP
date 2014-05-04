package com.kaizen.yelp.ui.resources;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONTokener;

import com.amazonaws.http.HttpResponse;

//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;


@Path("/kaizen/coupons")
@Produces(MediaType.TEXT_HTML)
public class CouponResource {
	private final String USER_AGENT = "Mozilla/5.0";

	/*@GET

	public Response getCoupons() throws UnirestException{
		System.out.println(" hello get");

	HttpResponse<JsonNode> request = Unirest.get("https://8coupons.p.mashape.com/getcategory?key=8871a60a47c12653695c681668f16e7a525d6bffc8c4af98c18a92e6e35578696c62b8cea9bf4f32c444ce507b243c39")
			.header("X-Mashape-Authorization", "0RbB2ECMjHfgGrM8ctKnsv97sZaqhcuT")
			.asJson();

	String name = " Food";
	System.out.println(" sub category" + "Food");

	JSONArray categoryArray = request.getBody().getArray();


	System.out.println(" request body" + request.getBody().getArray());
	for (int i =0 ; i < categoryArray.length() ; i++){


		if (categoryArray.getJSONObject(i).get("subCategory").equals(name)){

			System.out.println(" display id " +categoryArray.getJSONObject(i).get("subcategoryID"));

		}

		//System.out.println(" get results "+categoryArray.getJSONObject(i));


	}

	return Response.status(200).build();


}
*/

	
	
	@GET
	@Path("/test")

	public Response getTest() throws  Exception{
		System.out.println(" hello world");
		
		HttpClient client1 = new DefaultHttpClient();
		
		
		HttpGet request1 = new HttpGet("http://api.8coupons.com/v1/getsubcategory");
		org.apache.http.HttpResponse response = client1.execute(request1);
		
		
		
		System.out.println(" response" + response.getEntity().getContent().toString());
		
		BufferedReader reader = new BufferedReader  (new InputStreamReader(( response).getEntity().getContent()));
		StringBuilder allContents = new StringBuilder();

		String line = null;

		while ((line = reader.readLine()) != null) {
			allContents.append(line);
		}
		System.out.println(" string value :"+ allContents.toString());
		
	
		//allContents.toString().replaceAll("\\s", "");
//		
//		System.out.println(" new output value:" + allContents.toString().replaceAll("\\s", ""));
//		//allContents.replace(arg0, arg1, arg2)
//		
//		String input = allContents.toString().replaceAll("\\s", "");
//		InputStream is = new ByteArrayInputStream(input.getBytes());
//		 
//		// read it with BufferedReader
//		BufferedReader br = new BufferedReader(new InputStreamReader(is));
	 
//		JSONTokener tokener = new JSONTokener(br);
//		JSONArray finalResult = new JSONArray(tokener);
//		
		
		String userSelectedCategory = "Moroccan";
		
		String categoryId;
		
		JSONArray arr = new JSONArray(allContents.toString());
		for (int i =0 ; i < arr.length() ; i++){
			//System.out.println(" array deatils :" + arr.getJSONObject(i).get("category"));
			
			String sub = (String) arr.getJSONObject(i).get("subcategory");
			//System.out.println(" sub category" + sub);
			
			if ( sub.equals(userSelectedCategory)){
				categoryId = (String) arr.getJSONObject(i).get("subcategoryID");
				System.out.println(" id is " + arr.getJSONObject(i).get("subcategoryID"));
				
			}
		}
		
		

		return Response.status(200).build();

		
		
	}
}
