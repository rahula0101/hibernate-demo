package com.example.hibernatedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.repository.CourseRepository;

@RestController
public class CourseDetailsController {

	@Autowired
	CourseRepository courseRepo;

	@GetMapping("/courses/{courseId}")
	public Course getCourseDetails(@PathVariable Long courseId) {

		return courseRepo.findById(courseId);

	}

}
