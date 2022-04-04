package com.example.travel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="`TourType`")
@Getter
@Setter
@NoArgsConstructor
public class TourType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "`name`", length = 225,nullable = false )
	private String name;

	//du lich trong nuoc, ngoai nuoc,du lich bien, du lich rung,...
	@OneToMany(mappedBy = "tourType")
	private List<Regional> regionalList;

	public TourType(int id) {
		this.id = id;
	}
}
