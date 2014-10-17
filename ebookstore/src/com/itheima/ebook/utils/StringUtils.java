package com.itheima.ebook.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.UUID;

public class StringUtils {
	
	/**
	 * 获得32长度UUID值
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获得两次子目录
	 * @param filename
	 * @return
	 */
	public static String getDir(String filename){
		int hashCode = filename.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = hashCode >>> 4 & 0xf;
		return "/"+dir1 + "/" + dir2;
	}
	
	/**
	 * 进行MD5加密
	 * @param value
	 * @return
	 */
	public static String getMD5Value(String value){
		
		try {
			//1 获得摘要算法工具类
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			//2加密,结果为十进制
			byte[] md5ValueByteArr = messageDigest.digest(value.getBytes());
			//3将10进制 转换成 16 进制
			BigInteger bigInteger = new BigInteger(1,md5ValueByteArr);
			
			return bigInteger.toString(16);
		} catch (Exception e) {
			// 如果有异常，不进行加密
			return value;
		}
	}

	
	public static void main(String[] args) {
		
		System.out.println(getMD5Value("1234"));
		
		
	}
}
