package com.artemzi;

import com.artemzi.configs.ConfigCondition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Conditional;

@Conditional(ConfigCondition.class)
@SpringBootApplication
public class SupervisorBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupervisorBackendApplication.class, args);
    }
}
