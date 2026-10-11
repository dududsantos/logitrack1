package com.logitrack.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final SecretKey chave;
	private final long expiracaoMs;

	public JwtService(@Value("${jwt.secret}") String secret,
			@Value("${jwt.expiration-ms}") long expiracaoMs) {
		this.chave = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.expiracaoMs = expiracaoMs;
	}

	public String gerarToken(String username) {
		Date agora = new Date();
		return Jwts.builder()
				.subject(username)
				.issuedAt(agora)
				.expiration(new Date(agora.getTime() + expiracaoMs))
				.signWith(chave)
				.compact();
	}

	
	public String extrairUsername(String token) {
		return Jwts.parser()
				.verifyWith(chave)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

	public long getExpiracaoMs() {
		return expiracaoMs;
	}
}
