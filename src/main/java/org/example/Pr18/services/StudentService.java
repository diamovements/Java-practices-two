package org.example.Pr18.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.Pr18.models.Group;
import org.example.Pr18.models.Student;
import org.example.Pr18.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public ResponseEntity<?> delete(int id) {
        return studentRepository.findById(id).map(student -> {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    public ResponseEntity<Student> create(Student student) {
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    public List<Student> getFiltered(
            String firstName,
            String lastName,
            String middleName,
            String groupName
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
