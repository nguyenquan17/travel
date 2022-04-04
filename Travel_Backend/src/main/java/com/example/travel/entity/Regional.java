package com.example.travel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="`Regional`")
@Getter
@Setter
@NoArgsConstructor
public class Regional implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "`name_regional`", length = 225,nullable = false )
	private String nameRegional;


	//1 mien -> n thanh pho
	@OneToMany(mappedBy = "regional")
	private List<Tour> tourList;

	@ManyToOne
	@JoinColumn(name = "id_tourType", nullable = false)
	private TourType tourType;

	public Regional(int id) {
		this.id = id;
	}
}
