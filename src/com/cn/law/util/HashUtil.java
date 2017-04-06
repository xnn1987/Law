package com.cn.law.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashUtil {
//    Public Shared Function TimeStamp() As Double
//    Return TimeStamp(Now)
//End Function
//
//Public Shared Function TimeStamp(ByVal currDate As DateTime) As Double
//    Dim span As TimeSpan = (currDate - New DateTime(1970, 1, 1, 0, 0, 0, 0).ToLocalTime())
//    Return span.TotalSeconds
//End Function

		// md5 算法
		final static String[] hexDigits = {
		    "0", "1", "2", "3", "4", "5", "6", "7",
		    "8", "9", "a", "b", "c", "d", "e", "f"};

		public static String byteToHexString(byte b) {
		    int n = b;
		    if (n < 0) {
		      n = 256 + n;
		    }
		    int d1 = n / 16;
		    int d2 = n % 16;
		    return hexDigits[d1] + hexDigits[d2];
		}


		/**
		 * 转换字节数组为16进制字串
		 * @param b 字节数组
		 * @return 16进制字串
		 */
		 public static String byteArrayToString(byte[] b) {
		  StringBuffer resultSb = new StringBuffer();
		  for (int i = 0; i < b.length; i++) {
		    resultSb.append(byteToHexString(b[i]));//若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
		    //resultSb.append(byteToNumString(b[i]));//使用本函数则返回加密结果的10进制数字字串，即全数字形式
		  }
		  return resultSb.toString();
		}

		 private static String byteToNumString(byte b) {

			    int _b = b;
			    if (_b < 0) {
			      _b = 256 + _b;
			    }

			    return String.valueOf(_b);
			  }

		 
		 public static String MD5_utf8(String text) {
			MessageDigest msgDigest = null;
			try
			{
			  msgDigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
			  //return "System does not support MD5 algorithm.";
			}
			try
			{
			  msgDigest.update(text.getBytes("utf-8"));
			}
			catch (UnsupportedEncodingException e)
			{
			  //return "System does not support your  EncodingException.";
			}
			
			byte[] bytes = msgDigest.digest();
			
			String md5Str = new String(byteArrayToString(bytes));
			return md5Str;
		 }

		 
		 public static String time_stamp(){
			 return (System.currentTimeMillis()/1000)+"";
		 }
		 // Left(StrProcess.MD5_UTF8(StrProcess.MD5_UTF8(Left(TimeStamp() & "", 3)) & "|" & username & "|" & StrProcess.MD5_UTF8(GetSetting("mobileclient_key")) & "|" & hash_add), 8)
		 
		 public static String formhash(String username, String hash_add){
		 	return MD5_utf8(MD5_utf8(time_stamp().substring(0,3)) +"|" + username +"|" + MD5_utf8("law.client")+"|"+hash_add).substring(0,8);
		 }
	
		 //	long x=System.currentTimeMillis()/1000;
		    //formhash("test","131252");
}
