package com.knk.user.security;

import java.util.Base64.Decoder;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
public class JwtService {

	private final SecretKey secretKey;

	
	public JwtService(@Value("${jwt.secret}") String ourSecretKey) {
		this.secretKey=Keys.hmacShaKeyFor(Decoders.BASE64.decode(ourSecretKey));
	}
	
	private final long expirationTime=1000*60*12;
	
	public String generateToken(String userName) {
		
		return Jwts.builder()
				.subject(userName)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+expirationTime))
				.signWith(secretKey)
				.compact();
	}
	
	public String extractUserName(String token) {
		return Jwts.parser()
		.verifyWith(secretKey)
		.build()
		.parseSignedClaims(token)
		.getPayload()
		.getSubject();
	}
}
