package org.example.Pr15.models;


import jakarta.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Column(name = "grp")
    private String group;

    @Id
    @SequenceGenerator(name = "group_seq", sequenceName = "group_sequence", allocationSize = 1)
    @GeneratedValue(generator = "group_seq", strategy = GenerationType.AUTO)
    private int id;

    public Group(String group) {
        this.group = group;
    }

    public Group() {

    }
}
