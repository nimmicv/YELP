package com.kaizen.yelp.ui.views;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.kaizen.yelp.domain.CouponInfo;
import com.kaizen.yelp.domain.UserLogin;
import com.yammer.dropwizard.views.View;

public class CouponView extends View {
	
	 ArrayList<CouponInfo> couponlist  = new ArrayList<CouponInfo>();
		UserLogin user = new UserLogin();
		String username = user.get_id();

	public CouponView(String username,ArrayList<CouponInfo> couponlist)
	{
		
		super("couponinfo.mustache");
	
		
		this.couponlist = couponlist;
		this.username = username;
		
	}

	public CouponView(String username) {
		super("nocoupons.mustache");
		this.username = username;

		// TODO Auto-generated constructor stub
	}

}
