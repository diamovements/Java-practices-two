package org.example.Pr17.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.Pr17.models.Group;
import org.example.Pr17.models.Student;
import org.example.Pr17.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
    @GetMapping("")
    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return studentRepository.findById(id).map(student -> {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/filtered")
    public List<Student> getFiltered(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "middleName", required = false) String middleName,
            @RequestParam(name = "groupName", required = false) String groupName
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> crit = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = crit.from(Student.class);

        if (firstName != null) {
            crit.where(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
        }
        if (lastName != null) {
            crit.where(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
        }
        if (middleName != null) {
            crit.where(criteriaBuilder.like(root.get("middleName"), "%" + middleName + "%"));
        }
        if (groupName != null) {
            Join<Student, Group> join = root.join("group");
            crit.where(criteriaBuilder.like(join.get("name"), "%" + groupName + "%"));
        }
        crit.select(root);
        return entityManager.createQuery(crit).getResultList();
    }
}
