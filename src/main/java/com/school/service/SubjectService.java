package com.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.DTO.SubjectDTO;
import com.school.entity.Subject;
import com.school.repository.SubjectRepository;
import com.school.serviceInterface.SubjectServiceInterface;

@Service
public class SubjectService implements SubjectServiceInterface {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public Long updateDetails(SubjectDTO subjectDetails) throws Exception {
		try {
			Subject subject = subjectRepository.findById(subjectDetails.getId()).orElseThrow();
			subject.setName(subjectDetails.getName());
			subject.setCode(subjectDetails.getCode());
			subjectRepository.save(subject);
			return subject.getId();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public Long save(SubjectDTO subjectDetails) throws Exception {
		try {
			Subject subject = new Subject();
			subject.setName(subjectDetails.getName());
			subject.setCode(subjectDetails.getCode());
			subjectRepository.save(subject);
			return subject.getId();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		try {
			subjectRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<SubjectDTO> getAllSubjectData() throws Exception {
		try {
			List<SubjectDTO> subjectDTOs = new ArrayList<>();
			List<Subject> subjects = subjectRepository.findAll();
			for (Subject subject : subjects) {
				SubjectDTO subjectDTO = new SubjectDTO();
				subjectDTO.setCode(subject.getCode());
				subjectDTO.setName(subject.getName());
				subjectDTO.setId(subject.getId());
				subjectDTOs.add(subjectDTO);
			}
			return subjectDTOs;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public SubjectDTO findById(Long id) throws Exception {
		try {
			Subject subject = subjectRepository.findById(id).orElseThrow();
			SubjectDTO subjectDTO = new SubjectDTO();
			subjectDTO.setCode(subject.getCode());
			subjectDTO.setName(subject.getName());
			subjectDTO.setId(subject.getId());
			return subjectDTO;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
