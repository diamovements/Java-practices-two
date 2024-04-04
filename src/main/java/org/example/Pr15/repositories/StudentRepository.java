package org.example.Pr15.repositories;

import org.example.Pr15.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
