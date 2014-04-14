package com.kaizen.yelp.ui.views;

import java.util.List;

import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.domain.HelloMessage;
import com.yammer.dropwizard.views.View;


public class HomeView extends View {
	private final HelloMessage Message;
	
	
    public HomeView(HelloMessage message) {
              super("login.mustache");
              this.Message = message;
    }
    public HelloMessage getMessage() {
              return Message;
    }
}
