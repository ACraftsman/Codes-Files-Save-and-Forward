package com.example.cfsf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.cfsf.mapper")
@SpringBootApplication
public class CfsfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfsfApplication.class, args);
    }

}
