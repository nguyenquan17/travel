package com.example.travel.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="`User`")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "`fullname`" ,length = 255,nullable = false)
    private String fullName;
	
    @Column(name = "`email`", length = 255,nullable = false)
    private String email;
    
    @Column(name = "`phone`", length = 255,nullable = false)
    private String phone;
    
    @Column(name = "`address`", length = 255,nullable = false)
    private String address;
    
    @Column(name = "`username`", length = 255,nullable = false)
    private String userName;
    
    @Column(name = "`password`", length = 255,nullable = false)
    private String password;
    
    @Column(name = "`active`",nullable = false)
    private Boolean active = false;

    //1 manager -> create n tour
	@OneToMany(mappedBy = "manager", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<TourDetail> tourDetailList;

    //1 customer -> have n orders
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<TourOrder> tourOrderList;
    
    @OneToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role role;

//	public User(int id, String fullName, String email, String phone, String address, String username, String password) {
//
//		this.id = id;
//		this.fullName = fullName;
//		this.email = email;
//		this.phone = phone;
//		this.address = address;
//		this.userName = username;
//		this.password = password;
//
//	}

    public User(int id,String userName,String email,String password, String fullName,  String phone, String address, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public User(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}

