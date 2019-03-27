package com.example.hibernatedemo.repository;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Passport;
import com.example.hibernatedemo.entity.Student;

@Repository
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	@Transactional
	public void deleteById(Long id) {
		em.remove(this.findById(id));
	}

	@Transactional
	public Student save(Student s) {
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
	public Long saveStudentWithPassport() {

		logger.info("Saving passport and student");
		
		Passport p = new Passport("D125892");
		em.persist(p);
		Student s = new Student("Mike");
		s.setPassport(p);
		p.setStudent(s);
		em.persist(s);

		return s.getId();
	}

	public Student findByName(String name) {
		
		TypedQuery<Student> qry = em.createQuery(
				"select s from com.example.hibernatedemo.entity.Student s  where name = :name ", Student.class);
		qry.setParameter("name", name);
		return qry.setMaxResults(1).getSingleResult();
	}
	
	
	@Transactional
	public void retriveStudentAndCourses() {
		Student student = em.find(Student.class, 1001L);
		logger.info("Student details -> {} ", student);
		logger.info("Courses details -> {} ", student.getCourses());
		
	}
	
	@Transactional
	public void saveStudentWithCourse() {
		
		logger.info("Saving student and course");
	
		// Since Student has a not null column as passport, we must first insert the passport
		
		// Inserting passport
		Passport p = new Passport("X125892");
		em.persist(p);
		
		
		Student student = new Student("Jack");
		
		// Setting relationship between student and passport
		student.setPassport(p);
		p.setStudent(student);
		
		// Saving student
		em.persist(student);

		Course course = new Course("Control Systems");
		em.persist(course);
		
		// Setting relationship between student and course (bi directional)
		student.addCourse(course);
		course.addStudent(student);
		
		em.persist(student);
		
		
	}
	
	public List<Student> student_with_passport_contains_pattern(){
		
		TypedQuery<Student> qry = em.createQuery(" select s from Student s where s.passport.passportNumber like '%12%' ", Student.class);
		return qry.getResultList();
	}

}
