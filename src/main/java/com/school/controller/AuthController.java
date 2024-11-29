package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.serviceInterface.UserServiceInterface;


@RestController
public class AuthController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/auth/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Placeholder for authentication logic
        try {
            if (userServiceInterface.authenticate(username,password)) {
                return "redirect:/two-factor";
            } else {
                model.addAttribute("error", "Invalid credentials");
                return "login";
            }
        } 
        catch (Exception e) {
            throw new RuntimeException("Error!");
        }
    }

    @GetMapping("/two-factor")
    public String showTwoFactorPage() {
        return "two-factor";
    }

    @PostMapping("/auth/verify")
    public String handleVerification(@RequestParam String username,@RequestParam String code, Model model) {
        // Placeholder for 2FA verification logic
        try {
            if (userServiceInterface.verify(username, code)) {
                return "dashboard";
            } else {
                model.addAttribute("error", "Invalid verification code");
                return "two-factor";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error!");
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}
