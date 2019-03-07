package com.marstalk.ayncannotationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * demo depends on this:
 * https://www.baeldung.com/spring-async
 */

@EnableAsync
@SpringBootApplication
public class AyncannotationdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AyncannotationdemoApplication.class, args);
    }

}
