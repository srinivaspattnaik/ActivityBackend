package com.stackroute.activitystream.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.activitystream.dao.*;
import com.stackroute.activitystream.model.User;

public class UserDAOTest 
{
static UserDAO userDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.stackroute.activitystream");
		context.refresh();
		userDAO=(UserDAO)context.getBean("userDAO");
	}
	
	@Ignore
	@Test
	public void userInsertionTest()
	{
		User user=new User("dimple@gmail.com","pass@1234","Dimple Baid","9998881213");
		assertTrue("Problem in Insertion",userDAO.addUser(user));
	}
	
	@Test
	public void validateUserTest()
	{
		User user=new User("dimple@gmail.com","pass@1234","Dimple Baid","9998881213");
		assertTrue("Problem in Validation",userDAO.validateUser(user));
	}
	
	@Test
	public void deleteUserTest()
	{
		User user=new User("dimple@gmail.com","pass@1234","Dimple Baid","9998881213");
		assertTrue("Problem in Deletion",userDAO.deleteUser(user));
	}
	
	@Test
	public void updateUserTest()
	{
		User user=new User("dimple@gmail.com","pass@1234","Dimple Kumari Baid","9998881213");
		assertTrue("Problem in Updation",userDAO.updateUser(user));
	}
	
	//Negative Test Cases Implementation
	
	@Test
	public void notValidateUserTest()
	{
		User user=new User("dimple@gmail.com","pass@234","Dimple Baid","9998881213");
		assertFalse("Error Occured",userDAO.validateUser(user));
	}
	
	@Test
	public void notDeleteUserTest()
	{
		User user=new User("dimle@gmail.com","pass@1234","Dimple Baid","9998881213");
		assertFalse("Problem in Deletion",userDAO.deleteUser(user));
	}
	
	@Test
	public void notUpdateUserTest()
	{
		User user=new User("dimle@gmail.com","pass@1234","Dimple Kumarii Baid","9998881213");
		assertFalse("Problem in Updation",userDAO.updateUser(user));
	}
	
}
