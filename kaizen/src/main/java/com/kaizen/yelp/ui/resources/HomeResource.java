package com.kaizen.yelp.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kaizen.yelp.domain.HelloMessage;
import com.kaizen.yelp.ui.views.HomeView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
   

    @GET
    public HomeView getHome() {
	return new HomeView(new HelloMessage());
    }
}
