package com.kaizen.yelp.ui.resources;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kaizen.yelp.domain.Review;
import com.kaizen.yelp.domain.Search;
import com.kaizen.yelp.domain.UserLogin;
import com.kaizen.yelp.repository.UserRepository;
import com.kaizen.yelp.ui.views.ReviewView;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

@Path("/kaizen/{username}/review/{business_id}")
@Produces(MediaType.TEXT_HTML)
public class ReviewResource {
	private Mongo mongo;
	private UserRepository userRepository;
	public ReviewResource(Mongo mongo, UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.mongo = mongo;
		this.userRepository = userRepository;
	}

	@GET
	public ReviewView getReview(@PathParam("username") String username) {
		
		return new ReviewView(username);
	}
	
	@POST
	@Path("/signout")
	public void signout() {
		
		ArrayList<Search> searchRef;
		searchRef = userRepository.getSearch();
		searchRef.clear();
		userRepository.saveSearch(searchRef);
	}
	
	@POST
	@Path("/")
	public void postReview(@PathParam("username") String username, @PathParam("business_id") String business_id, @FormParam("rating") String rating, @FormParam("review_content") String review_content, @FormParam("block") String block) {
		
		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("review");
		
		 Review review = new Review();
		
		review.setBusiness_id(business_id);
		review.setUser_id(username);
		review.setRating(Float.parseFloat(rating));
		review.setReview_content(review_content);
		review.setBlock(block);
	
		if(!(rating.isEmpty()&&review_content.isEmpty()&&block.isEmpty()))
		{
			userRepository.saveReview(review);
		}
		
	}
	
}