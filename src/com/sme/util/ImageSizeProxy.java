package com.sme.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;
/**
 * 图片大小代理
 * @author yao
 *
 */
public class ImageSizeProxy implements IFileSaver {
	
	
	private boolean isOK = false;
	
	public static final int ICON_WIDTH_BIG = 480;
	
	public static final int ICON_HEIGHT_BIG = 800;
	
	private IFileSaver local;
	
	@Override
	public void save(MultipartFile file, String name) {
		
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file.getInputStream());
		}
		catch(IOException e) {
			return;
		}
		
		if(bi.getWidth() != ICON_WIDTH_BIG || bi.getHeight() != ICON_HEIGHT_BIG) {
			return;
		}
		
		isOK = true;
		local.save(file, name);
		return;
	}
	
	public ImageSizeProxy(IFileSaver body) {
		this.local = body;
	}
	
	@Override
	public JSONObject getResult() {
		
		if(isOK) {
			return local.getResult();
		}
		else {

			return JSONUtil.checkResult(501,"上传图片像素必须为480*800！");

		}
	}
}
