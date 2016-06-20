package com.sme.util;

import java.io.File;

import com.sme.entity.PAppDetail;
import com.sme.entity.PAppPatch;

public class DifApkUtil {

	public static PAppPatch diff(PAppDetail newUploadApk, PAppDetail oldApk) {
		PAppPatch patch = new PAppPatch();
		patch.setpAppPatchstate("0");
		patch.setpPatchPatchsize(0);
		patch.setpAppPatchfilepath(null);
		
		String basePath = ReadProperties.getString("config", "file.path");
		
		//diff
		String oldApkPath = basePath + "/" + oldApk.getpAppdetailPackagename() + "/" + oldApk.getpAppdetailVersionname() + "/" + oldApk.getpAppdetailApk(),
				newApkPath = basePath + "/" + newUploadApk.getpAppdetailPackagename() + "/" + newUploadApk.getpAppdetailVersionname() + "/" + newUploadApk.getpAppdetailApk(),
				patchPath = basePath + "/" + newUploadApk.getpAppdetailPackagename() + "/" + newUploadApk.getpAppdetailVersionname() + "/" + newUploadApk.getpAppdetailVersionname() + 
							"-" + oldApk.getpAppdetailPackagename() + ".patch";
		int n = SauDiffUtil.diffApk(oldApkPath, newApkPath, patchPath);
		if (n == 0) {
			String tempApk = basePath + "/" + newUploadApk.getpAppdetailPackagename() + "/" + newUploadApk.getpAppdetailVersionname() + "/tmp.apk";
			if (SauPatchUtil.patchApk(oldApkPath, tempApk, patchPath) == 0) {
				if (MD5Util.getFileMD5String(tempApk).equals(newUploadApk.getpAppdetailMd5())) {
					File p = new File(patchPath);
					patch.setpPatchPatchsize(new Long(p.length()).intValue() / 1024);
					patch.setpAppPatchfilepath(patchPath);
					patch.setpAppPatchstate("1");
				}
			}
		}
 		
		return patch;
	}
}
