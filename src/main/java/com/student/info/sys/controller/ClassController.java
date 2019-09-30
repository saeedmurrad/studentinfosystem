package com.student.info.sys.controller;

import com.student.info.sys.model.Classes;
import com.student.info.sys.model.Student;
import com.student.info.sys.repository.ClassRepository;
import com.student.info.sys.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    @GetMapping("/classes")
    public Page<Classes> getAllClasses(Pageable pageable) {
        return classRepository.findAll(pageable);
    }

    @PostMapping("/classes")
    public Classes createClass(@Valid @RequestBody Classes classes) {
        return classRepository.save(classes);
    }

}
