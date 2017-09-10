package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.Circle;

public interface CircleDAO 
{
	public boolean createCircle(Circle circle);
	public boolean removeCircle(String circleID,String owner);
	public boolean updateCircle(Circle circle);
	public List<String> getMyOwnCirclesID(String emailid);
	public List<String> getAllCircleID();
}
