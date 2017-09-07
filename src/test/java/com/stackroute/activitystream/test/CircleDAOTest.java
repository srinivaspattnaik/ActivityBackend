package com.stackroute.activitystream.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;

import com.stackroute.activitystream.dao.*;


public class CircleDAOTest 
{
	
	static CircleDAO circleDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.stackroute.activitystream");
		context.refresh();
		circleDAO=(CircleDAO)context.getBean("circleDAO");
	}
	
	@Ignore
	@Test
	public void insertionCircleTest()
	{
		Circle circle=new Circle();
		circle.setCircle_id("VegGrp06");
		circle.setCircle_name("Veg Group with Egg");
		circle.setCircle_owner("samita@gmail.com");
		circle.setCreation_date(new java.util.Date());
		circle.setDescription("This Group is Veg Group with Egg Eating");
		assertTrue("Problem in Insertion",circleDAO.createCircle(circle));
	}
	
	@Ignore
	@Test
	public void removeCircleTest()
	{
		String circleid="VegGrp06";
		assertTrue("Problem in Deleting",circleDAO.removeCircle(circleid));
	}
	
	@Test
	public void updateCircleTest()
	{
		Circle circle=new Circle();
		circle.setCircle_id("VegGrp06");
		circle.setCircle_name("Veg Group with Some Egg");
		circle.setCircle_owner("samita@gmail.com");
		circle.setCreation_date(new java.util.Date());
		circle.setDescription("This Group is Veg Group with Egg Eating");
		assertTrue("Problem in Insertion",circleDAO.updateCircle(circle));
	}
	
	@Test
	public void getAllCircleOwnedByUserTest()
	{
		List<Circle> ownerCircles=circleDAO.getMyCircles("nikita@gmail.com");
		assertNotNull("No Circles Owned",ownerCircles);
	}
	
	@Test
	public void getAllCircleIdTest()
	{
		List<String> allCircleId=circleDAO.getAllCircleID();
		assertNotNull("There are No Circle ID",allCircleId);
	}
	
	//Negative Test Cases
	
	@Test
	public void InvalidCircleOwnerTestCase()
	{
		List<Circle> ownerCircles=circleDAO.getMyCircles("nik@gmail.com");
		assertTrue("Circles Found",ownerCircles.isEmpty());
	}
	
	@Test(expected = HibernateOptimisticLockingFailureException.class)
	public void InvalidUpdationTestCase()
	{
		Circle circle=new Circle();
		circle.setCircle_id("VegGrp07");
		circle.setCircle_name("Veg Group with Some Egg");
		circle.setCircle_owner("samita@gmail.com");
		circle.setCreation_date(new java.util.Date());
		circle.setDescription("This Group is Veg Group with Egg Eating");
		
		assertFalse("No Circle Found",circleDAO.updateCircle(circle));
	}
	
}
