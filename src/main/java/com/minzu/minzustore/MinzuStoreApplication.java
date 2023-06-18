package com.minzu.minzustore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.minzu.minzustore.mapper")
public class MinzuStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinzuStoreApplication.class, args);
    }

}
