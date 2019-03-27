package com.example.hibernatedemo.repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Review;

@Repository
public class ReviewRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	EntityManager em;

	public Review findById(Long id) {
		return em.find(Review.class, id);
	}

	@Transactional
	public void deleteById(Long id) {
		em.remove(this.findById(id));
	}

	@Transactional
	public Review save(Review s) {
		if (s.getId() == null) {
			// New course
			em.persist(s);
		} else {
			// Updating an existing course
			em.merge(s);
		}

		return s;
	}
	
	@Transactional
	public Course findCourseDetails(Long id) {
		Review rev=  em.find(Review.class, id);
		
		Course c = rev.getCourse();
		
		return c;
	}

}
