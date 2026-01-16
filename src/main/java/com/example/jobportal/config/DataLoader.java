package com.example.jobportal.config;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadSample(UserRepository userRepository, CompanyRepository companyRepository, JobRepository jobRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("adminpass"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);

                User user = new User();
                user.setName("John Doe");
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("userpass"));
                user.setRole(Role.USER);
                userRepository.save(user);
            }

            if (companyRepository.count() == 0) {
                Company c = new Company();
                c.setName("Acme Corp");
                c.setLocation("New York");
                c.setWebsite("https://acme.example.com");
                c.setDescription("A sample company");
                companyRepository.save(c);

                Job j = new Job();
                j.setTitle("Software Engineer");
                j.setDescription("Develop amazing software");
                j.setLocation("Remote");
                j.setJobType("Full-time");
                j.setSalary("80000");
                j.setCompany(c);
                jobRepository.save(j);
            }
        };
    }
}
