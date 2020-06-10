package com.accenture.StudentApp2;

import com.accenture.StudentApp2.model.Student;
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


	@Test
	void contextLoads() {
	}

	@Test
	void getStudentByEmail(){
		List<Student> student = studentService.getByEmail("pbm222");
		Student student1 = student.get(0);
		assertThat(student1.getName()).equals("pbm222");
	}

}
