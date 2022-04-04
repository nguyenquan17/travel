package com.example.travel.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name = "`TourDetail`")
@Getter
@Setter
@NoArgsConstructor
public class TourDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name = "title" ,length = 255,nullable = false)
	private String title;
	
	 @Column(name = "description" ,nullable = false)
	private String description;
	
	 @Column(name = "schedule" ,nullable = false)
	private String schedule;

	
	@Column(name = "day_start",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dayStart;
	
	@Column(name = "vehicle" ,length = 255,nullable = false)
	private String vehicle;
	
	@Column(name = "departure_from" ,length = 255,nullable = false)
	private String departureFrom;
	
	@Column(name = "price" ,nullable = false)
	private Long price;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Column(name = "notes" ,length = 255,nullable = false)
	private String notes;
	
	@Column(name = "star")
	private int star = 5;


	@ManyToOne
	@JoinColumn(name="id_user", nullable = false)
	private User manager;

	//1 tourDetail -> n image
	@OneToMany(mappedBy = "idTourDetail")
	private List<Image> imageList;

	//1 tour -> 1 thanh pho
	@OneToOne()
	@JoinColumn(name = "id_tour")
	private Tour tourName;

	//1 tour -> 1 mien
	@OneToOne()
	@JoinColumn(name = "id_regional")
	private Regional regional;

	//Trong nuoc, ngoai nuoc, du lich bien, du lich rung,...
	@OneToOne()
	@JoinColumn(name = "id_tourType")
	private TourType tourType;

	public TourDetail(int id) {
		this.id = id;
	}
}
