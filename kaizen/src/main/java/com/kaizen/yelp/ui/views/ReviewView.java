package com.kaizen.yelp.ui.views;

import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.domain.UserLogin;
import com.yammer.dropwizard.views.View;

public class ReviewView extends View {
	
	UserLogin user = new UserLogin();
	String username = user.get_id();
	Business business = new Business();
	String name = business.getName();
	public ReviewView(String user, String name) {
		super("review.mustache");
		this.username = user;
		this.name = name;
	}
	

}
