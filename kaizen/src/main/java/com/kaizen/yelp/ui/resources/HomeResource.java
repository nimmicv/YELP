package com.kaizen.yelp.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kaizen.yelp.domain.HelloMessage;
import com.kaizen.yelp.domain.UserLogin;
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
	public HomeView getLogin() {
		return new HomeView(new HelloMessage());
	}

	@GET
	@Path("/home/{username}")
	public HomeView getHome(@PathParam("username") String username) {
		// Get user details viz id , email, pwd fom mongodb and create userLogin
		// Object
		UserLogin user = new UserLogin();
		String password = null;
		String email = null;
		DB db = mongo.getDB("273project");
		DBCollection coll = db.getCollection("userInfo");
		DBObject userInfo = new BasicDBObject("username", username);
		DBCursor cursor = coll.find(userInfo);
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			password = (String) o.get("password");
			email = (String) o.get("email");

		}
		System.out.println("password" + password);
		System.out.println("email" + email);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		return new HomeView(user);
	}
}
