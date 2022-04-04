package com.example.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.travel.entity.User;


public interface IUserRepository extends  JpaRepository<User, Integer>{

	public boolean existsByUserName(String userName);
	
	public boolean existsByEmail(String email);
	
	@Query("	SELECT 	active 		"
			+ "	FROM 	User 		"
			+ " WHERE 	email = :email ")
	public User findStatusByEmail(@Param("email") String email);

	
	public User findByUserName(String username);
	
	public User findByEmail(String email);
}
