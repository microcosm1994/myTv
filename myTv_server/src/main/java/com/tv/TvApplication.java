package com.tv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:xml/*.xml"})
public class TvApplication {

    public static void main(String[] args) {
        SpringApplication.run(TvApplication.class, args);
    }

}
