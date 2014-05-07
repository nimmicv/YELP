package com.kaizen.yelp.ui.views;

import java.util.ArrayList;

import com.kaizen.yelp.domain.CouponInfo;
import com.kaizen.yelp.domain.UserLogin;
import com.yammer.dropwizard.views.View;

public class NoCouponView extends View {
	UserLogin user = new UserLogin();
	String username = user.get_id();

public NoCouponView(String username)
{
	
	super("nocoupons.mustache");
	this.username = username;
	
}

}
