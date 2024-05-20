package org.example.Pr23.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Pr23.models.Group;
import org.example.Pr23.models.Student;
import org.example.Pr23.repositories.GroupRepository;
import org.example.Pr23.repositories.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class SchedulerService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    private final String backupDirectory = "D:\\Users\\karin\\java\\backup";

    @Scheduled(fixedRate = 180000)
    public void cleanBackupDB() throws Exception {
        log.info("cleaning and backup db started");
        File directory = new File(backupDirectory);
        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (!file.delete()) {
                    log.info("couldn't delete " + file);
                }
            }
        }
        else {
            if (!directory.mkdirs()) {
                log.info("mkdirs is not working");
            }
        }
        backupStudent();
        backupGroup();
        log.info("cleaning and back up db is over");
    }

    public void backupStudent() throws Exception{
        log.info("backup student has started");
        List<Student> students = studentRepository.findAll();
        String filePath = backupDirectory + "/Students_backup.txt";
        FileWriter fileWriter = new FileWriter(filePath);
        ObjectMapper mapper = new ObjectMapper();
        for (Student student : students) {
            fileWriter.write(mapper.writeValueAsString(student) + "\n");
        }
        fileWriter.close();
        log.info("backup student is over");
    }
    public void backupGroup() throws Exception {
        log.info("backup group has started");
        List<Group> groups = groupRepository.findAll();
        String filePath = backupDirectory + "/Groups_backup.txt";
        FileWriter fileWriter = new FileWriter(filePath);
        ObjectMapper mapper = new ObjectMapper();
        for (Group group : groups) {
            group.setStudents(new ArrayList<>());
            fileWriter.write(mapper.writeValueAsString(group) + "\n");
        }
        fileWriter.close();
        log.info("backup group is over");
    }
}
