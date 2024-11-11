package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Template;





public interface TemplateDAO extends GenericDAO<Template, Integer> {
  
	List<Template> findAll();
	






}


