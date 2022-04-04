package com.example.travel.services.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.travel.entity.RefreshToken;
import com.example.travel.entity.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.travel.dto.LoginDTO;
import com.example.travel.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JWTTokenService {
	private static final long EXPIRATION_TIME = 864000000; //864000000
	private static final String SECRET = "2362001";
	private static final String PREFIX_TOKEN = "Bearer";
	private static final String AUTHORIZATION  = "Authorization";

	private static UserDetailServiceImpl userDetailService;
	private static RefreshTokenService refreshTokenService;

	public JWTTokenService(UserDetailServiceImpl userDetailService, RefreshTokenService refreshTokenService) {
		JWTTokenService.userDetailService = userDetailService;
		JWTTokenService.refreshTokenService = refreshTokenService;
	}

//	private static UserDetailServiceImpl userDetailService;


//	@Autowired
//	private UserDetailServiceImpl userDetailService;


	public static void addJWTTokenAndUserInfoToBody(HttpServletResponse response, User user) throws Exception {
		// TODO Auto-generated method stub
		String JWT = Jwts.builder()
				.setSubject(user.getUserName())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
		// convert user entity to dto
		LoginDTO loginDTO = new LoginDTO(
				user.getActive().equals(true) ? JWT : null,
				refreshToken.getToken(),
				user.getId(),
				user.getUserName(),
				user.getFullName(),
				user.getEmail(),
				user.getPhone(),
				user.getAddress(),
				new Role(user.getRole().getId(),user.getRole().getNameRole()),
				user.getActive());
		
		// convert obj sang json
		ObjectWriter objectWriter= new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(loginDTO);
		
		// return json
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}

	public String generateTokenFromUsername(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}
//	public String getUserNameFromJwtToken(String token) {
//		return Jwts.parser()
//				.setSigningKey(SECRET)
//				.parseClaimsJws(token.replace(PREFIX_TOKEN, ""))
//				.getBody()
//				.getSubject();
//	}
	public static Authentication parseTokenToUserInformation(@NotNull HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getHeader(AUTHORIZATION);

		if(token == null) {
			return null;
		}
		// parse the token
		String username = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token.replace(PREFIX_TOKEN, ""))
				.getBody()
				.getSubject();



		if(username != null) {
			System.out.println(username);
			UserDetails userDetails = userDetailService.loadUserByUsername(username);
			return  new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
		}else {
			return null;
		}
	}





}
