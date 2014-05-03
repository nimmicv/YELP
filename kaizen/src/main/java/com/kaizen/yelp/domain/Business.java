package com.kaizen.yelp.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class Business {
private String business_id;
private String name;
private String full_address;
private String hours;
private String categories;
private float review_count;
private float stars;

public String getName() {
        return name;
}
public String getBusiness_id() {
	return business_id;
}
public void setBusiness_id(String business_id) {
	this.business_id = business_id;
}
public String getFull_address() {
	return full_address;
}
public void setFull_address(String full_address) {
	this.full_address = full_address;
}
public void setName(String name) {
        this.name = name;
}

public String getCategories() {
	return categories;
}

public void setCategories(String categories) {
	this.categories = categories;
}
public String getHours() {
	return hours;
}
public void setHours(String hours) {
	this.hours = hours;
}
public float getReview_count() {
	return review_count;
}
public void setReview_count(float review_count) {
	this.review_count = review_count;
}
public float getStars() {
	return stars;
}
public void setStars(float stars) {
	this.stars = stars;
}

}
