package org.example.Pr16.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Controller
public class HomePageController {
    @GetMapping("/home")
    @ResponseBody
    public String getHome() throws Exception{
        Resource resource = new ClassPathResource("templates/home.html");
        InputStream inputStream = resource.getInputStream();
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}
