package com.school.serviceInterface;

import java.util.List;

import com.school.DTO.TeacherDTO;

public interface TeacherServiceInterface {
    Long updateDetails(TeacherDTO teacherDetails) throws Exception;

    Long save(TeacherDTO teacherDetails) throws Exception;

    void delete(Long id) throws Exception;

    List<TeacherDTO> getAllTeacherData() throws Exception;

	TeacherDTO findById(Long id) throws Exception;
}
