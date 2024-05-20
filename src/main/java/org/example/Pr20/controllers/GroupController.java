package org.example.Pr20.controllers;

import org.example.Pr20.models.Group;
import org.example.Pr20.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
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
