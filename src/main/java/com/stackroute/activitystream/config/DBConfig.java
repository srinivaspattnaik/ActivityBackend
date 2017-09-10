package com.stackroute.activitystream.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stackroute.activitystream.dao.*;
import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.model.SubscribeCircle;
import com.stackroute.activitystream.model.User;

////USE APPLICATION.PROPERTIES FILE INSTEAD OF JAVA CONFIGURATION  --- SPRING BOOT

@Configuration
@ComponentScan("com.stackroute.activitystream")
@EnableTransactionManagement
public class DBConfig 
{

	@Bean(name = "dataSource")
	public DataSource getMySQLDataSource() 
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/activitystream");
		dataSource.setUsername("root");
		dataSource.setPassword("Password@123");
		System.out.println("Data Source Creation");
		return dataSource;
	}

	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		Properties prop=new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getMySQLDataSource());
		sessionBuilder.addProperties(prop);
	
		sessionBuilder.addAnnotatedClass(Circle.class);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(SubscribeCircle.class);
		sessionBuilder.addAnnotatedClass(Message.class);
		
		SessionFactory sessionFactory=sessionBuilder.buildSessionFactory();
		//remove SOPs..  can use logger statements - AOP
		System.out.println("Session Factory Object Created");
		return sessionFactory;
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
	@Bean(name="circleDAO")
	public CircleDAO getCircleDAO(SessionFactory sessionFactory) 
	{
		return new CircleDAOImpl(sessionFactory);
	}
	@Bean(name="userDAO")
	public UserDAO getBlogDAO(SessionFactory sessionFactory)
	{
		return new UserDAOImpl();
	}
	@Bean(name="subscribeCircleDAO")
	public SubscribeCircleDAO getSubscribeCircleDAO(SessionFactory sessionFactory)
	{
		return new SubscribeCircleDAOImpl(sessionFactory);
	}
	
	@Bean(name="messageDAO")
	public MessageDAO getMessageDAO(SessionFactory sessionFactory)
	{
		return new MessageDAOImpl(sessionFactory);
	}
}
