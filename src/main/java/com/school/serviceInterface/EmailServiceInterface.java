package com.school.serviceInterface;

public interface EmailServiceInterface {

	void sendEmail(String to, String subject, String body, String cc) throws Exception;
}
