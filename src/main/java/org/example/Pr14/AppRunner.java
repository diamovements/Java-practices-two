package org.example.Pr14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableWebMvc
public class AppRunner {
    public static void main(String[] args) {
        SpringApplication a = new SpringApplication(org.example.Pr15.AppRunner.class);
        a.run(args);
    }
}

