package com.sme.util;
/**
 * smart app update
 * @author haoy
 *
 */
public class SauDiffUtil {

	/**
	 * 本地方法 根据旧apk和新apk生成差异包
	 * @param oldApk 旧apk路径
	 * @param newApk 新apk路径
	 * @param patch  差异包路径
	 * @return
	 */
	public static native int diffApk(String oldApk, String newApk, String patch);
}
