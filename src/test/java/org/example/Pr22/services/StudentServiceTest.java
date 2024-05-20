package org.example.Pr22.services;

import org.example.Pr22.models.Student;
import org.example.Pr22.repositories.StudentRepository;
import org.example.Pr22.services.EmailService;
import org.example.Pr22.services.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @InjectMocks
    StudentService studentService;

    @Mock
    EmailService emailService;

    @Mock
    StudentRepository studentRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAll() {
        Student student = new Student();
        student.setFirstName("Lucas");
        student.setLastName("Don");
        student.setId(1);
        student.setMiddleName("Sinclair");

        List<Student> students = new ArrayList<>();
        students.add(student);
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> got = studentService.findAll();

        assertEquals(students, got);
    }

    @Test
    public void create() {
        Student student = new Student();
        student.setFirstName("Lucas");
        student.setLastName("Don");
        student.setMiddleName("Sinclair");
        student.setId(1);

        when(studentRepository.save(student)).thenReturn(student);
        ResponseEntity<Student> responseEntity = studentService.create(student);
        verify(emailService).sendEmailToYourSelf(eq("New student"), eq("Object created"));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    public void delete() {
        Student student = new Student();
        student.setFirstName("Lucas");
        student.setLastName("Don");
        student.setMiddleName("Sinclair");
        student.setId(1);
        studentService.create(student);
        int id = 1;
        ResponseEntity<?> deleteResponse = studentService.delete(id);
        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());
    }
}
