package com.kaizen.yelp.amazonsns;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SNS {


	public static void main(String[] args){

		final AmazonSNS snsConnection = new AmazonSNSClient(new BasicAWSCredentials("<accesskey>",  "<secret key>"));
		
		
		snsConnection.setEndpoint("sns.us-west-1.amazonaws.com");
		
		SNS sns = new SNS();
		//String topicArn = sns.createTopic(snsConnection);
		//sns.subscribeToTopic(snsConnection, topicArn, "email", "swetha.patnala@gmail.com");
		sns.publishToTopic(snsConnection, "arn:aws:sns:us-west-1:416012710802:SwethaTopic1", "testing", "test msg");
		

	}
	public static String createTopic(AmazonSNS sns){

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

	
	public void publishToTopic(AmazonSNS sns ,String topicArn , String message , String subject){
		
		PublishRequest publishToTopic = new PublishRequest(topicArn, message , subject);
		PublishResult publishResult = sns.publish(publishToTopic);
		publishResult.getMessageId();
		
		System.out.println("message id "+ publishResult.getMessageId());
		
	}


}




