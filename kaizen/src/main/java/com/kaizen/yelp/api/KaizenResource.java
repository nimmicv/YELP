package com.kaizen.yelp.api;

import java.net.UnknownHostException;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;

import com.google.common.base.Optional;
import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.domain.Review;
import com.kaizen.yelp.domain.User;
import com.kaizen.yelp.domain.Tip;
import com.kaizen.yelp.domain.HelloMessage;
import com.kaizen.yelp.domain.User;
import com.kaizen.yelp.domain.UserLogin;
import com.kaizen.yelp.dto.BusinessDto;
import com.kaizen.yelp.dto.ReviewDto;
import com.kaizen.yelp.dto.UserDto;
import com.kaizen.yelp.dto.TipDto;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
//import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.yammer.metrics.annotation.Timed;

@Path("/kaizen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KaizenResource {

	private Mongo mongo;
	private DBCollection coll;

	public KaizenResource(Mongo mongo, DBCollection coll)
			throws UnknownHostException, MongoException {
		this.mongo = mongo;
		this.coll = coll;

	}

	// @Timed(name = "get-requests")
	// Give json class name as parameter eg:Book book
	// Jersey notations
	/*
	 * public HelloMessage get(@QueryParam("name") Optional<String> name) {
	 * 
	 * 
	 * HelloMessage hello = new HelloMessage(); hello.setMessage("Hello" + (
	 * (name.isPresent()) ? " " + name.get() : "")); return hello; }
	 * 
	 * @GET //@Path("/query")
	 * 
	 * @Timed(name = "get-query") public String getQuery(@Context UriInfo
	 * uriInfo) { MultivaluedMap<String, String> queryParams =
	 * uriInfo.getQueryParameters(); String match=""; String state =
	 * queryParams.getFirst("state"); String city =
	 * queryParams.getFirst("city"); String address =
	 * queryParams.getFirst("address"); String zipcode =
	 * queryParams.getFirst("zipcode"); if (state !=
	 * null){match="State is "+state+" "+match; } if (city !=
	 * null){match="City is "+city+" "+match; } if (address !=
	 * null){match="Address is "+address+" "+match;} if (zipcode !=
	 * null){match="Zipcode is "+zipcode+" "+match; } if (match ==
	 * ""){match="nothing matched"; } return match; }
	 * 
	 * @GET
	 * 
	 * @Path("/{city}")
	 * 
	 * @Timed(name = "get-city") public BusinessDto getCity(@PathParam("city")
	 * String city) { // public DBCursor getCity(@PathParam("city") String city)
	 * { //return db.business.find().limit(5); //Mongo mongoClient = new
	 * Mongo(); DB db = mongo.getDB("273project"); DBCollection coll =
	 * db.getCollection("business"); BasicDBObject query = new
	 * BasicDBObject("city", city); DBCursor myCol = coll.find(query);
	 * myCol.limit(15);
	 * 
	 * BusinessDto businesses = new BusinessDto();
	 * 
	 * try { while(myCol.hasNext()) {
	 * 
	 * BasicDBObject businessObj = (BasicDBObject) myCol.next(); String
	 * business_id = businessObj.getString("business_id"); String categories =
	 * businessObj.getString("categories"); String full_address =
	 * businessObj.getString("full_address"); String hours =
	 * businessObj.getString("hours");
	 * 
	 * Business business = new Business(); business.setBusinessId(business_id);
	 * business.setCategories(categories);
	 * business.setFullAddress(full_address); business.setHours(hours);
	 * 
	 * businesses.addBusiness(business);
	 * 
	 * } } finally { myCol.close(); }
	 * 
	 * return businesses; }
	 */

    //@Timed(name = "get-requests")
   //Give json class name as parameter eg:Book book 
    //Jersey notations
/*	
    public HelloMessage get(@QueryParam("name") Optional<String> name) {
		
	
		HelloMessage hello = new HelloMessage();
        hello.setMessage("Hello" + ( (name.isPresent()) ? " " + name.get() : ""));
       return hello;
    }

    @GET
    //@Path("/query")
    @Timed(name = "get-query")
     public String getQuery(@Context UriInfo uriInfo) {
	MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
	String match="";
	String state = queryParams.getFirst("state");
	String city = queryParams.getFirst("city");
	String address = queryParams.getFirst("address");
	String zipcode = queryParams.getFirst("zipcode");
	if (state != null){match="State is "+state+" "+match; }
	if (city != null){match="City is "+city+" "+match; }
	if (address != null){match="Address is "+address+" "+match;}
	if (zipcode != null){match="Zipcode is "+zipcode+" "+match; }
	if (match == ""){match="nothing matched"; }
	return match;
    }
 
    @GET
    @Path("/{city}")
    @Timed(name = "get-city")
    	public BusinessDto getCity(@PathParam("city") String city) {
    //	public DBCursor getCity(@PathParam("city") String city) {
        //return db.business.find().limit(5);
        //Mongo mongoClient = new Mongo();
	DB db = mongo.getDB("273project");
	DBCollection coll = db.getCollection("business");
	BasicDBObject query = new BasicDBObject("city", city);
	DBCursor myCol = coll.find(query);
	myCol.limit(15);
	
	BusinessDto businesses = new BusinessDto();

	try {
			while(myCol.hasNext()) { 
								
			        BasicDBObject businessObj = (BasicDBObject) myCol.next();
			        String business_id = businessObj.getString("business_id");
			        String categories = businessObj.getString("categories");
			        String full_address = businessObj.getString("full_address");
			        String hours = businessObj.getString("hours");
			       
			        Business business = new Business();
			        business.setBusinessId(business_id);
			        business.setCategories(categories);
			        business.setFullAddress(full_address);
			        business.setHours(hours);
			 
			        businesses.addBusiness(business);
	
	   }
	} finally { myCol.close(); }
	
    return businesses;
}

 */
	@POST
	@Path("/validate")
	public boolean validateUser(UserLogin user) {
		String username = user.getUsername();
		String password = user.getPassword();
		boolean returnVal = false;
		int flag = 0;
		DB db = mongo.getDB("273project");
		DBCollection users = db.getCollection("userInfo");
		DBObject userData = new BasicDBObject("username", username).append(
				"password", password);
		DBCursor cursor = users.find(userData);
		while (cursor.hasNext()) {
			flag = 1;
			break;
		
		}

		if(flag == 0)
			returnVal = false;
		else if (flag ==1)
			returnVal = true;
		return returnVal;
	}

	@POST
	@Path("/insertReview")
	public boolean validateUser(Review review) {
		
		DB db = mongo.getDB("273project");
		DBCollection reviewColl = db.getCollection("review");
		BasicDBObject revObj = new BasicDBObject("review",review);
		reviewColl.insert(revObj);
		return true;
	
	}

    @GET
    @Path("/business")
    @Timed(name = "get-business")
     public BusinessDto getBusiness(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        DB db = mongo.getDB("273project");
        DBCollection coll = db.getCollection("business");
        String businessID = queryParams.getFirst("business_id");
        String name = queryParams.getFirst("name");
        String state = queryParams.getFirst("state");
        String city = queryParams.getFirst("city");
        String address = queryParams.getFirst("address");
        String zipcode = queryParams.getFirst("zipcode");
        String category = queryParams.getFirst("categories");
        System.out.println(name);

        BasicDBObject searchQuery = new BasicDBObject();

        if (businessID != null){ searchQuery.append("business_id", businessID); }
        else {
		if (name != null){ searchQuery.append("name", name); }
		if (state != null){ searchQuery.append("state", state); }
		if (city != null){ searchQuery.append("city", city); }
		if (zipcode != null){ searchQuery.append("zipcode", zipcode); }
		if (category != null){ searchQuery.append("categories", category); }
		if (address != null){ searchQuery.append("address", address); }
        }

        DBCursor busColl = coll.find(searchQuery);
        busColl.limit(20);

        BusinessDto businesses = new BusinessDto();

        try {
                        while(busColl.hasNext()) {
                                BasicDBObject businessObj = (BasicDBObject) busColl.next();
                                String business_id = businessObj.getString("business_id");
				               String names = businessObj.getString("name");
                                String categories = businessObj.getString("categories");
                                String full_address = businessObj.getString("full_address");
                                String hours = businessObj.getString("hours");

                                Business business = new Business();
                                business.setBusinessId(business_id);
				business.setName(name);
                                business.setCategories(categories);
                                business.setFullAddress(full_address);
                                business.setHours(hours);

                                businesses.addBusiness(business);

           }
        } finally { busColl.close(); }

    return businesses;
}

    @GET
    @Timed(name = "get-review")
    @Path("/review")
     public ReviewDto getReview(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        DB db = mongo.getDB("273project");
        DBCollection coll = db.getCollection("review");

        String reviewID = queryParams.getFirst("review_id");
        String businessID = queryParams.getFirst("business_id");
        String userID = queryParams.getFirst("user_id");

        BasicDBObject searchQuery = new BasicDBObject();
        
        if (reviewID != null){ searchQuery.append("review_id", reviewID); }
        else {        
                if (businessID != null){ searchQuery.append("business_id", businessID); }	
	        if (userID != null){ searchQuery.append("user_id", userID); }
        }

        DBCursor revCol = coll.find(searchQuery);
        revCol.limit(20);

        ReviewDto reviews = new ReviewDto();

        try {
                        while(revCol.hasNext()) {
                                BasicDBObject reviewObj = (BasicDBObject) revCol.next();

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
        } finally { revCol.close(); }

    return reviews;
}

    @GET
    @Timed(name = "get-user")
    @Path("/user")
     public UserDto getUser(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        DB db = mongo.getDB("273project");
        DBCollection coll = db.getCollection("user");

        String businessID = queryParams.getFirst("business_id");
        String userID = queryParams.getFirst("user_id");

        BasicDBObject searchQuery = new BasicDBObject();

	if (userID != null){ searchQuery.append("user_id", userID); }

        DBCursor userCol = coll.find(searchQuery);
        userCol.limit(20);

        UserDto users = new UserDto();

        try {
                        while(userCol.hasNext()) {
                                BasicDBObject userObj = (BasicDBObject) userCol.next();

                                String user_id = userObj.getString("user_id");
                                String yelping_since = userObj.getString("yelping_since");
                                String review_count = userObj.getString("review_count");
                                String name = userObj.getString("name");
                                String fans = userObj.getString("fans");
                                String average_stars = userObj.getString("average_stars");

                                User user = new User();
                                user.setUserId(user_id);
                                user.setYelpingSince(yelping_since);
                                user.setReviewCount(review_count);
                                user.setName(name);
                                user.setFans(fans);
                                user.setAverageStars(average_stars);

                                users.addUser(user);

           }
        } finally { userCol.close(); }

    return users;
}


    @GET
    @Timed(name = "get-tip")
    @Path("/tip")
     public TipDto getTip(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        DB db = mongo.getDB("273project");
        DBCollection coll = db.getCollection("tip");

        String businessID = queryParams.getFirst("business_id");
        String userID = queryParams.getFirst("user_id");

        BasicDBObject searchQuery = new BasicDBObject();

	if (userID != null){ searchQuery.append("user_id", userID); }
	if (businessID != null){ searchQuery.append("business_id", businessID); }
  

        DBCursor tipCol = coll.find(searchQuery);
        tipCol.limit(20);

        TipDto tips = new TipDto();

        try {
                        while(tipCol.hasNext()) {
                                BasicDBObject userObj = (BasicDBObject) tipCol.next();

                                String user_id = userObj.getString("user_id");
                                String business_id = userObj.getString("business_id");
                                String likes = userObj.getString("likes");
                                String date = userObj.getString("date");
                                String text = userObj.getString("text");

                                Tip tip = new Tip();
                                tip.setUserId(user_id);
                                tip.setBusinessId(business_id);
                                tip.setLikes(likes);
                                tip.setDate(date);
                                tip.setText(text);

                                tips.addTip(tip);

           }
        } finally { tipCol.close(); }

    return tips;
}


	@GET
	@Path("/{city}/{categories}")
	@Timed(name = "get-categories")
	public BusinessDto getCategory(@PathParam("city") String city, @PathParam("categories") String category) {

		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("business");
		BasicDBObject searchQuery = new BasicDBObject("city", city);
		searchQuery.append("categories", category);

		DBCursor myCol = coll.find(searchQuery);
		myCol.limit(15);

		BusinessDto businesses = new BusinessDto();

		try {
				while(myCol.hasNext()) { 

				        BasicDBObject businessObj = (BasicDBObject) myCol.next();
				        String business_id = businessObj.getString("business_id");
				        String categories = businessObj.getString("categories");
				        String full_address = businessObj.getString("full_address");
				        String hours = businessObj.getString("hours");

				        Business business = new Business();
				        business.setBusinessId(business_id);
				        business.setCategories(categories);
				        business.setFullAddress(full_address);
				        business.setHours(hours);

				        businesses.addBusiness(business);

		   }
		} finally { myCol.close(); }

	    return businesses;
	}



	@GET
	@Path("/{city}/{categories}/{hoursDay}/{time1}/{time2}")
	@Timed(name = "get-timebased")
	public BusinessDto getTimeBased(@PathParam("city") String city,@PathParam("categories") String category , @PathParam("hoursDay") String day ,
			@PathParam("time1") String startTime , @PathParam("time2") String endTime  ) {

		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("business");


		BasicDBObject searchQuery = new BasicDBObject("categories", category);
		searchQuery.append("city", city);
		searchQuery.append("open", true);

		searchQuery.append("hours."+day+".open", new BasicDBObject("$lte", startTime)).append("hours."+day+".close", new BasicDBObject("$gt", endTime));
		DBCursor myCol = coll.find(searchQuery);
		myCol.limit(15);

		BusinessDto businesses = new BusinessDto();

		try {
				while(myCol.hasNext()) { 

				        BasicDBObject businessObj = (BasicDBObject) myCol.next();
				        String business_id = businessObj.getString("business_id");
				        String categories = businessObj.getString("categories");
				        String full_address = businessObj.getString("full_address");
				        String hours = businessObj.getString("hours");

				        Business business = new Business();
				        business.setBusinessId(business_id);
				        business.setCategories(categories);
				        business.setFullAddress(full_address);
				        business.setHours(hours);

				        businesses.addBusiness(business);

		   }
		} finally { myCol.close(); }

	    return businesses;
	}


	@GET
	@Path("/{city}/{categories}/{when}")
	@Timed(name = "get-timebased")
	public BusinessDto getCurrentTime(@PathParam("city") String city,@PathParam("categories") String category , @PathParam("when") String when  ) {

		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("business");

		Calendar now = Calendar.getInstance();
		System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-"
				+ now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR) + "-"+ now.getTime().getHours() + "-"+ now.getTime().getMinutes());

		String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
				"Friday", "Saturday" };
		// Day_OF_WEEK starts from 1 while array index starts from 0
		String day = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
		int hours = now.getTime().getHours();
		//int hours = 7 ;
		int minutes = now.getTime().getMinutes();
		System.out.println("Current day is : " + day + "hours and minutes" + hours + " " +minutes);

		String startTime;

		if (hours <10 ){
			startTime = "0"+hours + ":00";
		}
		else{
			startTime = hours + ":00";
		}

		BasicDBObject searchQuery = new BasicDBObject("categories", category);
		searchQuery.append("city", city);
		searchQuery.append("open", true);
		searchQuery.append("hours."+day+".open", new BasicDBObject("$lt", startTime)).append("hours."+day+".close", new BasicDBObject("$gt", startTime));

		DBCursor cursor = coll.find(searchQuery);
		cursor.limit(10);

		BusinessDto businesses = new BusinessDto();

		try {
				while(cursor.hasNext()) { 

				        BasicDBObject businessObj = (BasicDBObject) cursor.next();
				        String business_id = businessObj.getString("business_id");
				        String categories = businessObj.getString("categories");
				        String full_address = businessObj.getString("full_address");
				        String hours_display = businessObj.getString("hours");

				        Business business = new Business();
				        business.setBusinessId(business_id);
				        business.setCategories(categories);
				        business.setFullAddress(full_address);
				        business.setHours(hours_display);

				        businesses.addBusiness(business);

		   }
		} finally { cursor.close(); }

	    return businesses;
	}
	// @GET
	// @Timed(name = "get-business")
	// public BusinessDto getBusiness(@Context UriInfo uriInfo) {
	// MultivaluedMap<String, String> queryParams =
	// uriInfo.getQueryParameters();
	// DB db = mongo.getDB("273project");
	// DBCollection bus = db.getCollection("business");
	// DBCollection rev = db.getCollection("review");
	// DBCollection usr = db.getCollection("user");
	//
	// String businessID = queryParams.getFirst("business_id");
	// String userID = queryParams.getFirst("user_id");
	// String reviewID = queryParams.getFirst("review_id");
	//
	// String state = queryParams.getFirst("state");
	// String city = queryParams.getFirst("city");
	// String address = queryParams.getFirst("address");
	// String zipcode = queryParams.getFirst("zipcode");
	// String category = queryParams.getFirst("categories");
	// BasicDBObject searchQuery = new BasicDBObject();
	//
	// if (state != null){ searchQuery.append("state", state); }
	//
	// if (city != null){ searchQuery.append("city", city); }
	//
	// if (zipcode != null){ searchQuery.append("zipcode", zipcode); }
	//
	// if (category != null){ searchQuery.append("categories", category); }
	//
	// if (address != null){ searchQuery.append("address", address); }
	//
	// if (businessID != null){ searchQuery.append("business_id", businessID); }
	//
	// if (userID != null){ searchQuery.append("user_id", userID); }
	//
	// if (reviewID != null){ searchQuery.append("review_id", reviewID); }
	//
	//
	// DBCursor busCol = bus.find(searchQuery);
	// busCol.limit(20);
	//
	// BusinessDto businesses = new BusinessDto();
	//
	// try {
	// while(busCol.hasNext()) {
	// BasicDBObject businessObj = (BasicDBObject) busCol.next();
	// String business_id = businessObj.getString("business_id");
	// String categories = businessObj.getString("categories");
	// String full_address = businessObj.getString("full_address");
	// String hours = businessObj.getString("hours");
	//
	// Business business = new Business();
	// business.setBusinessId(business_id);
	// business.setCategories(categories);
	// business.setFullAddress(full_address);
	// business.setHours(hours);
	//
	// businesses.addBusiness(business);
	//
	// }
	// } finally { busCol.close(); }
	//
	// return businesses;
	// }
	//
	// @GET
	// @Path("/{city}/{categories}")
	// @Timed(name = "get-categories")
	// public BusinessDto getCategory(@PathParam("city") String city,
	// @PathParam("categories") String category) {
	//
	// DB db = mongo.getDB("273project");
	// DBCollection coll = db.getCollection("business");
	// BasicDBObject searchQuery = new BasicDBObject("city", city);
	// searchQuery.append("categories", category);
	//
	// DBCursor myCol = coll.find(searchQuery);
	// myCol.limit(15);
	//
	// BusinessDto businesses = new BusinessDto();
	//
	// try {
	// while(myCol.hasNext()) {
	//
	// BasicDBObject businessObj = (BasicDBObject) myCol.next();
	// String business_id = businessObj.getString("business_id");
	// String categories = businessObj.getString("categories");
	// String full_address = businessObj.getString("full_address");
	// String hours = businessObj.getString("hours");
	//
	// Business business = new Business();
	// business.setBusinessId(business_id);
	// business.setCategories(categories);
	// business.setFullAddress(full_address);
	// business.setHours(hours);
	//
	// businesses.addBusiness(business);
	//
	// }
	// } finally { myCol.close(); }
	//
	// return businesses;
	// }
	//
	//
	//
	// @GET
	// @Path("/{city}/{categories}/{hoursDay}/{time1}/{time2}")
	// @Timed(name = "get-timebased")
	// public BusinessDto getTimeBased(@PathParam("city") String
	// city,@PathParam("categories") String category , @PathParam("hoursDay")
	// String day ,
	// @PathParam("time1") String startTime , @PathParam("time2") String endTime
	// ) {
	//
	// DB db = mongo.getDB("273project");
	// DBCollection coll = db.getCollection("business");
	//
	//
	// BasicDBObject searchQuery = new BasicDBObject("categories", category);
	// searchQuery.append("city", city);
	// searchQuery.append("open", true);
	//
	// searchQuery.append("hours."+day+".open", new BasicDBObject("$lte",
	// startTime)).append("hours."+day+".close", new BasicDBObject("$gt",
	// endTime));
	// DBCursor myCol = coll.find(searchQuery);
	// myCol.limit(15);
	//
	// BusinessDto businesses = new BusinessDto();
	//
	// try {
	// while(myCol.hasNext()) {
	//
	// BasicDBObject businessObj = (BasicDBObject) myCol.next();
	// String business_id = businessObj.getString("business_id");
	// String categories = businessObj.getString("categories");
	// String full_address = businessObj.getString("full_address");
	// String hours = businessObj.getString("hours");
	//
	// Business business = new Business();
	// business.setBusinessId(business_id);
	// business.setCategories(categories);
	// business.setFullAddress(full_address);
	// business.setHours(hours);
	//
	// businesses.addBusiness(business);
	//
	// }
	// } finally { myCol.close(); }
	//
	// return businesses;
	// }
	//
	//
	// @GET
	// @Path("/{city}/{categories}/{when}")
	// @Timed(name = "get-timebased")
	// public BusinessDto getCurrentTime(@PathParam("city") String
	// city,@PathParam("categories") String category , @PathParam("when") String
	// when ) {
	//
	// DB db = mongo.getDB("273project");
	// DBCollection coll = db.getCollection("business");
	//
	// Calendar now = Calendar.getInstance();
	// System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) +
	// "-"
	// + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR) + "-"+
	// now.getTime().getHours() + "-"+ now.getTime().getMinutes());
	//
	// String[] strDays = new String[] { "Sunday", "Monday", "Tuesday",
	// "Wednesday", "Thusday",
	// "Friday", "Saturday" };
	// // Day_OF_WEEK starts from 1 while array index starts from 0
	// String day = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
	// int hours = now.getTime().getHours();
	// //int hours = 7 ;
	// int minutes = now.getTime().getMinutes();
	// System.out.println("Current day is : " + day + "hours and minutes" +
	// hours + " " +minutes);
	//
	// String startTime;
	//
	// if (hours <10 ){
	// startTime = "0"+hours + ":00";
	// }
	// else{
	// startTime = hours + ":00";
	// }
	//
	// BasicDBObject searchQuery = new BasicDBObject("categories", category);
	// searchQuery.append("city", city);
	// searchQuery.append("open", true);
	// searchQuery.append("hours."+day+".open", new BasicDBObject("$lt",
	// startTime)).append("hours."+day+".close", new BasicDBObject("$gt",
	// startTime));
	//
	// DBCursor cursor = coll.find(searchQuery);
	// cursor.limit(10);
	//
	// BusinessDto businesses = new BusinessDto();
	//
	// try {
	// while(cursor.hasNext()) {
	//
	// BasicDBObject businessObj = (BasicDBObject) cursor.next();
	// String business_id = businessObj.getString("business_id");
	// String categories = businessObj.getString("categories");
	// String full_address = businessObj.getString("full_address");
	// String hours_display = businessObj.getString("hours");
	//
	// Business business = new Business();
	// business.setBusinessId(business_id);
	// business.setCategories(categories);
	// business.setFullAddress(full_address);
	// business.setHours(hours_display);
	//
	// businesses.addBusiness(business);
	//
	// }
	// } finally { cursor.close(); }
	//
	// return businesses;
	// }
	//
}
