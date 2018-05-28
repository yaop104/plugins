package com.sme.util;

import java.io.InputStream;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 字符串工具类
 * 
 * @author hhb
 * @version 1.0
 */
public class StringUtil {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字符串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(byteToHexString(b[i]));
		}

		return sb.toString();
	}

	/**
	 * 转换字节数为16进制字符串
	 * 
	 * @param b
	 *            byte数值
	 * @return 16进制字符串
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;

		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 获取指定字符串的MD5编码
	 * 
	 * @param original
	 *            字符串
	 * @return MD5编码
	 */
	public static String MD5Encode(String original) {
		String ret = null;

		try {
			ret = new String(original);
			MessageDigest md = MessageDigest.getInstance("MD5");
			ret = byteArrayToHexString(md.digest(ret.getBytes()));
		} catch (Exception ex) {
			// empty
		}

		return ret;
	}

	/**
	 * 获得0-9的随机数字符串
	 * 
	 * @param length
	 *            返回字符串的长度
	 * @return String
	 */
	public static String getRandomNumber(int length) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < length; i++) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}

	/**
	 * 获得0-9,a-z,A-Z范围的随机字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return String
	 */
	public static String getRandomChar(int length) {
		char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };

		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(chr[random.nextInt(62)]);
		}

		return buffer.toString();
	}

	/**
	 * 判断字符串数组中是否包含某字符串
	 * 
	 * @param substring
	 *            某字符串
	 * @param source
	 *            源字符串数组
	 * @return 包含则返回true，否则返回false
	 */
	public static boolean isContains(String substring, String[] source) {
		if (source == null || source.length == 0) {
			return false;
		}

		for (int i = 0; i < source.length; i++) {
			String aSource = source[i];
			if (aSource.equals(substring)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param str
	 *            某字符串
	 * @return 为null或为空串则返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 判断字符是否为数字串
	 * 
	 * @param str
	 *            某字符串
	 * @return 为数字串则返回true，否则返回false
	 */
	public static boolean isNumber(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		char cs[] = str.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			char c = cs[i];
			if (c > '9') {
				return false;
			}
			if (c < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 *            字符串
	 * @return 首字符大写后的字符串
	 */
	public static String upFirstChar(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 *            字符串
	 * @return 首字符大写后的字符串
	 */
	public static String lowerFirstChar(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 字符串数组转成列表
	 * 
	 * @param arr
	 * @return
	 */
	public static List<String> StringsToList(String[] arr) {
		List<String> strList = null;
		if (null == arr) {
			return strList;
		}
		strList = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			strList.add(arr[i]);
		}
		return strList;
	}

	/**
	 * inputstream解析成string
	 * 
	 * @param in
	 * @return
	 */
	public static String inputStream2String(InputStream in) {
		try {
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 把字串变为指定长度，用指定值实录空位
	 * 
	 * @param line
	 * @return
	 * @throws Exception
	 */
	public static String fixStringToLength(String numStr, int length,
			char addChar) {
		if (length <= 0) {
			return "";
		}
		if (numStr.length() == length) {
			return numStr;
		} else if (numStr.length() > length) {
			return numStr.substring(0, length);
		}
		char bytes[] = new char[length];
		char cs[] = numStr.toCharArray();
		int fromId = length - cs.length;

		for (int i = 0; i < fromId; i++) {
			bytes[i] = addChar;
		}
		for (int i = 0; i < cs.length; i++) {
			bytes[fromId] = cs[i];
			fromId++;
		}

		return new String(bytes, 0, fromId);
	}

	/**
	 * 生成token
	 * @param hasHyphen
	 * @return
	 */
	public static String generateUUID(boolean hasHyphen) {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		if (!hasHyphen) {
			str = str.replaceAll("-", "");
		}

		return str;
	}

	/**
	 *订单号
	 * @param uid
	 * @return
	 */
	public static String generateOrderId(Integer uid) {
		long epoch = System.currentTimeMillis();
		long temp = Math.round(Math.random() * 8999 + 1000);
		return String.format("%d%d%d", epoch, uid, temp);
	}

	public static String timeStampString(){
		DateFormat dateTimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strBeginDate = dateTimeformat.format(new Date());
		return strBeginDate;
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.MD5Encode("062395451"));
	}

}
