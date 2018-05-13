package com.sme.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sme.entity.PAppDetail;
import com.sme.entity.PAppPatch;

public class DiffAPKUtil {
	
	public final static String LINUX_SHELL = "/bin/bash";
	
	public final static String LINUX_ARG = "-c";
	
	public final static String BSDIFF_USAGE = "usage: bsdiff oldfile newfile patchfile";
	
	public final static String BSDIFF_FILE_NOTFOUND = "The system cannot find the file specified.";
	
	public final static String BSDIFF_COMMAND_NOTFOUND = "command \'bsdiff\' found";
	
	public final static String BSPATCH_USAGE = "usage: bspatch oldfile newfile patchfile";
	
	public final static String BSPATCH_FILE_NOTFOUND = "The system cannot find the file specified.";
	
	public final static String BSPATCH_COMMAND_NOTFOUND = "command \'bspatch\' found";
	
	public static final Log log = LogFactory.getLog(DiffAPKUtil.class);
	
	/**
	 * TODO:lishuo
	 **/
	public static PAppPatch diffutil(PAppDetail newUploadVesrion, PAppDetail oldVersion) {
		
		// 做完成DIFF后将信息写到PATCH对象中
		String new_version_path = ReadProperties.getString("config", "file.path") +"/"+ newUploadVesrion.getpAppdetailPackagename() + "/" + newUploadVesrion.getpAppdetailVersionname() + "/"
				+ newUploadVesrion.getpAppdetailApk();
		String[] args = new String[3];
		args[0] = LINUX_SHELL;//
		args[1] = LINUX_ARG;
		args[2] = null;
		
		String diff_cmd = "bsdiff ";
		String old_version_path = ReadProperties.getString("config", "file.path") +"/"+ oldVersion.getpAppdetailPackagename() + "/" + oldVersion.getpAppdetailVersionname() + "/"
				+ oldVersion.getpAppdetailApk();
		String patch_path = ReadProperties.getString("config", "file.path") +"/"+ newUploadVesrion.getpAppdetailPackagename() + "/" + newUploadVesrion.getpAppdetailVersionname() + "/"
				+ newUploadVesrion.getpAppdetailVersionname() + "-" + oldVersion.getpAppdetailPackagename() + "";// /去掉
		diff_cmd += old_version_path;
		diff_cmd += " ";
		diff_cmd += new_version_path;
		diff_cmd += " ";
		diff_cmd += patch_path;
		
		args[2] = diff_cmd;
		
		PAppPatch patch = new PAppPatch();
		patch.setpAppPatchstate("0");
		patch.setpPatchPatchsize(0);
		patch.setpAppPatchfilepath(null);
		
		if(log.isDebugEnabled())
		{
			
			log.debug("badiff command |"+args[0]+"|"+args[1]+"|"+args[2]);
			
		}
		if(bsdiff(args).equals("Utility - bsdiff ::: ok")) {
			
			String tmp_apk = ReadProperties.getString("config", "file.path") +"/"+ newUploadVesrion.getpAppdetailPackagename() + "/" + newUploadVesrion.getpAppdetailVersionname() + "/" + "tmp.apk";
			
			String patch_cmd = "bspatch ";
			patch_cmd += old_version_path;
			patch_cmd += " ";
			patch_cmd += tmp_apk;
			patch_cmd += " ";
			patch_cmd += patch_path;
			
			args[2] = patch_cmd;
			
			if(log.isDebugEnabled())
			{
				
				log.debug("badiff command |"+args[0]+"|"+args[1]+"|"+args[2]);
				
			}
			
			if(bspatch(args).equals("Utility - bspatch ::: ok")) {
				
				if(MD5Util.getFileMD5String(tmp_apk).equals(newUploadVesrion.getpAppdetailMd5())) {
					
					File p = new File(patch_path);
					patch.setpPatchPatchsize(new Long(p.length()).intValue() / 1024);
					patch.setpAppPatchfilepath(patch_path);
					patch.setpAppPatchstate("1");
				}
			}
		}
		
		return patch;
	}
	
	private static String bsdiff(String[] args) {
		
		try {
			
			Runtime runTime = Runtime.getRuntime();
			if(runTime == null) {
				System.err.println("Utility - bsdiff ::: create runtime false!");
			}
			Process ps = runTime.exec(args);
			
			String input = loadStream(ps.getInputStream());
			String error = loadStream(ps.getErrorStream());
			
			System.err.println(error);
			
			ps.waitFor();
			if(ps.exitValue() != 0) {
				System.err.println("Utility - bsdiff ::: process exit error");
			}
			ps.destroy();
			
			if(input.startsWith(BSDIFF_USAGE)) {
				return "Utility - bsdiff ::: usage error";
			}
			else
				if(input.startsWith(BSDIFF_FILE_NOTFOUND)) {
					return "Utility - bsdiff ::: file not found";
				}
				else
					if(input.startsWith(BSDIFF_COMMAND_NOTFOUND)) {
						return "Utility - bsdiff ::: command not found";
					}
					else
						if((input.equals("")) && (!error.equals(""))) {
							return "Utility - bsdiff ::: " + error;
						}
						else {
							return "Utility - bsdiff ::: ok";
						}
			
		}
		catch(IOException e) {
			log.error("Utility - bsdiff ::: process  io exception",e);
		}
		catch(InterruptedException e) {
			log.error("Utility - bsdiff ::: process interrupted exception",e);
		}
		
		return "Utility - bsdiff ::: exception";
	}
	
	private static String loadStream(InputStream in) throws IOException {
		
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();
		int ptr = 0;
		
		while((ptr = in.read()) != -1) {
			buffer.append((char)ptr);
		}
		
		in.close();
		return buffer.toString();
	}
	
	private static String bspatch(String[] args) {
		
		try {
			
			Runtime runTime = Runtime.getRuntime();
			if(runTime == null) {
				System.err.println("Utility - bspatch ::: create runtime false!");
			}
			Process ps = runTime.exec(args);
			
			String input = loadStream(ps.getInputStream());
			String error = loadStream(ps.getErrorStream());
			
			System.err.println(error);
			
			ps.waitFor();
			if(ps.exitValue() != 0) {
				System.err.println("Utility - bspatch ::: process exit error");
			}
			ps.destroy();
			
			if(input.startsWith(BSPATCH_USAGE)) {
				return "Utility - bspatch ::: usage error";
			}
			else
				if(input.startsWith(BSPATCH_FILE_NOTFOUND)) {
					return "Utility - bspatch ::: file not found";
				}
				else
					if(input.startsWith(BSPATCH_COMMAND_NOTFOUND)) {
						return "Utility - bspatch ::: command not found";
					}
					else
						if((input.equals("")) && (!error.equals(""))) {
							return "Utility - bspatch ::: " + error;
						}
						else {
							return "Utility - bspatch ::: ok";
						}
			
		}
		catch(IOException e) {
			log.error("Utility - bspatch ::: process  io exception",e);
		}
		catch(InterruptedException e) {
			log.error("Utility - bspatch ::: process interrupted exception",e);
		}
		
		return "Utility - bspatch ::: exception";
	}
}
