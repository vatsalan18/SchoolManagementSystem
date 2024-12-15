package com.school.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.entity.User;
import com.school.entity.UserAuthCode;
import com.school.repository.UserAuthRepository;
import com.school.repository.UserRepository;
import com.school.serviceInterface.EmailServiceInterface;
import com.school.serviceInterface.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {
	
	private static Random random = new Random();
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
            twoFactorAuthCodeGeneration(username);
            return true;
        }
        return false;
    }

    @Override
	public void twoFactorAuthCodeGeneration(String username) {
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
		
		StringBuilder msg = new StringBuilder("Your 2FA Code is ")
				.append(twoFactorCode);
		System.out.println("msg****"+msg.toString());
		String subject = "School managment app OTP for login";
		
		// Send the 2FA code via email
		try {
			emailServiceInterface.sendEmail(username, subject,msg.toString(), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    // Method to generate a random 6-digit 2FA code
    private String generateTwoFactorCode() {
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
