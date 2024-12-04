package com.school.serviceInterface;

import java.util.List;

import com.school.DTO.StudentDTO;

public interface StudentServiceInterface {
    Long updateDetails(StudentDTO studentDetails) throws Exception;

    Long save(StudentDTO studentDetails) throws Exception;

    void delete(Long id) throws Exception;

    List<StudentDTO> getAllStudentData() throws Exception;

	StudentDTO findById(Long id) throws Exception;
}
