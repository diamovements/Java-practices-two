package org.example.Pr21.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.Pr21.models.Group;
import org.example.Pr21.models.Student;
import org.example.Pr21.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EmailService emailService;

    @Transactional
    public List<Student> findAll() {
        log.info("Getting all students");
        return studentRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> delete(int id) {
        log.info("Deleting student with id " + id);
        return studentRepository.findById(id).map(student -> {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    @Transactional
    public ResponseEntity<Student> create(Student student) {
        emailService.sendEmailToYourSelf("New student", "Object created");
        Student saved = studentRepository.save(student);
        log.info("Creating student with id " + saved.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @Transactional
    public List<Student> getFiltered(
            String firstName,
            String lastName,
            String middleName,
            String groupName
    ) {
        log.info("Getting filtered groups: group - " + groupName + ", firstName - " + firstName + ", lastName - " +
                lastName + ", middleName - " + middleName);
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
