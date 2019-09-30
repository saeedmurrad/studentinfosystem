package com.student.info.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/about")
    public String about(){

        return "A sample student information system in which student can enroll to one or many classes and can cancel enrollment.";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(){
        return "Student Information System API v1";
    }
}
