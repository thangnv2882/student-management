package com.pmmnm.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URLClassLoader;

@SpringBootApplication
public class StudentManagementApplication {
    public static void main(String[] args) {
        Thread.currentThread().setContextClassLoader(URLClassLoader.getSystemClassLoader());
        SpringApplication.run(StudentManagementApplication.class, args);
    }

}
