package com.kaizen.yelp.amazonsns;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SNS {


	public static void main(String[] args){

		final AmazonSNS sns = new AmazonSNSClient(new BasicAWSCredentials("<accesskey>",  "<secret key>"));

		sns.setEndpoint("sns.us-west-1.amazonaws.com");

	}
	public String createTopic(AmazonSNS sns){

		CreateTopicRequest createTopicRequest = new CreateTopicRequest("SwethaTopic1");
		CreateTopicResult created = sns.createTopic(createTopicRequest);
		String topicArn = created.getTopicArn();
		System.out.println("topic arn " + topicArn);
		return topicArn;
	}



	public void subscribeToTopic(AmazonSNS sns , String topicArn , String protocol , String endpoint){
		
		sns.subscribe(topicArn, protocol, endpoint);
		//sns.subscribe(topicArn, "email", "swetha.patnala@gmail.com");
	}

	
	public void publishToTopic(){
		
	}


}




