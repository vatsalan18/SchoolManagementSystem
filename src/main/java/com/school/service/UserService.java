package com.school.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.school.entity.User;
import com.school.entity.UserAuthCode;
import com.school.repository.UserAuthRepository;
import com.school.repository.UserRepository;
import com.school.serviceInterface.EmailServiceInterface;
import com.school.serviceInterface.UserServiceInterface;

public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailServiceInterface emailServiceInterface;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public boolean authenticate(String username, String password) throws Exception{
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new Exception("Incorrect username/password");
        }
        if(user.getPassword() != null && passwordEncoder.matches(password, user.getPassword())){
            // Generate the 2FA code
            String twoFactorCode = generateTwoFactorCode();
            UserAuthCode userAuthCode = userAuthRepository.findByUsername(username);
            if(userAuthCode != null){
                userAuthCode.setCode(twoFactorCode);
                userAuthCode.setExpiryTime(LocalDateTime.now().plusMinutes(10));
            }else{
                userAuthCode = new UserAuthCode();
                userAuthCode.setUsername(username);
                userAuthCode.setCode(twoFactorCode);
                userAuthCode.setExpiryTime(LocalDateTime.now().plusMinutes(10));
            }
            userAuthRepository.save(userAuthCode);
            
            StringBuilder msg = new StringBuilder("Your 2FA Code")
            		.append("Your 2FA code is: ").append(twoFactorCode);
            
            String subject = "School managment app OTP for login";
            
            // Send the 2FA code via email
            emailServiceInterface.sendEmail(user.getUsername(), subject,msg.toString(), null);
            return true;
        }
        return false;
    }

    // Method to generate a random 6-digit 2FA code
    private String generateTwoFactorCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Generates a 6-digit code
        return String.valueOf(code);
    }

    @Override
    public boolean verify(String username, String code){
        if(code == null || code.isEmpty()){
            return false;
        }
        UserAuthCode userAuthCode = userAuthRepository.findByUsername(username);
        if(userAuthCode != null && (userAuthCode.getCode() != null ||  userAuthCode.getCode().isEmpty())){
            if(userAuthCode.getCode().equals(code) && LocalDateTime.now().isBefore(userAuthCode.getExpiryTime())){
                return true;
            }
        }
        return false;
    }
}
