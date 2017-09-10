package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.Message;

public interface MessageDAO 
{
	public boolean sendMessage(Message message);
	public List<Message> getMessageByCircle(String circleid);
	public List<Message> getMessageByUser(String receiverid);
}
