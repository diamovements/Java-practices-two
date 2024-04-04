package org.example.Pr15.controllers;

import org.example.Pr15.models.Student;
import org.example.Pr15.models.Group;
import org.example.Pr15.repositories.GroupRepository;
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
}
