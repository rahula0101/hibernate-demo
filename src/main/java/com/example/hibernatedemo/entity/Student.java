package com.example.hibernatedemo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_student_gen")
	private Long id;

	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Passport passport;

	@UpdateTimestamp
	private LocalDateTime updateDate;

	@CreationTimestamp
	private LocalDateTime creationDate;

	@ManyToMany
	// Helps in defining names of Join Table and corresponding columns
	@JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"))
	@JsonBackReference
	private List<Course> courses = new ArrayList<>();

	/**
	 * default constructor as mandated by jpa
	 */
	public Student() {
	}

	/**
	 * @param name
	 */
	public Student(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the passport
	 */
	public Passport getPassport() {
		return passport;
	}

	/**
	 * @param passport
	 *            the passport to set
	 */
	public void setPassport(Passport passport) {
		this.passport = passport;
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
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses
	 *            the courses to set
	 */
	public void addCourse(Course course) {
		this.courses.add(course);
	}

	/**
	 * Remove course
	 * 
	 * @param course
	 */
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + " updateDate=" + updateDate + ", creationDate=" + creationDate
				+ "]";
	}

}
