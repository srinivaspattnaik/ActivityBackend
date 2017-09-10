package com.stackroute.activitystream.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

//import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.model.Circle;



@Repository("circleDAO")
public class CircleDAOImpl implements CircleDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	public CircleDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	@Override
	public boolean createCircle(Circle circle) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(circle);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;	
		}
		
	}

	@Transactional
	@Override
	public boolean removeCircle(String circleID,String owner) 
	{
		try
		{
			
			Session session=sessionFactory.openSession();
			Circle circle=(Circle)session.get(Circle.class,circleID);
			if(circle.getCircle_owner().equals(owner))
			{
			session.delete(circle);
			session.flush();
			session.close();
			return true;
			}
			else
			{
			session.close();
			return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception e"+e);
			return false;	
		}
	}

	@Transactional
	@Override
	public boolean updateCircle(Circle circle) 
	{
		try
		{
			sessionFactory.getCurrentSession().update(circle);
			return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}

	@Transactional
	@Override
	public List<String> getMyOwnCirclesID(String emailid) 
	{	
		Session session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Circle.class);
		criteria.add(Restrictions.eq("circle_owner",emailid));
		List<String> listCircle=new ArrayList<String>();
		for(Circle circle:(List<Circle>)criteria.list())
		{
			listCircle.add(circle.getCircle_id());
		}
		
		return listCircle;
	}

	@Transactional
	@Override
	public List<String> getAllCircleID() 
	{
		Query query=sessionFactory.getCurrentSession().createQuery("select circle_id from Circle");
		List<String> listCircleId=(List<String>)query.list();
		return listCircleId;
	}
	
	

	
}
