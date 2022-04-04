package com.example.travel.authenconfig;


import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.travel.entity.User;
import com.example.travel.services.IUserServices;
import com.example.travel.services.impl.JWTTokenService;



public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	@Autowired
	private IUserServices userService;

	public JWTAuthenticationFilter(String url, AuthenticationManager authenticationManager,IUserServices userService) {
		// TODO Auto-generated constructor stub
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
		this.userService = userService;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getParameter("username"),
						request.getParameter("password"),
						Collections.emptyList()));
	}

	   @Override
	    protected void successfulAuthentication(
	    		HttpServletRequest request, 
	    		HttpServletResponse response, 
	    		FilterChain chain, 
	    		Authentication authResult) throws IOException, ServletException {
	    	// infor user
		   User user = userService.findByUserName(authResult.getName());
		   
		   try {
			JWTTokenService.addJWTTokenAndUserInfoToBody(response, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
