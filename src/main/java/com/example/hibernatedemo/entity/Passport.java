package com.example.hibernatedemo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "passport")
public class Passport {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_passport_gen")
	private Long id;

	private String passportNumber;

	// Student has been declared as owning side of relationship
	// Note that for this mappedBy has to be declared in the entity "Passport"
	// mappedBy = Name of the attribute in the student entity
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	private Student student;

	@UpdateTimestamp
	private LocalDateTime updateDate;

	@CreationTimestamp
	private LocalDateTime creationDate;

	/**
	 * default constructor as mandated by jpa
	 */
	public Passport() {
	}

	/**
	 * @param name
	 */
	public Passport(String passportNumber) {
		super();
		this.passportNumber = passportNumber;
	}

	/**
	 * @return the passportNumber
	 */
	public String getPassportNumber() {
		return passportNumber;
	}

	/**
	 * @param passportNumber
	 *            the passportNumber to set
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Passport [id=" + id + ", passportNumber=" + passportNumber + ", updateDate=" + updateDate
				+ ", creationDate=" + creationDate + "]";
	}
}
