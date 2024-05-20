package org.example.Pr23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"org.example.Pr23"})
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@EnableTransactionManagement
@EnableAsync
public class AppRunner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppRunner.class);
        app.run(args);
    }
}
