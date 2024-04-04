package org.example.Pr14;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class GroupController {
    private ArrayList<Group> groups = new ArrayList<>() {{
        add(new Group("IKBO-01-22"));
        add(new Group("IKBO-02-22"));
        add(new Group("IKBO-03-22"));
        add(new Group("IKBO-04-22"));
        add(new Group("IKBO-05-22"));
    }};

    @PostMapping("/groups")
    public void createGroup(@RequestBody Group group) {
        groups.add(group);
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable int id) {
        groups.removeIf(gr -> gr.getId() == id);
    }

    @GetMapping("/groups")
    public ArrayList<Group> getGroups() {
        return groups;
    }

}
