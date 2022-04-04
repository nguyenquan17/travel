package com.example.travel.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.travel.services.IEmailServices;


@Component
public class SendRegistrationUserConfimViaEmailListener implements ApplicationListener<OnSendRegistrationUserConfimViaEmailEvent> {

	@Autowired
	private IEmailServices emailService;
	
	@Override
	public void onApplicationEvent(OnSendRegistrationUserConfimViaEmailEvent event) {
		// TODO Auto-generated method stub
		sendConfirmViaEmail(event.getEmail());
	}
	
	private void sendConfirmViaEmail(String email) {
		emailService.sendRegistrationUserConfirm(email);
	}

}
