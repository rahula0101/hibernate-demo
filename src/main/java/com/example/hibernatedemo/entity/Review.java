package com.example.hibernatedemo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_review_gen")
	private Long id;

	private String description;

	private String rating;

	@ManyToOne
	private Course course;
	
	@UpdateTimestamp
	private LocalDateTime updateDate;

	@CreationTimestamp
	private LocalDateTime creationDate;

	/**
	 * default constructor as mandated by jpa
	 */
	public Review() {
	}

	/**
	 * @param name
	 */
	public Review(String description, String rating) {
		super();
		this.description = description;
		this.rating = rating;
	}

	/**
	 * @return the passportNumber
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Review [id=" + id + ", description=" + description + ", rating=" + rating + ", updateDate=" + updateDate
				+ ", creationDate=" + creationDate + "]";
	}
}
