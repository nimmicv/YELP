package com.kaizen.yelp.ui.resources;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.domain.Search;
import com.kaizen.yelp.repository.UserRepository;
import com.kaizen.yelp.ui.views.BusinessView;
import com.kaizen.yelp.ui.views.UserView;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

@Path("/kaizen/{username}/home")
@Produces(MediaType.TEXT_HTML)
public class UserResource {
	private Mongo mongo;
	private UserRepository userRepository;
	public UserResource(Mongo mongo, UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.mongo = mongo;
		this.userRepository = userRepository;
	}

	@GET
	public UserView getUser(@PathParam("username") String username) {
		ArrayList<Search> searchList = new ArrayList<Search>();
		
		searchList = userRepository.getSearch();
		for (int i = 0; i < searchList.size(); i++) {
			System.out.println(searchList.get(i).getName());
		}
		
		return new UserView(username, searchList);
	}
	
	@POST
	public void searchBiz(@FormParam("search_business") String search_business, @FormParam("search_city") String search_city, @FormParam("search_day") String search_day, @FormParam("search_startTime") String search_startTime, @FormParam("search_endTime") String search_endTime){
		
		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("business");
		System.out.println("****"+search_business+search_city+search_day+search_startTime+search_endTime);
		BasicDBObject searchQuery = new BasicDBObject("city", search_city);
		searchQuery.append("open", true);

		searchQuery.append("hours." + search_day + ".open",
				new BasicDBObject("$lte", search_startTime)).append(
				"hours." + search_day + ".close", new BasicDBObject("$gt", search_endTime));
		
		searchQuery.append("categories", search_business);
		
		DBCursor myCol = coll.find(searchQuery);
		myCol.limit(15);
		
		ArrayList<Search> searchList = new ArrayList<Search>();
		
		try { while(myCol.hasNext()) {
			 
			BasicDBObject obj= (BasicDBObject) myCol.next();
//			JSONObject json=new JSONObject(obj.toString());
//			JSONArray arr=json.getJSONArray("categories");
//			for (int i = 0; i < arr.length(); i++) {
//				
//				//System.out.println(arr.getString(i));
//				if(arr.getString(i).equals(search_business))
//				{
//					break;
//				}
									
			String business_id = obj.getString("business_id");
			String name = obj.getString("name");
			String full_address = obj.getString("full_address");
			float stars = Float.parseFloat(obj.getString("stars"));
			
			Search search = new Search();
			search.setBusiness_id(business_id);
			search.setName(name);
			search.setFull_address(full_address);
			search.setStars(stars);
			
			searchList.add(search);
			userRepository.saveSearch(searchList);
		
	}
}	finally {
				myCol.close();
		 	}		
	
	}
	

}