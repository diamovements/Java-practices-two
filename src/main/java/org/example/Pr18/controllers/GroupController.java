package org.example.Pr18.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.Pr18.models.Group;
import org.example.Pr18.models.Student;
import org.example.Pr18.repositories.GroupRepository;
import org.example.Pr18.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Group> create(@RequestBody Group group) {
        return groupService.create(group);
    }
    @GetMapping
    public List<Group> getAll() {
        return groupService.findAll();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return groupService.delete(id);
    }

    @GetMapping("/filtered")
    public List<Group> getFiltered(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "middleName", required = false) String middleName,
            @RequestParam(name = "name", required = false) String name
    ) {
        return groupService.getFiltered(firstName, lastName, middleName, name);
    }
}
