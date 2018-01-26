package com.rokid.skill.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5签名工具类
 * 
 * @author Bassam
 *
 */
public final class MD5Utils {

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	private MD5Utils() {

	}

	/**
	 * 进行签名校验
	 * 
	 * @param secret
	 * @param body
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String sign(String secret, String body) throws IOException, Exception {
		// 第一步：对于Body进行md5
		byte[] bytes = encryptMD5(body);
		String bodyDate = byte2hex(bytes);
		// 第二步：secret+MD5(Body)
		StringBuilder query = new StringBuilder();
		query.append(secret);
		query.append(bodyDate);
		// 第三步：使用MD5加密
		byte[] signDate = encryptMD5(query.toString());
		// 第四步：把二进制转化为大写的十六进制
		return byte2hex(signDate);
	}

	private static byte[] encryptMD5(String data) throws IOException, Exception {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(data.getBytes("UTF-8"));
			return bytes;
		} catch (Exception e) {
			throw e;
		}
	}

	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

	public static String getMD5String(String s) throws NoSuchAlgorithmException {
		return getMD5String(s.getBytes());
	}

	public static boolean checkPassword(String password, String md5PwdStr) throws NoSuchAlgorithmException {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

	public static String getFileMD5String(File file) throws IOException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		if (md == null) {
			throw new NoSuchAlgorithmException();
		}
		InputStream fis;
		fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			md.update(buffer, 0, numRead);
		}
		fis.close();
		return bufferToHex(md.digest());
	}

	public static String getMD5String(byte[] bytes) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		if (md == null) {
			throw new NoSuchAlgorithmException();
		}
		md.update(bytes);
		return bufferToHex(md.digest());
	}

	public static String getMD5StringUpper(String s) throws NoSuchAlgorithmException {
		return getMD5String(s.getBytes()).toUpperCase();
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuilder stringbuffer = new StringBuilder(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuilder stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>>
												// 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
		char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// long begin = System.currentTimeMillis();
		//
		// File file = new File("D:/resource/android480_1.png");
		// String md5 = md5Util.getFileMD5String(file);
		//
		// long end = System.currentTimeMillis();
		// System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) +
		// "s");
		int i = 2;
		while (i-- > 0) {
			System.out.println(i);
		}

		System.out.println(MD5Utils.getMD5StringUpper("test"));
	}
}
