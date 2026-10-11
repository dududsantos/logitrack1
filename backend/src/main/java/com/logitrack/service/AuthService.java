package com.logitrack.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.logitrack.dto.request.LoginRequest;
import com.logitrack.dto.response.TokenResponse;
import com.logitrack.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authManager;
	private final JwtService jwtService;

	public TokenResponse login(LoginRequest request) {
		try {
			// Busca o user e compara a senha com o hash 
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.username(), request.password()));
		} catch (AuthenticationException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos.");
		}

		String token = jwtService.gerarToken(request.username());
		return new TokenResponse(token, "Bearer", jwtService.getExpiracaoMs());
	}
}
