package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.serviceInterface.UserServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	@Autowired
	private UserServiceInterface userServiceInterface;

	@Autowired
	private AuthenticationManager authManager;

	@GetMapping("/login")
	public String showLoginPage(Model model) {
		return "loginPage";
	}

	@PostMapping("/auth/login")
	public String handleLogin(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {
		try {
			if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
				model.addAttribute("errorMessage", "Username and Password are required.");
				return "redirect:/login?error"; 
			}
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			session.setAttribute("authentication",authentication);

			return "redirect:/two-factor";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Username and Password are required.");
			return "redirect:/login?error";
		}
	}

	@GetMapping("/two-factor")
	public String showTwoFactorPage(Model model,HttpSession session) {
		Authentication authentication = (Authentication) session.getAttribute("authentication");
		StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName()).append(authentication.getAuthorities());
		model.addAttribute("welcomeMsg", welcomeMsg);
		return "two-factor";
	}

	@PostMapping("/auth/verify")
	public String handleVerification(@RequestParam String code, Model model, HttpSession session) {
		try {
			Authentication authentication = (Authentication) session.getAttribute("authentication");
			boolean isVerify = userServiceInterface.verify(authentication.getName(), code);
			if (isVerify) {
				// Set authentication in the SecurityContextHolder
				SecurityContextHolder.getContext().setAuthentication(authentication);

				// Set the authentication in the session
				session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
				return "redirect:/dashboard";
			} else {
				model.addAttribute("error", "Invalid verification code");
				return "redirect:/two-factor";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login?error";
		}
	}

	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		StringBuilder welcomeMsg = new StringBuilder("Welcome ").append(authentication.getName()).append(authentication.getAuthorities());
		model.addAttribute("welcomeMsg", welcomeMsg);
		return "dashboard";
	}
}
