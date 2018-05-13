package com.sme.util;


import org.springframework.web.multipart.MultipartFile;



public class ApkPrefixProxy implements IFileSaver {
	
	private JSONObject json = null;
	
	private IFileSaver local;
	
	@Override
	public void save(MultipartFile file, String name) {
		JSONObject obj = new JSONObject();
		
		obj.setCode(501);
		if(file == null || file.getSize() == 0) {
			this.json = obj;
			return;
		}
		if(name == null || !name.endsWith(".apk")) {
			obj.setInfo("插件必须是apk类型文件!");
			this.json = obj;
			return;
		}
		if(file.getSize() > 104857600) {
			obj.setInfo("上传组件文件不可大于100M!");
			this.json = obj;
			return;
		}
		
		String fileName = "_" + System.currentTimeMillis() + ".apk";
		local.save(file, fileName);
	}
	
	@Override
	public JSONObject getResult() {
		
		if(json != null) {
			return json;
		}
		else {
			return local.getResult();
		}
	}
	
	public ApkPrefixProxy(IFileSaver out) {
		this.local = out;
	}
}
