package com.yunsseong.chuckbodymainserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ChuckBodyMainServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChuckBodyMainServerApplication.class, args);
    }

}
