package com.student.info.sys.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/about")
    public String about(){

        return "about";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(){
        return "student info api v1";
    }
}
