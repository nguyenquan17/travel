package com.example.travel.dto;



import com.example.travel.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	private String token;

	private String refreshToken;

	private int id;

    private String userName;
    
    private String fullName;
    
    private String email;
    
    private String phone;
    
    private String address;

	private Role role ;

	private Boolean active;

	public LoginDTO(String token, String userName, String fullName, String email, String phone, String address,
			Role role, Boolean active) {

		this.token = token;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.active = active;
	}



//	public LoginDTO(String token, String userName, String fullName, String email, String phone, String address, int role, Boolean active) {
//		this.token = token;
//		this.userName = userName;
//		this.fullName = fullName;
//		this.email = email;
//		this.phone = phone;
//		this.address = address;
//		this.role = role;
//		this.active = active;
//	}


//	public LoginDTO(String s, String userName, String email, String fullName, String address, String phone, int id, Boolean active) {
//	}
}
