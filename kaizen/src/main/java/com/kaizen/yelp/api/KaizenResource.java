package com.kaizen.yelp.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import com.kaizen.yelp.domain.HelloMessage;


@Path("/kaizen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KaizenResource {
	
	public KaizenResource()
	{
		
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
