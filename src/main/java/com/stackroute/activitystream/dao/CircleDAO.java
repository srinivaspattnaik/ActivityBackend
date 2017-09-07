package com.stackroute.activitystream.dao;

import java.util.List;

public interface CircleDAO 
{
	public boolean createCircle(Circle circle);
	//This method should take two parameters circleID and emailid(owner)
	//Only owner can delete the circle.
	public boolean removeCircle(String circleID);
	public boolean updateCircle(Circle circle);
	
	//The return type can be List<String> i.e., need only circle ids.
	public List<Circle> getMyCircles(String emailid);
	public List<String> getAllCircleID();
}
