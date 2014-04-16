package com.kaizen.yelp.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.domain.HelloMessage;
import com.kaizen.yelp.domain.Review;
import com.kaizen.yelp.domain.User;
import com.kaizen.yelp.domain.UserLogin;
import com.kaizen.yelp.dto.ReviewDto;
import com.kaizen.yelp.ui.views.BusinessView;
import com.kaizen.yelp.ui.views.HomeView;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

	private Mongo mongo;

	public HomeResource(Mongo mongo) {
		// TODO Auto-generated constructor stub
		this.mongo = mongo;
	}
	@GET
	public HomeView getIndex() {
		return new HomeView();
	}

	@GET
	@Path("/login")
	public HomeView getLogin() {
		return new HomeView(new HelloMessage());
	}
	
	@GET
	@Path("/business/{business}/{user}")
	public BusinessView getBusiness(@PathParam("business") String business,@PathParam("user") String user) {
		User userData = new User();
		Business bus = new Business();
		return new BusinessView(bus,userData);
	}

	@GET
	@Path("/home/{username}")
	public HomeView getHome(@PathParam("username") String username) {
		// Get user details viz id , email, pwd fom mongodb and create userLogin
		// Object
		UserLogin user = new UserLogin();
		User userData = new User();
		String password = null;
		String email = null;
		String userId=null;
		String yelping_since=null;
		 String review_count=null;
		 String fans=null;
		 String average_stars = null;
		String reviewText=null;
		
		DB db = mongo.getDB("273project");
		
		DBCollection coll = db.getCollection("userInfo");
		DBCollection collUser=db.getCollection("user");
		DBCollection collReview=db.getCollection("review");
		
		DBObject userInfo = new BasicDBObject("username", username);
		DBCursor cursor = coll.find(userInfo);
		
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			password = (String) o.get("password");
			email = (String) o.get("email");

		}
//		System.out.println("password" + password);
//		System.out.println("email" + email);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
        userData.setName(username);
       userData.setPassword(password);
		userData.setEmail(email);
		BasicDBObject toFindUserId = new BasicDBObject("name",username);
		DBCursor cursor1 = collUser.find(toFindUserId);
		while(cursor1.hasNext())
		{
			//System.out.println(cursor1.next());
			BasicDBObject ob = (BasicDBObject) cursor1.next();
			userId =  ob.getString("user_id");
			yelping_since= ob.getString("yelping_since");
			review_count= ob.getString("review_count");
			fans=(String) ob.getString("fans");
			average_stars=ob.getString("average_stars");
			
			
		}
		userData.setUserId(userId);
		userData.setAverageStars(average_stars);
		userData.setFans(fans);
		userData.setYelpingSince(yelping_since);
		userData.setReviewCount(review_count);
		
		BasicDBObject toFindReviews = new BasicDBObject("user_id",userId);
		DBCursor cursor2 = collReview.find(toFindReviews);
        ReviewDto reviews = new ReviewDto();
		while(cursor2.hasNext())
		{
			
			BasicDBObject reviewObj = (BasicDBObject) cursor2.next();
			String review_id = reviewObj.getString("review_id");
            String business_id = reviewObj.getString("business_id");
            String user_id = reviewObj.getString("user_id");
            String stars = reviewObj.getString("stars");
            String date = reviewObj.getString("date");
            String text = reviewObj.getString("text");

            Review review = new Review();
            review.setReviewId(review_id);
            review.setBusinessId(business_id);
            review.setUserId(user_id);
            review.setStars(stars);
            review.setDate(date);
            review.setText(text);
            reviews.addReview(review);
		}
				return new HomeView(user,userData,reviews.getReviews());
	}
}
