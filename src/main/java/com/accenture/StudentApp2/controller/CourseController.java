package com.accenture.StudentApp2.controller;

import com.accenture.StudentApp2.model.Course;
import com.accenture.StudentApp2.model.Student;
import com.accenture.StudentApp2.service.CourseServiceImpl;
import com.accenture.StudentApp2.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;


    @GetMapping("/courses")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courses";
    }

    @GetMapping("/add-course")
    public String getCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "createCourse";
    }

    @PostMapping("/add-course")
    public String saveCourse(Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createCourse";
        } else {
            courseService.save(course);
            return "redirect:/course/courses";
        }
    }

    @GetMapping("/search")
    public String searchCourse(Model model, @RequestParam String title) {
        List<Course> courses = courseService.getListByTitle(title);
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/assign-course/{id}")
    public ModelAndView assignCourse(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/course/courses");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean courseAssigned = studentService.assignCourseToStudent(id, auth.getName());

        if (courseAssigned) {
            modelAndView.addObject("assign", "You already have this course!");
        } else {
            modelAndView.addObject("assign", "ASSIGNED");
        }

        System.out.println("Course exists: " + courseAssigned);

        return modelAndView;
    }

    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/course/courses";
    }


}
