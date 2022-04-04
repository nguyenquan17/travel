package com.example.travel.services;

public interface IEmailServices {

	void sendRegistrationUserConfirm(String email);
	
	void sendResetPassWord(String email) throws Exception;
}
