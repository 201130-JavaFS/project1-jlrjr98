package com.revature.security;

import java.security.MessageDigest;

import com.revature.exception.BusinessException;

public class Encrypt {
	
	private Encrypt() {}

	public static String encryptPassword(String unencryptedPassword) throws BusinessException {
		
		StringBuilder encryptedPassword = null;
		
		String algorithm = "SHA";

        byte[] plainText = unencryptedPassword.getBytes();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            messageDigest.reset();
            messageDigest.update(plainText);
            byte[] encodedPassword = messageDigest.digest();

            encryptedPassword = new StringBuilder();
            for (int i = 0; i < encodedPassword.length; i++) {
                if ((encodedPassword[i] & 0xff) < 0x10) {
                	encryptedPassword.append("0");
                }

                encryptedPassword.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (encryptedPassword != null) {
        	return encryptedPassword.toString();
        } else {
        	throw new BusinessException("Password is Null");
        }
        
	}
	
}
