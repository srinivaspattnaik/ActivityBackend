package com.stackroute.activitystream.dao;

import java.util.List;

public interface MessageDAO 
{
	public boolean sendMessage(Message message);
	public List<Message> getMessageByCircle(String circleid);
	public List<Message> getMessageByUser(String receiverid);
}
