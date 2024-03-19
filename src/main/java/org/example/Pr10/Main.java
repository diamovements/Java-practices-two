package org.example.Pr10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bean names: ");
        String name;
        while (sc.hasNext()) {
            name = sc.next();
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            Knight knight = (Knight) applicationContext.getBean(name);
            knight.fight();
        }
    }
}

