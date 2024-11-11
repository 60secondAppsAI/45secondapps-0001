package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.ApplicationDeveloper;





public interface ApplicationDeveloperDAO extends GenericDAO<ApplicationDeveloper, Integer> {
  
	List<ApplicationDeveloper> findAll();
	






}


