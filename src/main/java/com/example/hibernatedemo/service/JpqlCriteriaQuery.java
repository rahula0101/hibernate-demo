package com.example.hibernatedemo.service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JpqlCriteriaQuery {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	EntityManager em;

}
