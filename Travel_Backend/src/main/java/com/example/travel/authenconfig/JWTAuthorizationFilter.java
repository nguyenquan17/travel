package com.example.travel.authenconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.travel.services.impl.JWTTokenService;
import com.example.travel.services.impl.UserDetailServiceImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

//import com.example.travel.services.impl.JWTTokenService;
import org.springframework.web.filter.OncePerRequestFilter;


public class JWTAuthorizationFilter extends GenericFilterBean {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		Authentication authentication = JWTTokenService.parseTokenToUserInformation((HttpServletRequest) request);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}


//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		// parse the token
//		String jwt = parseJwt(request);
//		String username = jwtTokenService.getUserNameFromJwtToken(jwt);
//
//
//
//		if(username != null) {
//			System.out.println(username);
//			UserDetails userDetails = userDetailService.loadUserByUsername(username);
//			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////			return  new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
//		}
//
//
////		Authentication authentication = JWTTokenService.parseTokenToUserInformation((HttpServletRequest) request);
//
//
//		filterChain.doFilter(request,response);
//	}
//	private String parseJwt(HttpServletRequest request) {
//		String headerAuth = request.getHeader("Authorization");
//		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//			return headerAuth.substring(7, headerAuth.length());
//		}
//		return null;
//	}
}