package com.kaizen.yelp.domain;

public class CouponInfo {
	
	private String dealName;
	private String dealSource;
	private String dealImage;
	private String dealLink;
	
	
	public CouponInfo(String dealName , String dealSource , String dealImage, String dealLink) {
		this.dealName = dealName;
		this.dealSource = dealSource;
		this.dealImage = dealImage;
		this.dealLink = dealLink;
		
	}
	@Override
	public String toString(){
		
		return dealName + ":" + dealSource + ":" + dealImage + ":" + dealSource;
		
	}

}
