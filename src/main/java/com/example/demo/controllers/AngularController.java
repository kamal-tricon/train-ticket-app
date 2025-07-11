package com.example.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AngularController {


    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Spring Boot is running!";
    }


    @RequestMapping("/nexturl/**")
    public String serveNextStaticFile() {
        return "forward:/nextui/index.html";
    }

    @RequestMapping("/course/" )
    public String course() {
        return "course/index";
    }
}
