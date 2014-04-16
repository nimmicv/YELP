package com.kaizen.yelp.ui.views;

import com.kaizen.yelp.domain.Business;
import com.yammer.dropwizard.views.View;

public class BusinessView extends View{
	
	private Business business;

	public Business getBusiness() {
		return business;
	}

	protected BusinessView(Business business) {
		super("business.mustache");
		this.business=business;
		
		// TODO Auto-generated constructor stub
	}

}
