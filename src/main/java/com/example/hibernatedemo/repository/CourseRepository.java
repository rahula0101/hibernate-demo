package com.example.hibernatedemo.repository;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Review;

@Repository
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	@Transactional
	public void deleteById(Long id) {
		em.remove(this.findById(id));
	}

	@Transactional
	public Course save(Course c) {
		if (c.getId() == null) {
			// New course
			em.persist(c);
		} else {
			// Updating an existing course
			em.merge(c);
		}

		return c;
	}

	// Using Query -> Here we will have to provide a type casting
	public List<Course> findAll_jpql() {
		Query qry = em.createQuery("select c from com.example.hibernatedemo.entity.Course c");
		return qry.getResultList();
	}

	// Using TypedQuery -> No type casting is required
	public List<Course> findAll_typed_jpql() {
		TypedQuery<Course> qry = em.createQuery("select c from com.example.hibernatedemo.entity.Course c",
				Course.class);

		return qry.getResultList();
	}

	// Using Named Query
	public List<Course> findAll_named_query() {
		TypedQuery<Course> qry = em.createNamedQuery("get_all_rows", Course.class);

		return qry.getResultList();
	}

	// Using Native Query
	public List<Course> findAll_native_query() {
		Query qry = em.createNativeQuery(" select * from course", Course.class);

		return qry.getResultList();
	}

	// Using Query -> Here we will have to provide a type casting
	public List<Course> findById_jpql() {
		Query qry = em.createQuery("select c from com.example.hibernatedemo.entity.Course c where id = :id ");
		qry.setParameter("id", 4002L);
		return qry.getResultList();
	}

	// Using TypedQuery -> No type casting is required
	public List<Course> findById_typed_jpql() {
		TypedQuery<Course> qry = em
				.createQuery("select c from com.example.hibernatedemo.entity.Course c  where id = :id ", Course.class);
		qry.setParameter("id", 4003L);
		return qry.getResultList();
	}

	// Using Named Query
	public List<Course> findById_named_query() {
		TypedQuery<Course> qry = em.createNamedQuery("get_specific_rows", Course.class);
		qry.setParameter("id", 4002L);
		return qry.getResultList();
	}

	// Using Native Query
	public List<Course> findById_native_query() {
		Query qry = em.createNativeQuery(" select * from course where id = ? ", Course.class);
		qry.setParameter(1, 4006L);
		return qry.getResultList();
	}

	@Transactional
	public void addReviewsForCourse(long courseId) {

		Course course = this.findById(courseId);
		logger.info(" Review to be added to course : ->  {} ", course);

		logger.info(" Current set of reviews : ->  {} ", course.getReviews());

		Review rev1 = new Review("Good course ", "3");
		Review rev2 = new Review("Average course ", "2");

		course.addReview(rev1);
		rev1.setCourse(course);

		course.addReview(rev2);
		rev2.setCourse(course);

		em.persist(rev1);
		em.persist(rev2);
	}

	@Transactional
	public void getReviewsForCouse(long courseId) {

		Course course = this.findById(courseId);

		logger.info("Reviews for a course -> {} ", course.getReviews());

	}

	public List<Course> courses_without_students() {

		TypedQuery<Course> qry = em.createQuery(" select c from Course c where c.students is empty ", Course.class);

		return qry.getResultList();

	}

	public List<Course> courses_with_atleast_n_students(int size) {

		TypedQuery<Course> qry = em.createQuery(" select c from Course c where size(c.students) >= :size ",
				Course.class);
		qry.setParameter("size", size);

		return qry.getResultList();

	}

	public List<Course> courses_order_by_students_size() {

		TypedQuery<Course> qry = em.createQuery(" select c from Course c order by size(c.students) ", Course.class);
		return qry.getResultList();

	}

	public void join() {
		
		Query qry = em.createQuery(" select c, s from Course c JOIN c.students s");
		List resultSet = qry.getResultList();

		logger.info("Result size : -> {}", resultSet.size());
		resultSet.stream().forEach(System.out::println);
	}

}
