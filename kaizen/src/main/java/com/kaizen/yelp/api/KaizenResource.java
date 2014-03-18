package com.kaizen.yelp.api;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    @Path("/city")
    @Timed(name = "get-city")
	public DBObject getCity() {
        //return db.business.find().limit(5);
        //Mongo mongoClient = new Mongo();
	DB db = mongo.getDB("273project");
	DBCollection coll = db.getCollection("business");
	DBObject myDoc = coll.findOne();
	System.out.println(myDoc);
	return myDoc;
    }


}
