package com.kaizen.yelp.jdbi;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.kaizen.yelp.domain.UserLogin;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;





public class Mongo_Pojo {
	
	public DBObject toDbObject(UserLogin newUser) {
		DBObject tempDbObject = new BasicDBObject();
		tempDbObject.put("_id", newUser.get_id());
		tempDbObject.put("password", newUser.getPassword());
		tempDbObject.put("email", newUser.getEmail());
		return tempDbObject;
	}
	
	public UserLogin toUserObject(String jsonString) {
		UserLogin tempUser = new UserLogin();
		try {
			tempUser = new ObjectMapper().readValue(jsonString, UserLogin.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempUser;
	}

}
