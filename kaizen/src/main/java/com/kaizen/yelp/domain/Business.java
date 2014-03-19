package com.kaizen.yelp.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class Business {
private String categories;
private String business_id;
private String full_address;
private String hours;

/**
 * @return the business_id
 */
public String getBusinessId() {
	return business_id;
}
/**
 * @param business_id to set
 */
public void setBusinessId(String business_id) {
	this.business_id = business_id;
}
/**
 * @return the categories
 */
public String getCategories() {
	return categories;
}
/**
 * @param categories to set
 */
public void setCategories(String categories) {
	this.categories = categories;
}
/**
 * @return the full_address
 */
public String getFullAddress() {
	return full_address;
}
/**
 * @param full_address to set
 */
public void setFullAddress(String full_address) {
	this.full_address = full_address;
}
/**
 * @return the Hours
 */
public String getHours() {
	return hours;
}
/**
 * @param Hours to set
 */
public void setHours(String hours) {
	this.hours = hours;
}
}
