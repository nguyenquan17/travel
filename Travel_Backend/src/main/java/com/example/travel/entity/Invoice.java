package com.example.travel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="`Invoice`")
@Getter
@Setter
@NoArgsConstructor
public class Invoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//
	@OneToOne
	@JoinColumn(name = "id_tourOrder", referencedColumnName = "id")
	private TourOrder tourOrder;

}
