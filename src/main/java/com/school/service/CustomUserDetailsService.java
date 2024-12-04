package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.school.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.school.entity.User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Incorrect username/password");
        }
        return User.builder()
                   .username(user.getUsername())
                   .password(user.getPassword())
                   .roles(user.getRole())
                   .build();
    }
}
