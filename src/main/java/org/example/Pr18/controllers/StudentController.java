package org.example.Pr18.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.Pr18.models.Group;
import org.example.Pr18.models.Student;
import org.example.Pr18.repositories.StudentRepository;
import org.example.Pr18.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


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
