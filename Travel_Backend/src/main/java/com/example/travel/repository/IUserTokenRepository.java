package com.example.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.travel.entity.UserToken;


public interface IUserTokenRepository extends JpaRepository<UserToken, Integer>{

	public UserToken findByUserToken(String userToken);
	
	public boolean existsByUserToken(String userToken);
	
//	@Transactional
//	@Modifying
//	@Query("	SELECT 	userToken	"
//			+ "	FROM 	UserToken "
//			+ " WHERE 	idUser.id = :userId")
//	public String findByUserId(@Param("userId") int userId);

	@Transactional
//	@Modifying
	@Query(value = "	SELECT 	user_token	"
			+ "	FROM 	User_Token "
			+ " WHERE 	id_user = :userId",nativeQuery = true)
	public String findByUserId(@Param("userId") int userId);

	@Transactional 
	@Modifying
	@Query("	DELETE 							"
			+ "	FROM 	UserToken 	"
			+ " WHERE 	idUser.id = :userId")
	public void deleteByUserId(@Param("userId") int userId);

}
