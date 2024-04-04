package org.example.Pr14;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private ArrayList<Student> students = new ArrayList<>(){{
        add(new Student("Arina", "Kuznetsova", "Olegovna"));
        add(new Student("Dmitriy", "Leonov", "Vladimirovich"));
        add(new Student("Maxim", "Marinin", "Sergeevich"));
        add(new Student("Lada", "Malinovskaya", "Kirillovna"));
        add(new Student("Mark", "Alexeev", "Danilovich"));
    }};

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        students.add(student);
    }
    @RequestMapping("/students")
    public ArrayList<Student> getStudents() {
        return students;
    }
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        students.removeIf(st -> st.getId() == id);
    }
}
