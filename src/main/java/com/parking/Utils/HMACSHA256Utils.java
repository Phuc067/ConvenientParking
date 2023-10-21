package com.parking.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class HMACSHA256Utils {

	public static String encode(String inputData, String key) throws InvalidKeyException, NoSuchAlgorithmException
	{
		byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
		byte[] messageByte = inputData.getBytes(StandardCharsets.UTF_8);
		Mac hmacsha256 = Mac.getInstance("HmacSHA256");
		hmacsha256.init(new SecretKeySpec(keyByte, "HmacSHA256"));
		
		byte[] hashMessage = hmacsha256.doFinal(messageByte);
		String hex = String.format("%064x", new BigInteger(1, hashMessage));
		hex = hex.replace("-", "").toLowerCase();
		return hex;
	}
}
