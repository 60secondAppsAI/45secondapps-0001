package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Feature;





public interface FeatureDAO extends GenericDAO<Feature, Integer> {
  
	List<Feature> findAll();
	






}


