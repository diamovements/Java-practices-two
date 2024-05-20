package org.example.Pr17.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.Pr17.models.Group;
import org.example.Pr17.models.Student;
import org.example.Pr17.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<Group> create(@RequestBody Group group) {
        Group savedGroup = groupRepository.save(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }
    @GetMapping
    public List<Group> getAll() {
        return (List<Group>) groupRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return groupRepository.findById(id).map(group -> {
            groupRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtered")
    public List<Group> getFiltered(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "middleName", required = false) String middleName,
            @RequestParam(name = "name", required = false) String name
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> crit = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = crit.from(Group.class);
        if (firstName != null || lastName != null || middleName != null) {
            Join<Group, Student> join = root.join("students");
            if (firstName != null) {
                crit.where(criteriaBuilder.like(join.get("firstName"), "%" + firstName + "%"));
            }
            if (lastName != null) {
                crit.where(criteriaBuilder.like(join.get("lastName"), "%" + lastName + "%"));
            }
            if (middleName != null) {
                crit.where(criteriaBuilder.like(join.get("middleName"), "%" + middleName + "%"));
            }
        }
        if (name != null) {
            crit.where(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        crit.select(root);
        return entityManager.createQuery(crit).getResultList();
    }
}
