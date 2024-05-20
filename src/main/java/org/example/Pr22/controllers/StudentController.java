package org.example.Pr22.controllers;

import org.example.Pr22.models.Student;
import org.example.Pr22.services.EmailService;
import org.example.Pr22.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return studentService.create(student);
    }
    @GetMapping("")
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return studentService.delete(id);
    }
    @GetMapping("/filtered")
    public List<Student> getFiltered(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "middleName", required = false) String middleName,
            @RequestParam(name = "groupName", required = false) String groupName
    ) {
        return studentService.getFiltered(firstName, lastName, middleName, groupName);
    }

}
