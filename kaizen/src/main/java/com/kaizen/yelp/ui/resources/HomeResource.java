package com.kaizen.yelp.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kaizen.yelp.domain.HelloMessage;
import com.kaizen.yelp.domain.UserLogin;
import com.kaizen.yelp.ui.views.HomeView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
   

@GET
public HomeView getLogin() {
	return new HomeView(new HelloMessage());
}

@GET
@Path("/home/{username}")
public HomeView getHome(@PathParam("username") String username) {
	//Get user details viz id , email, pwd fom mongodb and create userLogin Object
	
	UserLogin user = new UserLogin();
	user.setUsername(username);
	return new HomeView(user);
}
}
