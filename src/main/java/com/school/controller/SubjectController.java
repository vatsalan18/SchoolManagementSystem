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

import com.school.DTO.SubjectDTO;
import com.school.serviceInterface.SubjectServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectServiceInterface subjectServiceInterface;

    @GetMapping("/all")
    public String getAllsubjects(Model model, HttpSession session) throws Exception {
        try{
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName()).append(authentication.getAuthorities());
    		model.addAttribute("welcomeMsg", welcomeMsg);
        	List<SubjectDTO> subjectDTOs = subjectServiceInterface.getAllSubjectData();
        	model.addAttribute("subjects",subjectDTOs);
            return "subject-list";
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Error!");
        }

    }
    
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName()).append(authentication.getAuthorities());
		model.addAttribute("welcomeMsg", welcomeMsg);
        model.addAttribute("subject", new SubjectDTO());
        return "subject-form";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public String createsubject(@ModelAttribute SubjectDTO subjectDetails) throws Exception {
        try{
            subjectServiceInterface.save(subjectDetails);
            return "redirect:/subjects/all";
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Error!");
        }

    }
    
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) throws Exception {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName()).append(authentication.getAuthorities());
		model.addAttribute("welcomeMsg", welcomeMsg);
        SubjectDTO subjectDTO = subjectServiceInterface.findById(id);
        model.addAttribute("subject", subjectDTO);
        return "subject-form";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/edit")
    public String updatesubject(@ModelAttribute SubjectDTO subjectDetails) throws Exception{
        try{
            subjectServiceInterface.updateDetails(subjectDetails);
            return "redirect:/subjects/all";
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Error!");
        }
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deletesubject(@PathVariable Long id) throws Exception {
        try{
        	System.out.println("inside delete");
           subjectServiceInterface.delete(id);
           return ResponseEntity.ok("Subject deleted successfully!");
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Error!");
        }
    }
}
