package org.example.Pr22.services;


import lombok.AllArgsConstructor;
import org.example.Pr21.services.EmailService;
import org.example.Pr22.models.Group;
import org.example.Pr22.models.Student;
import org.example.Pr22.repositories.GroupRepository;
import org.example.Pr22.repositories.StudentRepository;
import org.example.Pr22.services.GroupService;
import org.example.Pr22.services.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class GroupServiceTest {
    @InjectMocks
    GroupService groupService;
    @Mock
    GroupRepository groupRepository;

    @Mock
    EmailService emailService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAll() {
        Group group = new Group();
        group.setGroup_id(1);
        group.setName("IKBO-12-22");

        List<Group> groups = new ArrayList<>();
        groups.add(group);
        when(groupRepository.findAll()).thenReturn(groups);

        List<Group> got = groupRepository.findAll();
        assertEquals(groups, got);
    }

    @Test
    public void create() {
        Group group = new Group();
        group.setGroup_id(1);
        group.setName("IKBO-12-22");

        when(groupRepository.save(group)).thenReturn(group);
        ResponseEntity<Group> responseEntity = groupService.create(group);
        verify(emailService).sendEmailToYourSelf(eq("New group"), eq("Object created"));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(group, responseEntity.getBody());
    }

    @Test
    public void delete() {
        Group group = new Group();
        group.setGroup_id(1);
        group.setName("IKBO-12-22");
        groupService.create(group);
        int id = 1;
        ResponseEntity<?> deleteResponse = groupService.delete(id);
        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());
    }
}
