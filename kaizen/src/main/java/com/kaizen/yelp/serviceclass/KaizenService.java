package com.kaizen.yelp.serviceclass;

import net.vz.mongodb.jackson.JacksonDBCollection;
import com.kaizen.yelp.domain.Business;
import com.kaizen.yelp.api.KaizenResource;
import com.kaizen.yelp.config.KaizenConfiguration;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
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
	public void run(KaizenConfiguration configuration, Environment environment)
			throws Exception {
		Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
		DB db = mongo.getDB(configuration.mongodb);
		DBCollection coll = db.getCollection("docs");
	//JacksonDBCollection<Business, String> business = JacksonDBCollection.wrap(db.getCollection("docs"),Business.class,String.class);
		MongoManaged mongoManaged = new MongoManaged(mongo);
		environment.manage(mongoManaged);
		environment.addHealthCheck(new MongoHealthCheck(mongo));
		environment.addResource(new KaizenResource( mongo,  coll));
		
	}

}
