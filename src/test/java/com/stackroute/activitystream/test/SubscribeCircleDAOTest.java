package com.stackroute.activitystream.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.activitystream.dao.*;
import com.stackroute.activitystream.model.SubscribeCircle;

public class SubscribeCircleDAOTest 
{
static SubscribeCircleDAO subscribeCircleDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.stackroute.activitystream");
		context.refresh();
		subscribeCircleDAO=(SubscribeCircleDAO)context.getBean("subscribeCircleDAO");
	}
	@Ignore
	@Test
	public void subscribeToCircleTest()
	{
		SubscribeCircle subscribeCircle=new SubscribeCircle();
		subscribeCircle.setSubscribe_id((int)Math.random()*10000);
		subscribeCircle.setCircle_id("veggrp05");
		subscribeCircle.setEmail_id("nikita@gmail.com");
		subscribeCircle.setStatus("A");
		subscribeCircle.setSubscribe_date(new java.util.Date());
		
		assertTrue("Problem in Subscribing",subscribeCircleDAO.subscribeToCircle(subscribeCircle));
	}

	@Test
	public void getAllSubscribeCirclesTest()
	{
		List<String> listSubscribeCirclesID=subscribeCircleDAO.getMySubscribeCirclesID("nikita@gmail.com");
		assertTrue("Not Subscribed to Any Circles",listSubscribeCirclesID.size()>0);
		
		for(String circleId:listSubscribeCirclesID)
		{
			System.out.println(circleId);
		}
	}
	

	@Test
	public void unsubScribeCircleTest()
	{
		assertTrue("Problem in Unsubscribing",subscribeCircleDAO.unSubscribeFromCircle(0));
	}
	
	//Negative Test Cases

	@Test
	public void inValidSubscribeCirclesTest()
	{
		List<String> listSubscribeCirclesID=subscribeCircleDAO.getMySubscribeCirclesID("niet@gmail.com");
		assertTrue("Problem in Subscribe Circles",listSubscribeCirclesID.isEmpty());
	}
	
	@Test
	public void negativeUnsubScribeCircleTest()
	{
		assertFalse("Problem in Unsubscribing",subscribeCircleDAO.unSubscribeFromCircle(99));
	}
	
	
	
}
