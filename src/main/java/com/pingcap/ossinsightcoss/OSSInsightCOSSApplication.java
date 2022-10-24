package com.pingcap.ossinsightcoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OSSInsightCOSSApplication {
    public static void main(String[] args) {
        SpringApplication.run(OSSInsightCOSSApplication.class, args);
    }
}
