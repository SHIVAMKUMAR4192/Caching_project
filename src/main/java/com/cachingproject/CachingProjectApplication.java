package com.cachingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CachingProjectApplication.class, args);
    }

}
