package com.accenture.StudentApp2.controller;

import com.accenture.StudentApp2.model.Course;
import com.accenture.StudentApp2.service.CourseServiceImpl;
import com.accenture.StudentApp2.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {


    @Autowired
    private CourseServiceImpl courseService;


    @GetMapping("/courses")
    public String getAllCourses(Model model){
        model.addAttribute("courses", courseService.findAll());
        return "courses";
    }

    @GetMapping("/add-course")
    public String getCourseForm(Model model){
        model.addAttribute("course", new Course());
        return "createCourse";
    }

    @PostMapping("/add-course")
    public String saveCourse(Course course, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "createCourse";
        }else {
           courseService.save(course);
            return "redirect:/students";
        }
    }

    @PostMapping("/assign-course/{id}")
    public String assignCourse(@PathVariable Long id){
        courseService.getById(id);
        //TODO: assign course to a logged in user/student
        return "redirect:/courses";
    }

    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteById(id);
        return "redirect:/courses";
    }


}
