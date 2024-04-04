package org.example.Pr16.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
public class Group {
    @Id
    private int group_id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group")
    private List<Student> students;
}
