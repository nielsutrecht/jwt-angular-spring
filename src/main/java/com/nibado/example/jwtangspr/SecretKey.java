package com.nibado.example.jwtangspr;

import java.security.SecureRandom;

public class SecretKey {
	public static final byte[] BYTES;
	
	static {
		BYTES = new byte[256];
		SecureRandom random = new SecureRandom();
		random.nextBytes(BYTES);
	}
	
	private SecretKey() {
		
	}
}
