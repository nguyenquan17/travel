package com.example.travel.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.dto.UserDTO;
import com.example.travel.services.IUserServices;



@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users")
@Validated
public class UserController {

	@Autowired
	private IUserServices userService;
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> existsUserByEmail(@PathVariable(name = "email") String email) {
		// get entity
		boolean result = userService.existsUserByEmail(email);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/userName/{userName}")
	public ResponseEntity<?> existsUserByUserName(@PathVariable(name = "userName") String userName) {
		// get entity
		boolean result = userService.existsByUserName(userName);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	// Tạo account
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO dto){
		//create user
		userService.createUser(dto.toEntity());
		
		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}
	
	// Active
	@GetMapping("/activeUser")
	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
		userService.activeUser(token);
		
		return new ResponseEntity<>("Active success!", HttpStatus.OK);
	}
	
	// gửi lại 
	@GetMapping("/userRegistrationConfirmRequest")
	public ResponseEntity<?> sendViaEmail(@RequestParam String email){
		userService.sendConfimUserRegistrationViaEmail(email);
		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
		
	}
	
	//gửi reset password Request
	@GetMapping("/resetPassword/Request")
	public ResponseEntity<?> SendresetPasswordViaEmail(@RequestParam String email){
		
		userService.resetPasswordViaEmail(email);
		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
		
	}
	
	// gửi lại reset pasword
	@GetMapping("/resetPassword/resend")
	public ResponseEntity<?> resenPasswordViaEmail(@RequestParam String email){
		userService.sendResetPasswordViaEmail(email);
		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
		
	}
	
	// reset password
	@GetMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestParam String token,@RequestParam String newPassword){
		
		userService.resetPassword(token,newPassword);
		
		return new ResponseEntity<>("Change Password success!", HttpStatus.OK);
	}
	
}
