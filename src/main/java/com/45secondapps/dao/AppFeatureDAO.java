package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.AppFeature;





public interface AppFeatureDAO extends GenericDAO<AppFeature, Integer> {
  
	List<AppFeature> findAll();
	






}


