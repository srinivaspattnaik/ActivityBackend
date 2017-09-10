package com.stackroute.activitystream.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.model.SubscribeCircle;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("subscribeCircleDAO")
public class SubscribeCircleDAOImpl implements SubscribeCircleDAO 
{

	@Autowired
	SessionFactory sessionFactory;
	
	public SubscribeCircleDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	@Override
	public boolean subscribeToCircle(SubscribeCircle subscribeCircle) 
	{
		try
		{
		sessionFactory.getCurrentSession().save(subscribeCircle);
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
	public boolean unSubscribeFromCircle(int subscribeID) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			SubscribeCircle subscribeCircle=(SubscribeCircle)session.get(SubscribeCircle.class,subscribeID);
			session.close();
			subscribeCircle.setStatus("N");
			subscribeCircle.setSubscribe_date(new java.util.Date());
			sessionFactory.getCurrentSession().saveOrUpdate(subscribeCircle);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@Transactional
	public List<String> getMySubscribeCirclesID(String emailID)
	{
		try
		{
			//Query query=sessionFactory.getCurrentSession().createQuery("from SubscribeCircle where email_id=:emailid and status='A'");
			//query.setParameter("emailid",emailID);
			//List<SubscribeCircle> listCircles=query.list();
			
			List<String> listCirclesID=new ArrayList<String>();
			
			Session session=sessionFactory.openSession();
			Criteria criteria=session.createCriteria(SubscribeCircle.class);
			criteria.add(Restrictions.eq("email_id",emailID));
			criteria.add(Restrictions.eq("status","A"));
			
			for(SubscribeCircle subscribeCircle:(List<SubscribeCircle>)criteria.list())
			{
				listCirclesID.add(subscribeCircle.getCircle_id());
			}
			
			return listCirclesID;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
