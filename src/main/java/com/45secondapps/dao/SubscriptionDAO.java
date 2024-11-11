package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Subscription;





public interface SubscriptionDAO extends GenericDAO<Subscription, Integer> {
  
	List<Subscription> findAll();
	






}


