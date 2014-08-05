package com.omazan.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {

	public static String encrypt(String password) {
		MessageDigest digest = null;
		try {
			digest = java.security.MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		digest.update(password.getBytes());
		byte[] hash = digest.digest();
		return new String(hash);
	}
}
