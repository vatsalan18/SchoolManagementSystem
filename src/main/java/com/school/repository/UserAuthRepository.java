package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.UserAuthCode;

public interface UserAuthRepository extends JpaRepository<UserAuthCode, Long> {
    UserAuthCode findByUsername(String username);
}
