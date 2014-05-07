package com.kaizen.yelp.ui.resources;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
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
import com.kaizen.yelp.coupon.Coupon;
import com.kaizen.yelp.domain.CouponInfo;
import com.kaizen.yelp.ui.views.CouponView;
import com.kaizen.yelp.ui.views.NoCouponView;
import com.kaizen.yelp.ui.views.UserView;
import com.mongodb.Mongo;

//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;


@Path("/kaizen/coupons/{username}/{category}/{zipcode}")
@Produces(MediaType.TEXT_HTML)

public class CouponResource {
	private Mongo mongo;

	public CouponResource(Mongo mongo) {
		this.mongo = mongo;
	}
	//private final String USER_AGENT = "Mozilla/5.0";

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


//
//	@GET
//	@Path("/test")
//
//	public Response getTest() throws  Exception{
//		System.out.println(" hello world");
//
//
//		String baseurl = "http://api.8coupons.com/v1/getsubcategory";
//
//		Coupon coupon = new Coupon();
//		StringBuilder contentsOfURL = coupon.getFromCouponApi(baseurl);
//
//
//
//		/*HttpClient client1 = new DefaultHttpClient();
//
//
//		HttpGet request1 = new HttpGet("http://api.8coupons.com/v1/getsubcategory");
//		org.apache.http.HttpResponse response = client1.execute(request1);
//
//
//
//		System.out.println(" response" + response.getEntity().getContent().toString());
//
//		BufferedReader reader = new BufferedReader  (new InputStreamReader(( response).getEntity().getContent()));
//		StringBuilder allContents = new StringBuilder();
//
//		String line = null;
//
//		while ((line = reader.readLine()) != null) {
//			allContents.append(line);
//		}
//		System.out.println(" string value :"+ allContents.toString());*/
//
//
//		//allContents.toString().replaceAll("\\s", "");
//		//		
//		//		System.out.println(" new output value:" + allContents.toString().replaceAll("\\s", ""));
//		//		//allContents.replace(arg0, arg1, arg2)
//		//		
//		//		String input = allContents.toString().replaceAll("\\s", "");
//		//		InputStream is = new ByteArrayInputStream(input.getBytes());
//		//		 
//		//		// read it with BufferedReader
//		//		BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//		//		JSONTokener tokener = new JSONTokener(br);
//		//		JSONArray finalResult = new JSONArray(tokener);
//		//		
//
//		String userSelectedCategory = "Moroccan";
//
//
//
//		/*String categoryIdFromCouponAPI = "";
//
//		JSONArray arr = new JSONArray(allContents.toString());
//		for (int i =0 ; i < arr.length() ; i++){
//			//System.out.println(" array deatils :" + arr.getJSONObject(i).get("category"));
//
//			String sub = (String) arr.getJSONObject(i).get("subcategory");
//			//System.out.println(" sub category" + sub);
//
//			if ( sub.equals(userSelectedCategory)){
//				categoryIdFromCouponAPI = (String) arr.getJSONObject(i).get("subcategoryID");
//				System.out.println(" id is " + arr.getJSONObject(i).get("subcategoryID"));
//
//			}
//		}
//
//
//		String baseUrl = "http://api.8coupons.com/v1/getdeals?key=8871a60a47c12653695c681668f16e7a525d6bffc8c4af98c18a92e6e35578696c62b8cea9bf4f32c444ce507b243c39";
//
//		String include = "&mileradius=20&limit=5&orderby=radius";
//
//		String zip = "85233";
//		String zipQuery = "&zip="+zip;
//		String categoryQuery = "&subcategoryid="+categoryIdFromCouponAPI;
//
//		String fullQuery = baseUrl + zipQuery + include + categoryQuery;
//
//		System.out.println(" full query" + fullQuery);
//
//
//
//		HttpClient client2 = new DefaultHttpClient();
//
//
//		HttpGet request2 = new HttpGet(fullQuery);
//		org.apache.http.HttpResponse response2 = client1.execute(request2);
//
//
//
//		System.out.println(" response" + response2.getEntity().getContent().toString());
//
//		BufferedReader reader2 = new BufferedReader  (new InputStreamReader(( response2).getEntity().getContent()));
//		StringBuilder allContents2 = new StringBuilder();
//
//		String line2 = null;
//
//		while ((line2 = reader2.readLine()) != null) {
//			allContents2.append(line2);
//		}
//
//		JSONArray arr2 = new JSONArray(allContents2.toString());
//
//
//		for (int i =0 ; i < arr2.length(); i++){
//
//			System.out.println(" deal title :" +  arr2.getJSONObject(i).get("dealTitle"));
//			System.out.println(" deal source :" +arr2.getJSONObject(i).get("dealSource"));
//
//		}*/
//
//		//return Response.status(200).build();
//
//
//		//Coupon coupon = new Coupon();
//		String categoryIdFromCoupon = coupon.getSubCategoryId(contentsOfURL, userSelectedCategory);
//
//		String zipcode = "85233" ;
//		String newQuery = coupon.newQuery(zipcode, categoryIdFromCoupon);
//
//
//		StringBuilder newQueryContents = coupon.getFromCouponApi(newQuery);
//
//		/*HttpClient client2 = new DefaultHttpClient();
//
//
//		HttpGet request2 = new HttpGet(newQuery);
//		org.apache.http.HttpResponse response2 = client1.execute(request2);
//
//
//
//		System.out.println(" response" + response2.getEntity().getContent().toString());
//
//		BufferedReader reader2 = new BufferedReader  (new InputStreamReader(( response2).getEntity().getContent()));
//		StringBuilder allContents2 = new StringBuilder();
//
//		String line2 = null;
//
//		while ((line2 = reader2.readLine()) != null) {
//			allContents2.append(line2);
//		}*/
//
//		ArrayList<CouponInfo> couponlist = coupon.getCouponDetails(newQueryContents);
//
//		System.out.println(" coupon list" +couponlist.toString());
//
//		for (int i = 0 ; i < couponlist.size() ; i++){
//
//			System.out.println(" list: "+couponlist.get(i).toString());
//
//		}
//
//		return Response.status(200).build();
//
//
//
//
//	}


	@GET

	public CouponView getCouponDetails(@PathParam("username") String username, @PathParam("category") String userSelectedCategory1, @PathParam("zipcode") String zipcode) throws  Exception{
		System.out.println(" hello coupon details");


		String baseurl = "http://api.8coupons.com/v1/getsubcategory";

		Coupon coupon = new Coupon();
		StringBuilder contentsOfURL = coupon.getFromCouponApi(baseurl);
		String userSelectedCategory = "jhgdkjhgfh";
		//String userSelectedCategory = "Moroccan123";
		String categoryIdFromCoupon = coupon.getSubCategoryId(contentsOfURL, userSelectedCategory);
		ArrayList<CouponInfo> couponlist = null;
		
		try {
			
			
			
			//String zipcode = "85233" ;
			String newQuery = coupon.newQuery(zipcode, categoryIdFromCoupon);


			StringBuilder newQueryContents = coupon.getFromCouponApi(newQuery);
			 couponlist = coupon.getCouponDetails(newQueryContents);

			System.out.println(" coupon list" +couponlist.toString());

			for (int i = 0 ; i < couponlist.size() ; i++){

				System.out.println(" list: "+couponlist.get(i).toString());

			}
			
		} catch (ArrayIndexOutOfBoundsException ex) {
			
		System.out.println(" There are no coupons in this category");
		return new CouponView(username);
		}
		
		catch (Exception e) {
			System.out.println(" There is some other problem");
		}

		return new CouponView(username,couponlist);
	}
}
