package com.school.controller;

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

import com.school.DTO.StudentDTO;
import com.school.serviceInterface.StudentServiceInterface;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceInterface studentServiceInterface;

    @GetMapping("/all")
    public String getAllStudents(Model model) throws Exception {
        try{
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName()).append(authentication.getAuthorities());
    		model.addAttribute("welcomeMsg", welcomeMsg);
            model.addAttribute("students", studentServiceInterface.getAllStudentData());
            return "student-list";
        } catch (Exception e) {
            throw new Exception("Error!");
        }

    }
    
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName()).append(authentication.getAuthorities());
		model.addAttribute("welcomeMsg", welcomeMsg);
        model.addAttribute("student", new StudentDTO());
        return "student-form";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")    
    public String createStudent(@ModelAttribute StudentDTO studentDetails) throws Exception {
        try{
            studentServiceInterface.save(studentDetails);
            return "redirect:/students/all";
        } catch (Exception e) {
            throw new Exception("Error!");
        }

    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) throws Exception {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName()).append(authentication.getAuthorities());
		model.addAttribute("welcomeMsg", welcomeMsg);
        StudentDTO studentDTO = studentServiceInterface.findById(id);
        model.addAttribute("student", studentDTO);
        return "student-form";
    }
    
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/edit")
    public String updateStudent(@ModelAttribute StudentDTO studentDetails) throws Exception{
        try{
            studentServiceInterface.updateDetails(studentDetails);
            return "redirect:/students/all";
        } catch (Exception e) {
            throw new Exception("Error!");
        }
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) throws Exception {
        try{
        	System.out.println("inside delete student");
           studentServiceInterface.delete(id);
           return ResponseEntity.ok("Student record deleted successfully!");
        } catch (Exception e) {
            throw new Exception("Error!");
        }
    }
}
