package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
