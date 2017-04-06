package com.cn.law.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {
		public static String md5(String input)  {
			String result = input;
			if (input != null) {
				try {
					MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
				
						md.update(input.getBytes("utf-8"));		
				BigInteger hash = new BigInteger(1, md.digest());
				result = hash.toString(16);
				while (result.length() < 32) {
					result = "0" + result;
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
}
