package com.kaizen.yelp.ui.views;

import com.kaizen.yelp.domain.UserLogin;
import com.yammer.dropwizard.views.View;

public class ReviewView extends View {
	
	UserLogin user = new UserLogin();
	String username = user.get_id();
	public ReviewView(String user) {
		super("review.mustache");
		this.username = user;
	}

}
