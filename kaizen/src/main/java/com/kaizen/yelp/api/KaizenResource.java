package com.kaizen.yelp.api;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.kaizen.yelp.domain.HelloMessage;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.yammer.metrics.annotation.Timed;


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
}
