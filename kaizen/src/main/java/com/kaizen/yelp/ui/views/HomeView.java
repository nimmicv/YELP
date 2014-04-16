package com.kaizen.yelp.ui.views;

import java.util.List;

import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.domain.HelloMessage;
import com.kaizen.yelp.domain.Review;
import com.kaizen.yelp.domain.User;
import com.kaizen.yelp.domain.UserLogin;
import com.kaizen.yelp.dto.ReviewDto;
import com.yammer.dropwizard.views.View;


public class HomeView extends View {

	private UserLogin loginuser;
	private User userinfo;
	private List<Review> reviews;
	
	public User getUserInfo() {
		return userinfo;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public UserLogin getLoginuser() {
		return loginuser;
	}
    public HomeView(HelloMessage message) {
             super("login.mustache");

    	//super("home.mustache");
    }
    public HomeView() {
        super("index.mustache");
        

	//super("home.mustache");
         
}
    public HomeView(UserLogin loggedinuser) {
        super("home.mustache");
        loginuser = loggedinuser;

	//super("home.mustache");
         
}
	public HomeView(UserLogin user, User userData, List<Review> reviews) {
		// TODO Auto-generated constructor stub
        super("home.mustache");
<<<<<<< HEAD
        loginuser=user;
        userInfo=userData;
        System.out.println(userInfo.getYelpingSince());
=======
        this.loginuser=user;
        this.userinfo=userData;
>>>>>>> FETCH_HEAD
        this.reviews=reviews;
        
        

	}
  
}
