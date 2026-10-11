package com.logitrack.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.logitrack.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

// Ensina o Spring  a buscar o usuário na tabela "usuarios"
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return usuarioRepository.findByUsername(username)
				.map(usuario -> User.withUsername(usuario.getUsername())
						.password(usuario.getPassword())
						.roles(usuario.getRole())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
}
