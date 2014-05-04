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

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


@Path("/kaizen/coupons")
@Produces(MediaType.TEXT_HTML)
public class CouponResource {
	private final String USER_AGENT = "Mozilla/5.0";

@GET

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

}
