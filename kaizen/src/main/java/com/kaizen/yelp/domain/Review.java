package com.kaizen.yelp.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class Review {
private String user_id;
private String business_id;
private String review_id;
private String stars;
private String date;
private String text;

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
 * @return the user_id
 */
public String getUserId() {
        return user_id;
}
/**
 * @param user_id to set
 */
public void setUserId(String user_id) {
        this.user_id = user_id;
}


/**
 * @return the review_id
 */
public String getReviewId() {
        return review_id;
}
/**
 * @param review_id to set
 */
public void setReviewId(String review_id) {
        this.review_id = review_id;
}

/**
 * @return the starts
 */
public String getStars() {
	return stars;
}
/**
 * @param stars to set
 */
public void setStars(String stars) {
	this.stars = stars;
}
/**
 * @return the date
 */
public String getDate() {
	return date;
}
/**
 * @param date to set
 */
public void setDate(String date) {
	this.date = date;
}
/**
 * @return the text
 */
public String getText() {
	return text;
}
/**
 * @param text to set
 */
public void setText(String text) {
	this.text = text;
}
}
