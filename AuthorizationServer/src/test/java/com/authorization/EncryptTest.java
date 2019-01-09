package com.authorization;

import org.junit.Test;

import com.authorization.utils.TokenGenerateUtil;

public class EncryptTest {
	
	@Test
	public void randomKeyGenerate() {
		TokenGenerateUtil randomKeyGenerateUtil = new TokenGenerateUtil();
		String token = randomKeyGenerateUtil.randomKey(30);
		token = randomKeyGenerateUtil.tokenGenerate();
		System.out.println(token);
	}

}
