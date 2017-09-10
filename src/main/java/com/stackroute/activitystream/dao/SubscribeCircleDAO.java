package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.SubscribeCircle;

//The comments given long back, still not incorporated.
public interface SubscribeCircleDAO 
{
	public boolean subscribeToCircle(SubscribeCircle subscribeCircle);
	//From where you will get subscribeID?  
	//You can unsubscribe easily if you send emailID and circleID
	public boolean unSubscribeFromCircle(int subscribeID);
	public List<String> getMySubscribeCirclesID(String emailID);
}
