package com.kaizen.yelp.ui.views;

import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.domain.User;
import com.yammer.dropwizard.views.View;

public class BusinessView extends View{
	
	private Business business;
	private User user;

	public User getUser() {
		return user;
	}

	public Business getBusiness() {
		return business;
	}

	public BusinessView(Business business, User user) {
		super("business.mustache");
		
		
		// TODO Auto-generated constructor stub
	}

}
