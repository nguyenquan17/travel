package com.example.travel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name ="`Tour`")
@Setter
@Getter
@NoArgsConstructor
public class Tour implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "`name`", length = 225,nullable = false )
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "id_regional",nullable = false)
	private Regional regional;

	public Tour(int id) {
		this.id = id;
	}
}
