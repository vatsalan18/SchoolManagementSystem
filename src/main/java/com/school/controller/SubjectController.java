package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.DTO.SubjectDTO;
import com.school.serviceInterface.SubjectServiceInterface;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectServiceInterface subjectServiceInterface;

    @GetMapping("/all")
    public List<SubjectDTO> getAllsubjects() throws Exception {
        try{
            return subjectServiceInterface.getAllSubjectData();
        } catch (Exception e) {
            throw new Exception("Error!");
        }

    }

    @PostMapping
    public Long createsubject(@RequestBody SubjectDTO subjectDetails) throws Exception {
        try{
            return subjectServiceInterface.save(subjectDetails);
        } catch (Exception e) {
            throw new Exception("Error!");
        }

    }

    @PutMapping("/{id}")
    public Long updatesubject(@PathVariable Long id, @RequestBody SubjectDTO subjectDetails) throws Exception{
        try{
            return subjectServiceInterface.updateDetails(subjectDetails);
        } catch (Exception e) {
            throw new Exception("Error!");
        }
    }

    @DeleteMapping("/{id}")
    public void deletesubject(@PathVariable Long id) throws Exception {
        try{
           subjectServiceInterface.delete(id);
        } catch (Exception e) {
            throw new Exception("Error!");
        }
    }
}
