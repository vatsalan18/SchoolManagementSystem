package com.school.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.school.entity.User;
import com.school.repository.UserRepository;


@Component
public class Dataloader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insert some data manually if it doesn't exist
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setUsername("Admin");
            user1.setPassword("$2a$10$kYAdTC470hH3o.TZuVeK1OIOf5tzhpYVapRWDM6jAz7UqfL1DnxdK");
            user1.setRole("admin");
            userRepository.save(user1);
            
            User user2 = new User();
            user2.setUsername("Viewer");
            user2.setPassword("$2a$10$kYAdTC470hH3o.TZuVeK1OIOf5tzhpYVapRWDM6jAz7UqfL1DnxdK");
            user2.setRole("view");
            userRepository.save(user2);

        }
    }
}
