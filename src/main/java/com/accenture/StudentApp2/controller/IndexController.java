package com.accenture.StudentApp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/home")
    public String toIndex(){
        return "index";
    }
}
