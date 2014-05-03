package com.kaizen.yelp.amazonsns;

import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Subscription;

public class SNS {


public static void main(String[] args){


		//if user comes and clicks on subscribe button  

		SNS sns = new SNS();
		//sns.userSubscribeToTopic("category10", "karthikswetha3@gmail.com");
		sns.userPublishingToTopic("category1", "message 2for category1");
		
	}


	private AmazonSNS connectToSNS (){
		//final AmazonSNS snsConnection = new AmazonSNSClient(new BasicAWSCredentials("<accesskey>",  "<secret key>"));
		final AmazonSNS snsConnection = new AmazonSNSClient(new BasicAWSCredentials("AKIAJ7K2MAJXLWYXUBUA",  "cXazKIn16Jao2vEv0TkisjrTDV6l327BkWbPdlUj"));
		snsConnection.setEndpoint("sns.us-west-1.amazonaws.com");
		return snsConnection;

	}


	public void userSubscribeToTopic (  String category , String email ){

		SNS sns = new SNS();

		AmazonSNS snsconnect = sns.connectToSNS();


		String categoryName = category.replaceAll("\\s+","");

		System.out.println(" category name " + categoryName);


		String topicArn = sns.createTopic(snsconnect, categoryName);


		System.out.println(" New topic arn" + topicArn);



		/*String[] split = topicArn.split(":");
		String categoryName = split[split.length-1];
		System.out.println(" category name is "+ categoryName);

		ListTopicsRequest listTopicsRequest = new ListTopicsRequest();
		snsconnect.listTopics(listTopicsRequest);
		List<Topic> allTopics = snsconnect.listTopics().getTopics();

		for (Topic topic : allTopics){
			String existingTopicArn = topic.getTopicArn();

			String[] splitting = existingTopicArn.split(":");
			String existingTopicName= splitting[splitting.length-1];
			System.out.println(" existing topic name" + existingTopicName);

			if ( !topicArn.equalsIgnoreCase(existingTopicArn)){
				System.out.println(" have to subscribe  to new topic");
				//sns.subscribeToTopic(snsconnect, topicArn, "email", email);
				break;
			}
			else{
				System.out.println(" already subscribed");
			}
		}
		 */


		boolean subscribeStatus = sns.isSubscribed(snsconnect, topicArn, email);

		if(subscribeStatus == true){
			System.out.println(" User already subscribed to topic "+ categoryName);
		}
		else{

			System.out.println(" Sending email to user to  subscribe to topic "+ categoryName);
			sns.subscribeToTopic(snsconnect, topicArn, "email", email);
		}


		//sns.publishToTopic(snsconnect, topicArn, message, "publishing new review");

	}


	public boolean isSubscribed(AmazonSNS snsConnect ,String topicArn , String endpoint){


		//ListSubscriptionsByTopicRequest listSubscriptionsRequest = new ListSubscriptionsByTopicRequest();
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

	public  String createTopic(AmazonSNS sns , String topicName){

	
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

		
		String categoryName = category.replaceAll("\\s+","");
		System.out.println(" category name " + categoryName);
		
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(categoryName);


		


		CreateTopicResult created = snsconnect.createTopic(createTopicRequest);

		String topicArn = created.getTopicArn();

		
		String subject =  "publishing message from "+ categoryName;


		

		sns.publishToTopic(snsconnect, topicArn, message, subject);



		}

}




