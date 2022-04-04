package com.example.travel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="`User_Token`")
@Setter
@Getter
@NoArgsConstructor
public class UserToken implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_token" ,length = 255,nullable = false)
	private String userToken;

 	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private User idUser;

	public UserToken(String userToken, User idUser) {
		this.userToken = userToken;
		this.idUser = idUser;
	}
	

}
