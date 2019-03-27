package com.example.hibernatedemo;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Student;
import com.example.hibernatedemo.repository.CourseRepository;
import com.example.hibernatedemo.repository.PassportRepository;
import com.example.hibernatedemo.repository.ReviewRepository;
import com.example.hibernatedemo.repository.StudentRepository;
import com.example.hibernatedemo.service.JpqlService;

@SpringBootApplication
public class HibernateDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	CourseRepository courseRepo;

	@Resource
	StudentRepository studentRepo;

	@Resource
	PassportRepository passportRepo;

	@Resource
	ReviewRepository reviewRepo;
	
	@Resource
	JpqlService jpqlService;

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Search for a course
		Course course = courseRepo.findById(4002L);
		logger.info("course -> {} ", course);

		// Delete an existing course
		courseRepo.deleteById(4012L);
		Course deletedCourse = courseRepo.findById(4012L);
		logger.info("course -> {} ", deletedCourse);

		// Insert a new course
		Course newCourse = new Course("Java");
		courseRepo.save(newCourse);
		logger.info("course -> {} ", newCourse);

		// Update existing course
		Course existingCourse = courseRepo.findById(4002L);
		existingCourse.setName("SQL");
		courseRepo.save(existingCourse);
		logger.info("course -> {} ", courseRepo.findById(4002L));

		List<Course> courseList = courseRepo.findAll_jpql();
		// Show all the entries jpql
		logger.info("Courses JPQL -> {} ", courseList);

		List<Course> courseListTyped = courseRepo.findAll_typed_jpql();
		// Show all the entries jpql
		logger.info("Courses Typed Query -> {} ", courseListTyped);

		List<Course> courseListNamedQuery = courseRepo.findAll_named_query();
		// Show all the entries jpql
		logger.info("Courses Named Query -> {} ", courseListNamedQuery);

		List<Course> courseListNativeQuery = courseRepo.findAll_native_query();
		// Show all the entries jpql
		logger.info("Courses Named Query -> {} ", courseListNativeQuery);

		List<Course> courseJpql = courseRepo.findById_jpql();
		// Show all the entries jpql
		logger.info("Courses JPQL -> {} ", courseJpql);

		List<Course> courseTyped = courseRepo.findById_typed_jpql();
		// Show all the entries jpql
		logger.info("Courses Typed Query -> {} ", courseTyped);

		List<Course> courseNamedQuery = courseRepo.findById_named_query();
		// Show all the entries jpql
		logger.info("Courses Named Query -> {} ", courseNamedQuery);

		List<Course> courseNativeQuery = courseRepo.findById_native_query();
		// Show all the entries jpql
		logger.info("Courses Named Query -> {} ", courseNativeQuery);

		// One to one relationships
		// By default fetch type is always EAGER
		Long newStudentId = studentRepo.saveStudentWithPassport();

		Student newStudent = studentRepo.findById(newStudentId);

		logger.info("Get new student details -> {} ", newStudent);

		// One to many relationships
		// By default for Many to One side the fetch type is eager
		// By default for One to Many side the fetch type is lazy, hence @Transactional
		// need to be used
		courseRepo.addReviewsForCourse(4007L);

		logger.info("Get Reviews for a course ");
		courseRepo.getReviewsForCouse(4007L);

		logger.info("Get course from Review");
		Course reviewCourse = reviewRepo.findCourseDetails(3004L);
		logger.info("Course details for the review -> {} ", reviewCourse);

		logger.info("Many to many relationship samples");
		// This is always going to be lazy fetch by default
		studentRepo.retriveStudentAndCourses();

		studentRepo.saveStudentWithCourse();

		// Courses without students

		List<Course> coursesWithoutStudents = courseRepo.courses_without_students();
		logger.info("courses without students -> {}", coursesWithoutStudents);

		// Courses with more than two students

		List<Course> coursesWithNStudents = courseRepo.courses_with_atleast_n_students(2);
		logger.info("courses with N students -> {}", coursesWithNStudents);

		// Courses in order of size of students

		List<Course> coursesWithOrderBy = courseRepo.courses_order_by_students_size();
		logger.info("courses with order by students -> {}", coursesWithOrderBy);
		
		// List students with having passport number with specific pattern
		List<Student> studentWithPassportPattern = studentRepo.student_with_passport_contains_pattern();
		logger.info("students with having passport number with specific pattern -> {}", studentWithPassportPattern);

		// Use Jpql to implement inner join
		jpqlService.join();
		
		// Use Jpql to implement left join
		jpqlService.join_left();
		
		// Use Jpql to implement right join
		jpqlService.join_right();
		
		// Use Jpql to implement cross join
		jpqlService.join_cross();
	}

}
