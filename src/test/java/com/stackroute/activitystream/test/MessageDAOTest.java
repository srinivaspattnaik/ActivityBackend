package com.stackroute.activitystream.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.activitystream.dao.*;

public class MessageDAOTest 
{

static MessageDAO messageDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.stackroute.activitystream");
		context.refresh();
		messageDAO=(MessageDAO)context.getBean("messageDAO");
	}
	
	@Test
	public void sendMessageTest()
	{
		Message message=new Message();
		
		
		message.setMessageID((int)Math.random()*10000);
		message.setMessageContent("This is a simple Message from User");
		message.setMessageType("Text");
		message.setMessageTime(new java.util.Date());
		message.setReceiverCircleID("veggrp04");
		message.setReceiverID(null);
		message.setSenderID("nikita@gmail.com");
		
		assertTrue("Problem in Insertion",messageDAO.sendMessage(message));
	}
	
	@Test
	public void getMessageByCircleTest()
	{
		List<Message> listCircleMessages=messageDAO.getMessageByCircle("veggrp05");
		assertTrue("There are no Messages",listCircleMessages.size()>0);
	}
	
	@Test
	public void getMessageByReceiverTest()
	{
		List<Message> listUserMessages=messageDAO.getMessageByUser("nikita@gmail.com");
		assertTrue("There are no Messages",listUserMessages.size()>0);
	}
	
	//Negative Test Case
	
	@Test
	public void inValidMessageByCircleTest()
	{
		List<Message> listCircleMessages=messageDAO.getMessageByCircle("veggrp02");
		assertTrue("There are no Messages",listCircleMessages.isEmpty());
	}
	
	@Test
	public void InvalidMessageByReceiverTest()
	{
		List<Message> listUserMessages=messageDAO.getMessageByUser("niita@gmail.com");
		assertTrue("There are no Messages",listUserMessages.isEmpty());
	}
	
}
