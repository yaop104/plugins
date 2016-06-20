package com.ncscyber.bigdt.test;

import com.sme.util.SauDiffUtil;
import com.sme.util.SauPatchUtil;

public class SauTest {
	static {
		System.loadLibrary("plugins");
	}

	public static void main(String[] args) {
//		testDiff();
		testPatch();
	}

	public static void testDiff() {
		System.out.println("开始差分..");
		String newApk = "/Users/haoy/Documents/sautest/weiboV4.5.5.apk",
				oldApk = "/Users/haoy/Documents/sautest/weiboV4.5.0.apk",
				patch = "/Users/haoy/Documents/sautest/weibo.patch";
		int i= SauDiffUtil.diffApk(oldApk, newApk, patch);
		System.out.println("结束" + i);
	}
	
	public static void testPatch() {
		System.out.println("开始差分..");
		String oldApk = "/Users/haoy/Documents/sautest/weiboV4.5.5.apk",
				patchApk = "/Users/haoy/Documents/sautest/tmp.apk",
				patch = "/Users/haoy/Documents/sautest/weibo.patch";
		int i= SauPatchUtil.patchApk(oldApk, patchApk, patch);
		System.out.println("结束" + i);
	}
}
