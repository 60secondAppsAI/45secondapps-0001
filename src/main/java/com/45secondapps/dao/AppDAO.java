package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.App;





public interface AppDAO extends GenericDAO<App, Integer> {
  
	List<App> findAll();
	






}


