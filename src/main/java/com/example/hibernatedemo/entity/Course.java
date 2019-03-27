package com.example.hibernatedemo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "course")
@NamedQueries(value = { @NamedQuery(name = "get_all_rows", query = "select c from Course c"),
		@NamedQuery(name = "get_specific_rows", query = "select c from Course c where id =  :id ") })
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_course_gen")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<>();

	@UpdateTimestamp
	private LocalDateTime updateDate;

	@CreationTimestamp
	private LocalDateTime creationDate;

	/**
	 * default constructor as mandated by jpa
	 */
	public Course() {
	}

	/**
	 * @param name
	 */
	public Course(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * Add a new review
	 * 
	 * @param reviews
	 *            the reviews to set
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	/**
	 * Remove review
	 * 
	 * @param review
	 */
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students
	 *            the students to set
	 */
	public void addStudent(Student student) {
		this.students.add(student);
	}

	/**
	 * Remove student
	 * 
	 * @param student
	 */
	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", updateDate=" + updateDate + ", creationDate=" + creationDate
				+ "]";
	}

}
