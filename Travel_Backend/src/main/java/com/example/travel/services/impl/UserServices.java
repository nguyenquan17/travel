package com.example.travel.services.impl;

import java.util.UUID;

import com.example.travel.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import com.example.travel.entity.Role;
import com.example.travel.entity.User;
import com.example.travel.entity.UserToken;
import com.example.travel.event.OnResetPasswordViaEmailEvent;
import com.example.travel.event.OnSendRegistrationUserConfimViaEmailEvent;
import com.example.travel.repository.IUserRepository;
import com.example.travel.repository.IUserTokenRepository;

import com.example.travel.services.IUserServices;

@Component
@Transactional
public class UserServices implements IUserServices {
	
	@Autowired
	private IUserRepository userReponsitory;

	@Autowired
	private IRoleRepository iRoleRepository;

	@Autowired
	private IUserTokenRepository userTokenRepositorys;

	
	@Autowired
	private IUserTokenRepository resetPasswordTokenReponsitory;

	// thông báo nhận event
	@Autowired
	private ApplicationEventPublisher evenPublisher;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
	
		//Encoder password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(new Role(2,"User"));
		
		//tạo user
		userReponsitory.save(user);
		
		// tạo token
		createNewregistrationUserToken(user);
		
		
		// send email
		sendConfimUserRegistrationViaEmail(user.getEmail());
	}
	
	private void createNewregistrationUserToken(User user) {
		//Create new token for cofirm Registration
		final String newToken = UUID.randomUUID().toString();
		
		UserToken userToken=  new UserToken(newToken, user);
		
		userTokenRepositorys.save(userToken);
	};
	
	public void sendConfimUserRegistrationViaEmail(String email) {
		evenPublisher.publishEvent(new OnSendRegistrationUserConfimViaEmailEvent(email));
	}
	
	public User findUserByEmail(String email) {
		return userReponsitory.findByEmail(email);
	}

	@Override
	public void activeUser(String token) {
		// TODO Auto-generated method stub
		UserToken userToken = userTokenRepositorys.findByUserToken(token);
		
		User user = userToken.getIdUser();
		user.setActive(true);
		
		// remove token
		userTokenRepositorys.deleteByUserId(userToken.getId());
		
	}

	@Override
	public void resetPasswordViaEmail(String email) {
		// TODO Auto-generated method stub
		//tìm user qua email
		User user = findUserByEmail(email);
		//delete token
		resetPasswordTokenReponsitory.deleteByUserId(user.getId());
		//tạo token mới
		createNewPassworToken(user);
		//gửi token đến email
		sendResetPasswordViaEmail(email);
	}

	private void createNewPassworToken(User user) {
		// tạo new token
		final String newToken = UUID.randomUUID().toString();
		
		UserToken resetuserToken = new UserToken(newToken, user);
		
		resetPasswordTokenReponsitory.save(resetuserToken);
	}
	
	public void sendResetPasswordViaEmail(String email) {
		evenPublisher.publishEvent(new OnResetPasswordViaEmailEvent(email));
	}

	@Override
	public void resetPassword(String token, String newPassword) {
		// TODO Auto-generated method stub
		// lay token
		UserToken resetPasswordToken = resetPasswordTokenReponsitory.findByUserToken(token);
		
		// change password
		User user = resetPasswordToken.getIdUser();
		// mã hóa mk
		user.setPassword(passwordEncoder.encode(newPassword));
		//luu xuong database
		userReponsitory.save(user);
		
		// xóa token
		resetPasswordTokenReponsitory.deleteByUserId(resetPasswordToken.getId());
		
		
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		//Check user tồn tại qua username
//		User user = userReponsitory.findByUserName(username);
//		if(user == null) {
//			throw new UsernameNotFoundException(username);
//		}
//		System.out.println(user.getRole().getNameRole());
//		return new org.springframework.security.core.userdetails.User(user.getUserName(),
//				user.getPassword(), true, true, true, true,
//				AuthorityUtils.createAuthorityList(user.getRole().getNameRole()));
//	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userReponsitory.findByUserName(username);
	}

	@Override
	public boolean existsUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userReponsitory.existsByEmail(email);
	}

	@Override
	public boolean existsByUserName(String userName) {
		// TODO Auto-generated method stub
		return userReponsitory.existsByUserName(userName);
	}
}
