package com.school.serviceInterface;

public interface UserServiceInterface {

    boolean authenticate(String username, String password) throws Exception;

    boolean verify(String username, String code);

	void twoFactorAuthCodeGeneration(String username);
}
