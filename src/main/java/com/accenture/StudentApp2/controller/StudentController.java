package com.accenture.StudentApp2.controller;

import com.accenture.StudentApp2.model.Student;
import com.accenture.StudentApp2.service.CourseServiceImpl;
import com.accenture.StudentApp2.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;


    @GetMapping("/students")
    public String getAllStudents(Model model){
        model.addAttribute("students", studentService.findAll());
        //model.addAttribute("courses", courseService.findAll());
        return "students";
    }


    @GetMapping("/add-student")
    public String getStudentForm(Model model){
        model.addAttribute("student", new Student());
        return "createStudent";
    }

    @PostMapping("/add-student")
    public String saveStudent(@Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "createStudent";
        }else {
            studentService.save(student);
            return "redirect:/students";
        }
    }

    @GetMapping("/edit-student/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        Optional<Student> studentToEdit = studentService.getById(id);
        model.addAttribute("student", studentToEdit.get());
        return "editStudent";
    }

    @PostMapping("/edit-student/{id}")
    public String editStudentData(@PathVariable Long id, Student studentToEdit, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "editStudent";
        }else {
            studentToEdit.setId(id);
            studentService.update(studentToEdit);
            return "redirect:/students";
        }
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteById(id);
        return "redirect:/students";
    }

}
