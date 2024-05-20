package org.example.Pr21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableAspectJAutoProxy
public class AppRunner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppRunner.class);
        app.run(args);
    }
}
