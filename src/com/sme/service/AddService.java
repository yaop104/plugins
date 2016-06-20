package com.sme.service;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.logicalcobwebs.proxool.admin.Admin;

import com.sme.entity.PAppDetail;
import com.sme.service.interfaces.IAPKType;
import com.sme.service.interfaces.IPluginSaveStrategy;
import com.sme.util.Config;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;


public abstract class AddService implements IPluginSaveStrategy{
	

	@Override
	public JSONObject save(PAppDetail detail) {
		JSONObject jsonIfExit = check(detail);
		
		if(null != jsonIfExit)
		{
			return jsonIfExit;
		}
		
		detail.setpAppdetailAdminid(getAdminId());
		detail.setpAppdetailAuditstate("1");
		detail.setpAppdetailDate(new Date());
		
		if(this instanceof IAPKType)
		{
			detail.setpAppdetailPlatform("Android");
		}
		else
		{
			detail.setpAppdetailPlatform(null);
		}
		return insertDB(detail);
	}
	
	//后期session获取
	protected Integer getAdminId() {
//		Admin admin = (Admin)ServletActionContext.getRequest().getSession().getAttribute("admin") ;
		
//		return admin.getAdminid();
		return 1;
	}
	
	/**
	 * 根据保存时提交的数据
	 * @param cor
	 * @return
	 */
	protected boolean updateAppUrl(PAppDetail detail) 
	{
//		//要新建的文件夹
//		String file=Config.FILE_PATH+"/"+detail.getApp_package_name()+"/"+detail.getApp_version();
//		File newfile = new File(file);
//		if (!newfile.exists()) {
//			if(!newfile.mkdirs())
//			{
//				return false;
//			}
//		}
//
		try {
//			String filename = detail.getApp_apk();
//						
//			File tempfile = new File(Config.FILE_PATH+"/"+filename);
//			
//			//将临时文件复制到正式路径下
//			FileUtils.copyFile(tempfile, new File(file +"/"+ filename));
//			
//			//删除临时文件
//			tempfile.delete();
//			
//			//在数据库中设置预览路径
//			detail.setApp_url(Config.URL_PREFIX+"/"+detail.getApp_package_name()+"/"+detail.getApp_version()+"/"+filename);
//			
			return true;
		} catch (Exception e) {
			//log4j
			return false;
		}
	}
	
	/**
	 * 
	 * @方法名：returnJson
	 * @描述：(描述这个方法的作用)
	 * @param flag
	 * @return
	 * @输出：JSONObject
	 * @作者：hejh
	 *
	 */
	protected JSONObject returnJson(boolean flag)
	{
		JSONObject result = new JSONObject();
		if(flag)
		{	
			result = JSONUtil.checkResult(500, "保存成功！");
		}
		else
		{
			result = JSONUtil.checkResult(501,  "保存失败！");
		}
		return result;
	}
	/**
	 * 
	 * @方法名：check
	 * @描述：(校验保存的可能性)
	 * @param detail
	 * @return
	 * @输出：如果校验不通过返回对象，如果校验通过就返回null
	 * @作者：hejh
	 *
	 */
	protected abstract JSONObject check(PAppDetail detail);
	
	/**
	 * 
	 * @方法名：insertDB
	 * @描述：(把数据放入数据库)
	 * @param detail
	 * @return
	 * @输出：JSONObject
	 * @作者：hejh
	 *
	 */
	protected abstract JSONObject insertDB(PAppDetail detail);
	
}
