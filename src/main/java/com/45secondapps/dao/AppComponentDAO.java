package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.AppComponent;





public interface AppComponentDAO extends GenericDAO<AppComponent, Integer> {
  
	List<AppComponent> findAll();
	






}


