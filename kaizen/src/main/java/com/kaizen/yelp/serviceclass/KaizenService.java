package com.kaizen.yelp.serviceclass;

import com.kaizen.yelp.api.KaizenResource;
import com.kaizen.yelp.config.KaizenConfiguration;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;


public class KaizenService extends Service<KaizenConfiguration> {

    public static void main(String[] args) throws Exception {
	new KaizenService().run(args);
    }

    @Override
    public void initialize(Bootstrap<KaizenConfiguration> bootstrap) {
	bootstrap.setName("kaizen-service");
    }

    @Override
    public void run(KaizenConfiguration configuration,
	    Environment environment) throws Exception {
	environment.addResource(new KaizenResource());
    }

}

