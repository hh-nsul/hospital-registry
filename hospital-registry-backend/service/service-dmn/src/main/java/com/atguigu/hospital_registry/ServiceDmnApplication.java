package com.atguigu.hospital_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
public class ServiceDmnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDmnApplication.class, args);
    }
}
