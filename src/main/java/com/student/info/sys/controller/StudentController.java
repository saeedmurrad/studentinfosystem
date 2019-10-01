package com.student.info.sys.controller;

import com.student.info.sys.exception.ResourceNotFoundException;
import com.student.info.sys.model.Classes;
import com.student.info.sys.model.Student;
import com.student.info.sys.repository.ClassRepository;
import com.student.info.sys.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created By Saeed Murrad 10/01/2019
 */

@RestController
@RequestMapping(value = "/")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    @GetMapping("/students/{studentId}")
    public Student retrieveStudent(@PathVariable long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent())
            throw new ResourceNotFoundException("id-" + studentId);
        return student.get();
    }

    @GetMapping("/students")
    public ResponseEntity getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return ResponseEntity.ok().body(studentList);
    }

    @PostMapping("/student")
    public ResponseEntity addClass(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentRepository.save(student));
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();
        student.setStudentId(studentId);
        studentRepository.save(student);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable long studentId) {
        studentRepository.deleteById(studentId);
    }

    @GetMapping("/classes")
    public ResponseEntity getAllClasses() {
        List<Classes> classesList = classRepository.findAll();
        return ResponseEntity.ok().body(classesList);
    }

    @GetMapping("/class")
    public ResponseEntity searchForClass(@RequestParam(name = "className") String className) {
        return ResponseEntity.status(HttpStatus.OK).body(classRepository.findAllByName(className));
    }

    @PostMapping("/class")
    public ResponseEntity addClass(@RequestBody Classes newClass) {
        return ResponseEntity.ok().body(classRepository.save(newClass));
    }

    @PutMapping("/student/class/{classId}")
    public ResponseEntity enrollStudentInClass(@PathVariable Long classId, @RequestBody Student student) {
        Optional<Classes> optionalClasses = classRepository.findById(classId);
        if (optionalClasses.isPresent()) {
            Classes classes = optionalClasses.get();
            List<Student> studentList = (List<Student>) classes.getStudents();
            studentList.add(student);
            classes.setStudents(studentList);
            classRepository.save(classes);
            return ResponseEntity.ok().body("Student enrolled successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/student/{studentId}/class/{classId}")
    public ResponseEntity cancelEnrollmentOfStudent(@PathVariable Long studentId, @PathVariable Long classId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Classes> optionalClasses = classRepository.findById(classId);
        if (optionalStudent.isPresent() && optionalClasses.isPresent()) {
            if (optionalClasses.get().getStudents().contains(optionalStudent.get())) {
                optionalClasses.get().getStudents().remove(optionalStudent.get());
                classRepository.save(optionalClasses.get());
                return ResponseEntity.ok().body("Student enrollment cancelled");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}