package com.example.travel.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.travel.services.IEmailServices;

@Component
public class ResetPasswordViaEmailListener implements ApplicationListener<OnResetPasswordViaEmailEvent>{

	@Autowired
	private IEmailServices emailService;
	
	@Override
	public void onApplicationEvent(OnResetPasswordViaEmailEvent event) {
		// TODO Auto-generated method stub
		try {
			resetPassword(event.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void resetPassword(String email) throws Exception {
		emailService.sendResetPassWord(email);
	}
}
