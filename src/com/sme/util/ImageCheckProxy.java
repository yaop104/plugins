package com.sme.util;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片检查代理
 * @author yao
 *
 */
public class ImageCheckProxy implements IFileSaver {
	
	
	private boolean isOK = false;
	
	IFileSaver local;
	
	public void save(MultipartFile file, String name) {
		if(file == null || name == null || name.equals("")) {
			return;
		}
		
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file.getInputStream());
		}
		catch(Exception e) {
			return;
		}
		
		if(bi == null) {
			// 不是图片的情况
			return;
		}
		
		int dotIndex = name.lastIndexOf(".");
		if(dotIndex == -1) {
			return;
		}
		String postFfix = name.substring(dotIndex);
		String newFileName = "_" + System.currentTimeMillis() + postFfix;
		isOK = true;
		local.save(file, newFileName);
		
		return;
	}
	
	public ImageCheckProxy(IFileSaver body) {
		this.local = body;
	}
	
	@Override
	public JSONObject getResult() {
		if(isOK) {
			return local.getResult();
		}
		else {
			
			return JSONUtil.checkResult(501, "上传图片有误！");
			
		}
	}
}
