package com.sme.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;
/**
 * 文件处理类
 * @author yao
 *
 */
public class FileSaver implements IFileSaver {
	
	private Exception expBean = null;
	
	private String fileName = null;

	private String savePath;

	public FileSaver(String savePath) {
		this.savePath = savePath;
	}

	@Override
	public void save(MultipartFile file, String newFileName) {
		
		this.fileName = newFileName;
		
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
//			String folder = "D:\\resourcesfile\\download\\apk";
//			String folder = Config.DEFAULT_APK_PATH;
			String folder = this.savePath;
			File folderFile = new File(folder);
			if(!folderFile.exists()) {
				folderFile.mkdirs();
			}
			
			String realPath = folder + File.separator + newFileName;
//			String realPath = Config.DEFAULT_APK_PATH + File.separator  + newFileName;
			File localFile = new File(realPath);
			if(!localFile.exists()) {
				localFile.createNewFile();
			}
			fis = file.getInputStream();
			
			fos = new FileOutputStream(localFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			expBean = e;
		}
		finally {
			try {
				if(fis != null) {
					fis.close();
				}
			}
			catch(Exception e3) {
				// log4j
			}
			
			try {
				if(fos != null) {
					fos.close();
				}
			}
			catch(Exception e3) {
				// log4j
			}
		}
		
	}
	
	@Override
	public JSONObject getResult() {
		
		if(expBean == null) {
			return JSONUtil.checkResult(500, fileName);
		}
		else {
			return JSONUtil.checkResult(501, "保存文件失败！");
		}
		
	}
	
}
