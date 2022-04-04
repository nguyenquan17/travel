package com.example.travel.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.travel.entity.User;

public interface IUserServices {

	void createUser(User user);

	User findUserByEmail(String email);

	void activeUser(String token);

	void sendConfimUserRegistrationViaEmail(String email);

	void resetPasswordViaEmail(String email);

	void sendResetPasswordViaEmail(String email);

	void resetPassword(String token, String newPassword);

	User findByUserName(String username);

	boolean existsUserByEmail(String email);

	boolean existsByUserName(String userName);
}

