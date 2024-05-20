package org.example.Pr19.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.example.Pr19.models.Group;
import org.example.Pr19.models.Student;
import org.example.Pr19.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Group> findAll() {
        log.info("Getting all groups");
        return groupRepository.findAll();
    }
    public ResponseEntity<Group> create(Group group) {
        Group saved = groupRepository.save(group);
        log.info("Creating group with name " + saved.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    public ResponseEntity<?> delete(int id) {
        log.info("Deleting group with id " + id);
        return groupRepository.findById(id).map(g -> {
            groupRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public List<Group> getFiltered(
            String firstName,
            String lastName,
            String middleName,
            String name
    ) {
        log.info("Getting filtered groups: group - " + name + ", firstName - " + firstName + ", lastName - " +
                lastName + ", middleName - " + middleName);
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
