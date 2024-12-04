package com.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.DTO.StudentDTO;
import com.school.entity.Student;
import com.school.repository.StudentRepository;
import com.school.serviceInterface.StudentServiceInterface;

@Service
public class StudentService implements StudentServiceInterface {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Long updateDetails(StudentDTO studentDetails) throws Exception {
        try{
            Student student = studentRepository.findById(studentDetails.getId()).orElseThrow();
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            student.setDivision(studentDetails.getDivision());
            student.setRollNo(studentDetails.getRollNo());
            studentRepository.save(student);
            return student.getId();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Long save(StudentDTO studentDetails) throws Exception {
        try{
            Student student = new Student();
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            student.setDivision(studentDetails.getDivision());
            student.setRollNo(studentDetails.getRollNo());
            studentRepository.save(student);
            return student.getId();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try{
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public List<StudentDTO> getAllStudentData() throws Exception {
        try{
            List<StudentDTO> studentDTOS=new ArrayList<>();
            List<Student> students =studentRepository.findAll();
            for (Student student : students) {
            	StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(student.getId());
                studentDTO.setName(student.getName());
                studentDTO.setEmail(student.getEmail());
                studentDTO.setDivision(student.getDivision());
                studentDTO.setRollNo(student.getRollNo());
                studentDTOS.add(studentDTO);
            }
            return studentDTOS;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    @Override
	public StudentDTO findById(Long id) throws Exception {
		try {
			Student student = studentRepository.findById(id).orElseThrow();
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setDivision(student.getDivision());
            studentDTO.setRollNo(student.getRollNo());
			return studentDTO;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
