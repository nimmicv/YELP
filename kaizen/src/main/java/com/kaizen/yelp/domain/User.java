package com.kaizen.yelp.domain;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder(alphabetic = true)
public class User {
private String user_id;
private String yelping_since;
private String review_count;
private String name;
private String fans;
private String average_stars;

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
 * @return the yelping_since
 */
public String getYelpingSince() {
        return yelping_since;
}

/**
 * @param yelping_since to set
 */
public void setYelpingSince(String yelping_since) {
        this.yelping_since = yelping_since;
}

/**
 * @return the review_count
 */
public String getReviewCount() {
        return review_count;
}
/**
 * @param review_count to set
 */
public void setReviewCount(String review_count) {
        this.review_count = review_count;
}

/**
 * @return the name
 */
public String getName() {
        return name;
}
/**
 * @param name to set
 */
public void setName(String name) {
        this.name = name;
}

/**
 * @return the fans
 */
public String getFans() {
        return fans;
}
/**
 * @param fans to set
 */
public void setFans(String fans) {
        this.fans = fans;
}

/**
 * @return the average_stars
 */
public String getAverageStars() {
        return average_stars;
}
/**
 * @param average_stars to set
 */
public void setAverageStars(String fans) {
        this.average_stars = average_stars;
}

}
