package com.example.travel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.travel.entity.User;
import com.example.travel.repository.IUserTokenRepository;
import com.example.travel.services.IEmailServices;
import com.example.travel.services.IUserServices;

@Component
public class EmailSerives implements IEmailServices{
	@Autowired
	private IUserServices userService;
	
	@Autowired
	public JavaMailSender mailSender;
	
	@Autowired
	private IUserTokenRepository registrationUserTokenReponsitory;
	
	@Autowired
	private IUserTokenRepository resetPasswordTokenReponsitory;

	@Override
	public void sendRegistrationUserConfirm(String email) {
		// TODO Auto-generated method stub
		User user = userService.findUserByEmail(email);
		
		String token = registrationUserTokenReponsitory.findByUserId(user.getId());
		
		String cofirmtionUrl ="http://localhost:8080/api/v1/users/activeUser?token=" + token;
		String subject ="Xác Nhận Đăng Ký Account";
		String content="Bạn đã đăng kí thành công. Click vào link dưới đây để kích hoạt tài khoản \n" + cofirmtionUrl;
		
		sendEmail(email, subject, content);
	}
	
	public void sendResetPassWord(String email) throws Exception {
		// TODO Auto-generated method stub
		// tìm user qua email
		User user = userService.findUserByEmail(email);
		 if (user == null) {
		        throw new Exception();
		    }
		String token = resetPasswordTokenReponsitory.findByUserId(user.getId());
		
		String cofirmtionUrl ="http://localhost:3000/auth/resetPassword/" + token;
		String subject ="Thiết lập lại mật khẩu";
		String content="Click vào đây để thay đổi mật khẩu(Nếu không phải bạn xin vui lòng bỏ qua)!! \n" + cofirmtionUrl;
		sendEmail(email, subject, content);
	}
	
	private void sendEmail(final String recipientEmail, final String subject, final String content) {
		SimpleMailMessage message = new  SimpleMailMessage();
		message.setTo(recipientEmail);
		message.setSubject(subject);
		message.setText(content);
		
		mailSender.send(message);
	}

}
