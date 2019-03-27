package com.example.hibernatedemo.repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.hibernatedemo.entity.Passport;

@Repository
public class PassportRepository {
	
	@Resource
	EntityManager em;

	public Passport findById(Long id) {
		return em.find(Passport.class, id);
	}

	@Transactional
	public void deleteById(Long id) {
		em.remove(this.findById(id));
	}

	@Transactional
	public Passport save(Passport s) {
		if (s.getId() == null) {
			// New course
			em.persist(s);
		} else {
			// Updating an existing course
			em.merge(s);
		}

		return s;
	}


}
