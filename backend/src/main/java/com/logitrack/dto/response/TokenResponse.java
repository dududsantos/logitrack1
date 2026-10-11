package com.logitrack.dto.response;

public record TokenResponse(
		String token,
		String tipo,
		long expiraEmMs ) {
}
