package org.example.Pr15.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "middleName")
    private String middleName;

    @Id
    @SequenceGenerator(name = "students_seq", sequenceName = "students_sequence", allocationSize = 1)
    @GeneratedValue(generator = "students_seq", strategy = GenerationType.AUTO)
    private int id;

    public Student(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public Student() {

    }

}
