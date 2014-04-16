package com.kaizen.yelp.amazonsns;

import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListTopicsRequest;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.Topic;

public class SNS {


	public static void main(String[] args){

		
		
		
		
		//String topicArn = sns.createTopic(snsConnection , "newTopic");
		//sns.subscribeToTopic(snsConnection, topicArn, "email", "swetha.patnala@gmail.com");
		//sns.publishToTopic(snsConnection, "arn:aws:sns:us-west-1:416012710802:SwethaTopic1", "testing", "test msg");
		
		
		//if user comes and clicks on subscribe button  , and give their message for a particular category

		SNS sns = new SNS();
		sns.userSubscribeToTopic("category1", "category 1 message 4", "swetha.patnala@gmail.com");
		//sns.userPublishingToTopic("category1", "message 2for category1");
	}
	
	
	private AmazonSNS connectToSNS (){
		final AmazonSNS snsConnection = new AmazonSNSClient(new BasicAWSCredentials("<accesskey>",  "<secret key>"));
		
		snsConnection.setEndpoint("sns.us-west-1.amazonaws.com");
		return snsConnection;
		
	}
	
	
	public void userSubscribeToTopic (  String category , String message , String email ){
		SNS sns = new SNS();
		AmazonSNS snsconnect = sns.connectToSNS();
		String topicArn = sns.createTopic(snsconnect, category);
		System.out.println(" New topic arn" + topicArn);
		
		ListTopicsRequest listTopicsRequest = new ListTopicsRequest();
		snsconnect.listTopics(listTopicsRequest);
		List<Topic> allTopics = snsconnect.listTopics().getTopics();
		
		for (Topic topic : allTopics){
			
			String existingTopicArn = topic.getTopicArn();
			System.out.println(" existing topic arn" + existingTopicArn);
			
			if ( topicArn.equalsIgnoreCase(existingTopicArn)){
				sns.subscribeToTopic(snsconnect, topicArn, "email", email);
				break;
			}
		}
		sns.publishToTopic(snsconnect, topicArn, message, "publishing new review");
	
	}
	
	public boolean isSubscribed(AmazonSNS snsConnect ,String topicArn , String endpoint){
		
		
		ListSubscriptionsByTopicRequest listSubscriptionsRequest = new ListSubscriptionsByTopicRequest();
		ListSubscriptionsByTopicResult listofsubs = snsConnect.listSubscriptionsByTopic(topicArn);
		List<Subscription> list = listofsubs.getSubscriptions();
		
		
		for (Subscription sub :list){
			String existingEndPoint = sub.getEndpoint();
			System.out.println("endpoint : " + existingEndPoint);
			if( endpoint.equalsIgnoreCase(existingEndPoint)){
				return true;
			}
		}
		
		return false;
	}
	
	public static String createTopic(AmazonSNS sns , String topicName){

		/*ListTopicsRequest topicList = new ListTopicsRequest();
		
		ListTopicsResult topicLists = new ListTopicsResult();
		List<Topic> list = topicLists.getTopics();
		
		
		for (Topic lt : list){
			
			String arn = lt.getTopicArn();
			
			
			System.out.println("arn num" + arn);
			System.out.println("to string" +lt.toString() );
			
		}*/
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(topicName);
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
	
	public void userPublishingToTopic(String category , String message){
		SNS sns = new SNS();
		AmazonSNS snsconnect = sns.connectToSNS();
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(category);
		CreateTopicResult created = snsconnect.createTopic(createTopicRequest);
		String topicArn = created.getTopicArn();
		sns.publishToTopic(snsconnect, topicArn, category, "publishing message");
		
	}


}




