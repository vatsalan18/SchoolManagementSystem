package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
