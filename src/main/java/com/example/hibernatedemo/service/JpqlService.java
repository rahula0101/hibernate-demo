package com.example.hibernatedemo.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JpqlService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	EntityManager em;

	public void join() {

		Query qry = em.createQuery(" select c, s from Course c JOIN c.students s");
		List<Object[]> resultSet = qry.getResultList();

		logger.info("Result size Join : -> {}", resultSet.size());
		for (Object[] o : resultSet) {
			logger.info("Course{} Student{}", o[0], o[1]);
		}
	}

	// Print all the courses irrespective of student is associated or not
	public void join_left() {

		Query qry = em.createQuery(" select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultSet = qry.getResultList();

		logger.info("Result size Left Join : -> {}", resultSet.size());
		for (Object[] o : resultSet) {
			logger.info("Course{} Student{}", o[0], o[1]);
		}
	}

	// Print all the student irrespective of Course is associated or not
	public void join_right() {

		Query qry = em.createQuery(" select c, s from Course c RIGHT JOIN c.students s");
		List<Object[]> resultSet = qry.getResultList();

		logger.info("Result size Right Join : -> {}", resultSet.size());
		for (Object[] o : resultSet) {
			logger.info("Course {} Student {}", o[0], o[1]);
		}
	}

	// Print all the student irrespective of Course is associated or not and all the
	// course irrespective of whether student is associated or not
	public void join_cross() {

		Query qry = em.createQuery(" select c, s from Course c , Student s");
		List<Object[]> resultSet = qry.getResultList();

		logger.info("Result cross join : -> {}", resultSet.size());
		for (Object[] o : resultSet) {
			logger.info("Course {} Student {}", o[0], o[1]);
		}
	}

}
