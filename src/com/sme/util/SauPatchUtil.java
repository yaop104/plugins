package com.sme.util;

public class SauPatchUtil {

	/**
	 * 本地方法 由旧apk和差异包生成新的apk
	 * @param oldApk 旧apk路径
	 * @param patchApk 生成的新apk路径
	 * @param patch 差分包路径
	 * @return
	 */
	public static native int patchApk(String oldApk, String patchApk, String patch);
}
