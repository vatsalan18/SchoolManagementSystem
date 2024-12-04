package com.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.DTO.TeacherDTO;
import com.school.entity.Teacher;
import com.school.repository.TeacherRepository;
import com.school.serviceInterface.TeacherServiceInterface;

@Service
public class TeacherService implements TeacherServiceInterface {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Long updateDetails(TeacherDTO teacherDetails) throws Exception {
        try{
            Teacher teacher = teacherRepository.findById(teacherDetails.getId()).orElseThrow();
            teacher.setName(teacherDetails.getName());
            teacher.setSubject(teacher.getSubject());
            teacherRepository.save(teacher);
            return teacher.getId();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Long save(TeacherDTO teacherDetails) throws Exception {
        try{
            Teacher teacher = new Teacher();
            teacher.setName(teacherDetails.getName());
            teacher.setSubject(teacherDetails.getSubject());
            teacherRepository.save(teacher);
            return teacher.getId();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try{
            teacherRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public List<TeacherDTO> getAllTeacherData() throws Exception {
        try{
            List<TeacherDTO> teacherDTOs=new ArrayList<>();
            List<Teacher> teachers =teacherRepository.findAll();
            for (Teacher teacher : teachers) {
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setId(teacher.getId());
                teacherDTO.setName(teacher.getName());
                teacherDTO.setSubject(teacher.getSubject());
                teacherDTOs.add(teacherDTO);
            }
            return teacherDTOs;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    @Override
	public TeacherDTO findById(Long id) throws Exception {
		try {
			Teacher teacher = teacherRepository.findById(id).orElseThrow();
			TeacherDTO teacherDTO = new TeacherDTO();
			teacherDTO.setId(teacher.getId());
            teacherDTO.setName(teacher.getName());
            teacherDTO.setSubject(teacher.getSubject());
			return teacherDTO;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
