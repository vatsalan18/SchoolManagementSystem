package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.DTO.TeacherDTO;
import com.school.serviceInterface.TeacherServiceInterface;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherServiceInterface teacherServiceInterface;

    @GetMapping("/all")
    public String getAllteachers(Model model) throws Exception {
        try{
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName());
    		model.addAttribute("welcomeMsg", welcomeMsg);
        	List<TeacherDTO> teachers = teacherServiceInterface.getAllTeacherData();
        	model.addAttribute("teachers",teachers);
            return "teacher-list";
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Error!");
        }

    }
    
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName());
		model.addAttribute("welcomeMsg", welcomeMsg);
        model.addAttribute("teacher", new TeacherDTO());
        return "teacher-form";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public String createteacher(@ModelAttribute TeacherDTO teacher) throws Exception {
        try{
            teacherServiceInterface.save(teacher);
            return "redirect:/teachers/all";
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Error!");
        }

    }
    
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) throws Exception {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName());
		model.addAttribute("welcomeMsg", welcomeMsg);
        TeacherDTO teacher = teacherServiceInterface.findById(id);
        model.addAttribute("teacher", teacher);
        return "teacher-form";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/edit")
    public String updateteacher(@ModelAttribute TeacherDTO teacher) throws Exception{
        try{
            teacherServiceInterface.updateDetails(teacher);
            return "redirect:/teachers/all";
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Error!");
        }
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteteacher(@PathVariable Long id) throws Exception {
        try{
           teacherServiceInterface.delete(id);
           return ResponseEntity.ok("Teacher record deleted successfully!");
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Error!");
        }
    }
}
