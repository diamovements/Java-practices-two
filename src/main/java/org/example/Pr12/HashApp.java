package org.example.Pr12;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.security.MessageDigest;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HashApp {
    public static String input_file = "input.txt";
    public static String output_file = "output.txt";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HashApp.class, args).close();
    }

    @PostConstruct
    public static void onStart() throws Exception {
        if (input_file == null) {
            throw new IllegalArgumentException("File not given");
        }
        File file = new File(input_file);
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("null");
            }
            return;
        }

        String hashcode = getHash(file);
        writeHashToFile(hashcode);
        Files.delete(file.toPath());
    }

    @PreDestroy
    public static void onStop() throws Exception {
        File file = new File(output_file);
        if (file.exists()) {
            Files.delete(file.toPath());
        }
    }

    private static String getHash(File file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashcode = digest.digest(Files.readAllBytes(file.toPath()));
        StringBuilder res = new StringBuilder();
        for (byte h : hashcode) {
            res.append(Integer.toHexString(0xff & h));
        }
        return res.toString();
    }

    private static void writeHashToFile(String hashcode) throws Exception {
        File file = new File(output_file);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(hashcode);
        }
    }

}