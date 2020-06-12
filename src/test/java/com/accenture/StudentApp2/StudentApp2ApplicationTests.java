package com.accenture.StudentApp2;

import com.accenture.StudentApp2.model.Course;
import com.accenture.StudentApp2.model.Student;
import com.accenture.StudentApp2.service.CourseServiceImpl;
import com.accenture.StudentApp2.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentApp2ApplicationTests {

	@Autowired private StudentServiceImpl studentService;
	@Autowired private CourseServiceImpl courseService;


	@Test
	void contextLoads() {
	}

	@Test
	void getStudentByEmail(){
		Student student = studentService.getByEmail("pbm222");
		assertThat(student.getName()).isEqualTo("Inna");
	}

	@Test
	void getCourseByTitle(){
		Course course = new Course();
		course.setTitle("Performance");
		courseService.save(course);

		assertThat(courseService.getListByTitle("per").size() > 0);
	}



}
