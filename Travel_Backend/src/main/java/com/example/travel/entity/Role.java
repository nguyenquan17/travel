package com.example.travel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="`Role`")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name = "name_role" ,length = 255,nullable = false)
	private String nameRole;

	public Role(String nameRole) {
		this.nameRole = nameRole;
	}

	//	public Role(int id, String nameRole) {
//		this.id = id;
//		this.nameRole = nameRole;
//	}


	 
	 
	
}
