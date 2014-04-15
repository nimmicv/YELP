package com.kaizen.yelp.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class Tip {
private String user_id;
private String business_id;
private String text;
private String likes;
private String date;

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

/**
 * @return the likes
 */
public String getLikes() {
        return likes;
}
/**
 * @param likes to set
 */
public void setLikes(String likes) {
        this.likes = likes;
}

}
