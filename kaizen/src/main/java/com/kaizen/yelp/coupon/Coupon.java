package com.kaizen.yelp.coupon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import com.kaizen.yelp.domain.CouponInfo;



public class Coupon {



	public String getSubCategoryId (StringBuilder content , String userSelectedCategory){
		//String userSelectedCategory = "Moroccan";
		
		
	String subcategoryIdsFromCouponAPI = "";


	// If the category selected by the user is one of the main category of 8 coupons
		JSONArray arr = new JSONArray(content.toString());
		for (int i =0 ; i < arr.length() ; i++){
			//System.out.println(" array deatils :" + arr.getJSONObject(i).get("category"));

			String mainCategory = (String)arr.getJSONObject(i).get("category");
			System.out.println("USERCATEGORY "+userSelectedCategory);
			System.out.println("Main category "+mainCategory);
			if(mainCategory.trim().contains(userSelectedCategory.trim())){
				System.out.println("inside if");
				String categoryId = (String) arr.getJSONObject(i).get("categoryID");
				
				
				String subcategoryId = (String) arr.getJSONObject(i).get("subcategoryID");
				if(categoryId.equalsIgnoreCase("1")){
					// The category is restaurants
					// By default giving the subcategory as Chinese restuarent
					subcategoryId = "12";
					
				}
				subcategoryIdsFromCouponAPI = categoryId + "***" + subcategoryId;
				System.out.println(" main category if loop "+ subcategoryIdsFromCouponAPI);
				return subcategoryIdsFromCouponAPI;

			}

			// If the category selected by the user is one of the sub category of 8 coupons

			String sub = (String) arr.getJSONObject(i).get("subcategory");
			//System.out.println(" sub category" + sub);

			if ( sub.contains(userSelectedCategory)){

				String categoryId = (String) arr.getJSONObject(i).get("categoryID");
				String subCategoryID = (String) arr.getJSONObject(i).get("subcategoryID");


				System.out.println(" sub id is " + arr.getJSONObject(i).get("subcategoryID"));
				System.out.println("id is "+ arr.getJSONObject(i).get("categoryID"));

				subcategoryIdsFromCouponAPI = categoryId + "***" + subCategoryID;
				System.out.println(" category and subcategory id : " + subcategoryIdsFromCouponAPI);
			}
		}

		return subcategoryIdsFromCouponAPI;
	}





	public String newQuery(String zip , String categoryIdFromCouponAPI){
		String baseUrl = "http://api.8coupons.com/v1/getdeals?key=8871a60a47c12653695c681668f16e7a525d6bffc8c4af98c18a92e6e35578696c62b8cea9bf4f32c444ce507b243c39";

		String include = "&mileradius=20&limit=5&orderby=radius";

		//String zip = "85233";
		String zipQuery = "&zip="+zip;


		String[] ids = categoryIdFromCouponAPI.split("\\*\\*\\*");
		String categoryQuery = "&categoryID="+ ids[0];

		System.out.println(" category query"+ categoryQuery);

		String subcategoryQuery = "&subcategoryID="+ids[1];

		System.out.println(" sub category query"+ subcategoryQuery);
		String fullQuery = baseUrl + zipQuery + include+ categoryQuery +subcategoryQuery;
		
		/*if ( ids[1].equals("0")){
			 fullQuery = fullQuery+ categoryQuery +"&subcategoryID=1" ;
		}
		else{
		 fullQuery = fullQuery+ categoryQuery + subcategoryQuery;
		}*/

		System.out.println(" full query" + fullQuery);

		return fullQuery;

	}


	public StringBuilder getFromCouponApi(String url) throws ClientProtocolException, IOException{

		HttpClient client1 = new DefaultHttpClient();


		HttpGet request1 = new HttpGet(url);
		org.apache.http.HttpResponse response = client1.execute(request1);

		System.out.println(" response" + response.getEntity().getContent().toString());

		BufferedReader reader = new BufferedReader  (new InputStreamReader(( response).getEntity().getContent()));
		StringBuilder allContents = new StringBuilder();

		String line = null;

		while ((line = reader.readLine()) != null) {
			allContents.append(line);
		}
		System.out.println(" string value :"+ allContents.toString());
		return allContents;
	}

	public ArrayList<CouponInfo> getCouponDetails( StringBuilder content){

		JSONArray arr2 = new JSONArray(content.toString());

		ArrayList<CouponInfo> couponDetails = new ArrayList<CouponInfo>();
		
		for (int i =0 ; i < arr2.length(); i++){

			
			String dealName = (String) arr2.getJSONObject(i).get("dealTitle");
			String dealSource = (String) arr2.getJSONObject(i).get("dealSource");
			String dealImage = (String) arr2.getJSONObject(i).get("showImage");
			String dealLink = (String) arr2.getJSONObject(i).get("storeURL");
			
			couponDetails.add(new CouponInfo(dealName, dealSource, dealImage, dealLink));
			//System.out.println(" deal title :" +  arr2.getJSONObject(i).get("showImage"));
			//System.out.println(" deal source :" +arr2.getJSONObject(i).get("storeURL"));


		}

		return couponDetails;
	}


}
