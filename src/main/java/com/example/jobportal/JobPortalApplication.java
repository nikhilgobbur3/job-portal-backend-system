package com.example.jobportal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobPortalApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // Sample data loader placeholder; services will be autowired in later implementation
    @Bean
    public CommandLineRunner runner() {
        return args -> {
            // data will be added here in later steps
        };
    }
}
