package me.llss.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Get Gravatar Image
 * 
 * @version 1.0 2013/05/02
 * @author Acris
 * 
 */
public class Gravatar {
	/**
	 * MD5加密
	 * 
	 * @param array
	 * @return
	 */
	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(
					1, 3));
		}
		return sb.toString();
	}

	public static String md5Hex(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return hex(md.digest(message.getBytes("CP1252")));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	/**
	 * 获取头像
	 * 
	 * @param mail
	 * @param size
	 * @return
	 */
	public static String getAvatar(String mail, int size) {
		String hashMail = md5Hex(mail);
		String avatar = "<img src='http://www.gravatar.com/avatar/" + hashMail
				+ "?s=" + size + "&d=mm' />";
		return avatar;
	}
}
