package com.cours.allo.docteur.utils;

import java.security.SecureRandom;

/**
 * Token
 */
public class Token {
	private final static String letters = "abcdefghijklmnopqrstuvxyz";
	private final static String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
	private final static String numbers = "0123456789";
	private final static String specialChar = "!~@#$%^&*()/?{}[]_-+=<>";
	private final static String allChar = letters + capitalLetters + numbers + specialChar;

	public static String generateRandomToken(int size) {
		String randomAllChar;
		StringBuilder ret;
		Integer idx;
		Integer rdmIdx;
		SecureRandom rand;

		idx = 0;
		randomAllChar = shuffleString(allChar);
		rand = new SecureRandom();
		ret = new StringBuilder();

		while (idx < size) {
			rdmIdx = rand.nextInt(size);
			ret.append(randomAllChar.charAt(rdmIdx));
			idx++;
		}

		return ret.toString();
	}

	private static String shuffleString(String str) {
		final Integer strLength = str.length();
		Integer idx;
		Integer rdmIdx;
		SecureRandom rand;

		idx = 0;
		rand = new SecureRandom();

		while (idx < strLength) {
			rdmIdx = rand.nextInt(idx + 1);
			str = swapString(str, idx, rdmIdx);
			idx++;
		}

		return str;
	}

	private static String swapString(String str, int idx1, int idx2) {
		char[] strCharArray;
		String ret;
		char tmp;

		strCharArray = str.toCharArray();

		tmp = strCharArray[idx1];
		strCharArray[idx1] = strCharArray[idx2];
		strCharArray[idx2] = tmp;

		ret = new String(strCharArray);

		return ret;
	}

}