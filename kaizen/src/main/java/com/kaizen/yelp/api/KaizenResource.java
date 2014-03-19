package com.kaizen.yelp.api;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

import com.google.common.base.Optional;
import com.kaizen.yelp.domain.HelloMessage;
import com.yammer.metrics.annotation.Timed;

import com.mongodb.Mongo;
//import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;


@Path("/kaizen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KaizenResource {
	
	private Mongo mongo;
	private DBCollection coll;
	public KaizenResource(Mongo mongo, DBCollection coll) throws UnknownHostException, MongoException
	{
		this.mongo = mongo;
		this.coll = coll;
		
	}
	
	@GET
    @Timed(name = "get-requests")
   //Give json class name as parameter eg:Book book 
    //Jersey notations
	
    public HelloMessage get(@QueryParam("name") Optional<String> name) {
		
	
		HelloMessage hello = new HelloMessage();
        hello.setMessage("Hello" + ( (name.isPresent()) ? " " + name.get() : ""));
       return hello;
    }
   
    @GET
    @Path("/{city}")
    @Timed(name = "get-city")
    	public DBObject getCity(@PathParam("city") String city) {
    //	public DBCursor getCity(@PathParam("city") String city) {
        //return db.business.find().limit(5);
        //Mongo mongoClient = new Mongo();
	DB db = mongo.getDB("273project");
	DBCollection coll = db.getCollection("business");
	DBObject myDoc = coll.findOne();
	BasicDBObject query = new BasicDBObject("city", city);
	DBCursor myCol = coll.find(query);
	//System.out.println(myDoc);
	try {
	   while(myCol.hasNext()) { System.out.println(myCol.next()); }
	} finally { myCol.close(); }
	
       return myDoc;
}

	
	@GET
	@Path("/{city}/{categories}")
	@Timed(name = "get-categories")
	public DBObject getCategory(@PathParam("city") String city, @PathParam("categories") String category) {

		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("business");


		BasicDBObject searchQuery = new BasicDBObject("city", city);
		searchQuery.append("categories", category);

		//BasicDBObject query = new BasicDBObject("categories", "Mexican");


		DBCursor myCol = coll.find(searchQuery);
		System.out.println("Total count"+ myCol.size());
		DBObject myDoc= coll.findOne();
		myCol.limit(15);


		try {
			while(myCol.hasNext()) {
				System.out.println(myCol.next());
				//return myCol.next();
			}
		} finally { myCol.close(); }

		return myDoc;

	}



	@GET
	@Path("/{city}/{categories}/{hoursDay}/{time1}/{time2}")
	@Timed(name = "get-timebased")
	public DBObject getTimeBased(@PathParam("city") String city,@PathParam("categories") String category , @PathParam("hoursDay") String day ,
			@PathParam("time1") String startTime , @PathParam("time2") String endTime  ) {

		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("business");


		BasicDBObject searchQuery = new BasicDBObject("categories", category);
		searchQuery.append("city", city);
		searchQuery.append("open", true);

		//searchQuery.append("hours.Monday.open", new BasicDBObject("$gt", startTime)).append("hours.Monday.close", new BasicDBObject("$lt", endTime));

		searchQuery.append("hours."+day+".open", new BasicDBObject("$gt", startTime)).append("hours."+day+".close", new BasicDBObject("$lt", endTime));
		DBCursor myCol = coll.find(searchQuery);
		myCol.limit(15);
		System.out.println("Total count"+ myCol.size());

		DBObject myDoc = coll.findOne();
		try {
			while(myCol.hasNext()) {
				System.out.println(myCol.next());
				//return myCol.next();
			}
		} finally { myCol.close(); }

		return myDoc;


	}
	
	
	@GET
	@Path("/{city}/{categories}/{when}")
	@Timed(name = "get-timebased")
	public DBObject getCurrentTime(@PathParam("city") String city,@PathParam("categories") String category , @PathParam("when") String when  ) {

		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("business");

		Calendar now = Calendar.getInstance();
		System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-"
				+ now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR) + "-"+ now.getTime().getHours() + "-"+ now.getTime().getMinutes());

		String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
				"Friday", "Saturday" };
		// Day_OF_WEEK starts from 1 while array index starts from 0
		String day = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
		//int hours = now.getTime().getHours();
		int hours = 7 ;
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
		DBObject myDoc = coll.findOne();

		try {

			while (cursor.hasNext()) {
				System.out.println(cursor.next());

			}
		} finally {
			cursor.close();
		}
		System.out.println("Count"+cursor.count());
		return myDoc;
		
	}

}

