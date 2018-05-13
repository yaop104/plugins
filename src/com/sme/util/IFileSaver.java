package com.sme.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件接口
 * @author yao
 *
 */
public interface IFileSaver {
	
	/**
	 * 
	 * @方法名：save
	 * @描述：(保存文件，返回)
	 * @param file
	 * @param name
	 * @return
	 * @throws Exception
	 * @输出：String
	 * @作者：hejh
	 *
	 */
	public void save(MultipartFile file,String name);
	
	/**
	 * 
	 * @方法名：getResult
	 * @描述：(获取保存文件的结果)
	 * @return
	 * @输出：JSONObject
	 * @作者：hejh
	 *
	 */
	public JSONObject getResult();
}
