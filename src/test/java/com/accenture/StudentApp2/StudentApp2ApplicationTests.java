package com.accenture.StudentApp2;

import com.accenture.StudentApp2.controller.StudentController;
import com.accenture.StudentApp2.model.Course;
import com.accenture.StudentApp2.model.Student;
import com.accenture.StudentApp2.service.CourseServiceImpl;
import com.accenture.StudentApp2.service.StudentServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class StudentApp2ApplicationTests {

    @MockBean
    private StudentServiceImpl studentService;

    @MockBean
    private CourseServiceImpl courseService;

    @Autowired
    private StudentController studentController;

    @Test
    void contextLoads() {
    	assertThat(studentController).isNotNull();
    }

    @Test
    void getStudentByEmail() {
        Student student = studentService.getByEmail("pbm222");
        assertThat(student.getName()).isEqualTo("Inna");
    }

    @Test
    public void getStudentByNameOrSurname(){
        List<Student> students = studentService.getListByNameOrSurname("i");
        assertThat(students.size() > 0);
        assertThat(students.get(0).getSurname().equals("Meskauska"));

    }

    @Test
    public void getCourseByTitle() {
        Course course = new Course();
        course.setTitle("Performance");
        courseService.save(course);
        assertThat(courseService.getListByTitle("per").size() > 0);
    }


}
