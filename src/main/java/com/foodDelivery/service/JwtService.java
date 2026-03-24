package com.foodDelivery.service;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.foodDelivery.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	 @Value("${jwt.secret}")
	  private String SECRET;
	
	private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

	private Key getKey() {
		 System.out.println("JWT SECRET USED = " + SECRET);
		    System.out.println("SECRET LENGTH = " + SECRET.length());
	    return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	  
	// generate token with claims
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.claim("userId", user.getId())
				.claim("role", user.getRole())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(getKey()).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(getKey())
					.build()
					.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
