package com.school.serviceInterface;

import java.util.List;

import com.school.DTO.SubjectDTO;

public interface SubjectServiceInterface {
    Long updateDetails(SubjectDTO subjectDetails) throws Exception;

    Long save(SubjectDTO studentDetails) throws Exception;

    void delete(Long id) throws Exception;

    List<SubjectDTO> getAllSubjectData() throws Exception;

	SubjectDTO findById(Long id) throws Exception;
}
