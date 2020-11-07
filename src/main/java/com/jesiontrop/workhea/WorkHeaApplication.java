package com.jesiontrop.workhea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.jesiontrop.workhea")
@SpringBootApplication
public class WorkHeaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkHeaApplication.class, args);
    }

}
