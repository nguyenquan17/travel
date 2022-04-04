package com.example.travel.event;

import org.springframework.context.ApplicationEvent;

public class OnResetPasswordViaEmailEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public OnResetPasswordViaEmailEvent(String email) {
		super(email);
		// TODO Auto-generated constructor stub
		this.email=email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
