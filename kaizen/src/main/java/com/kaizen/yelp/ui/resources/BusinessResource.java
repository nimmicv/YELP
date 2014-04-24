package com.kaizen.yelp.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.repository.UserRepository;
import com.kaizen.yelp.ui.views.BusinessView;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

@Path("/kaizen/{username}/{business_id}")
@Produces(MediaType.TEXT_HTML)
public class BusinessResource {
	private Mongo mongo;
	private UserRepository userRepository;
	public BusinessResource(Mongo mongo, UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.mongo = mongo;
		this.userRepository = userRepository;
	}

	@GET
	public BusinessView getBusiness(@PathParam("username") String username, @PathParam("business_id") String business_id) {
		
		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("business");
		
		BasicDBObject searchQuery = new BasicDBObject();

		if (business_id != null) {
			searchQuery.append("business_id", business_id);
		}
		
		DBCursor myCol = coll.find(searchQuery);
		
		Business business = new Business();
		
		 try { while(myCol.hasNext()) {
		 
		  BasicDBObject businessObj = (BasicDBObject) myCol.next();
		  String b_id = businessObj.getString("business_id");
		  String name = businessObj.getString("name");
		  String full_address = businessObj.getString("full_address");
		  String categories = businessObj.getString("categories");
		  float review_count = Float.parseFloat(businessObj.getString("review_count"));
		  float stars = Float.parseFloat(businessObj.getString("stars"));
		  
		  
		  
		  business.setBusiness_id(b_id);
		  business.setName(name);
		  business.setFull_address(full_address);
		  business.setCategories(categories);
		  business.setReview_count(review_count);
		  business.setStars(stars);
		   
		  } } finally { myCol.close(); }
		
		return new BusinessView(username, business);
	}
	
	
}