package com.kaizen.yelp.RetrieveData;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class QueryMongoDB {
	private static DBCollection coll;
	private static Mongo mongo;
	private static DB db;

	public static void main(String[] args) throws UnknownHostException,
			MongoException {

		try {
			System.out.println("Connecting to Mongo DB..");
			mongo = new Mongo("127.0.0.1", 27017);
			db = mongo.getDB("273project");
			coll = db.getCollection("docs");
		} catch (UnknownHostException ex) {
			System.out.println("MongoDB Connection Error :" + ex.getMessage());
		}
		System.out.println("Success..");

		Pattern Restaurants = Pattern.compile("Restaurant?s",
				Pattern.CASE_INSENSITIVE);

		BasicDBObject searchQuery = new BasicDBObject("categories", Restaurants);
		DBCursor cursor = coll.find(searchQuery);

		try {

			while (cursor.hasNext()) {
				System.out.println(cursor.next());

			}
		} finally {
			cursor.close();
		}
	}
}
