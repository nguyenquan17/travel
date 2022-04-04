package com.example.travel.event;

import org.springframework.context.ApplicationEvent;

public class OnSendRegistrationUserConfimViaEmailEvent extends ApplicationEvent {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public OnSendRegistrationUserConfimViaEmailEvent(String email) {
		super(email);
		this.email = email;
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
}
