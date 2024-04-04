package org.example.Pr14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Group {
    private String groupName;
    private int id;
    private int count = 0;

    public int getId() {
        return id;
    }

    public Group(String groupName) {
        this.groupName = groupName;
        this.id = count++;
    }

    public Group() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

