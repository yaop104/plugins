package com.sme.service.impl;

import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.AddService;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;
import com.sme.util.SpringContextUtil;


public class AddHTMLStrategy extends AddService{

//	IDetailDao dDao = DaoFactory.getDetail();

	@Override
	protected JSONObject check(PAppDetail detail) {
		
		//新增一个插件的版本
		if(null != detail.getpAppdetailApkactionid())
		{	
//			AppDetail oldOne = dDao.getMaxVersionDetail(detail.getApp_apkactionid());
			PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
			
			PAppDetail oldOne = pAppDetailDao.selectOneByActionId(detail.getpAppdetailApkactionid());
			
			if(null != oldOne){
				if(detail.getpAppdetailVersion() <= oldOne.getpAppdetailVersion())
				{
					return	JSONUtil.checkResult(501,  "必须高于已发布最新版本！");
					
				}
				
				if(!oldOne.getpAppdetailAdminid().equals(super.getAdminId()))
				{
					return  JSONUtil.checkResult(503, "请勿非法操作！");
					
				}
				
				if(oldOne.getpAppdetailAuditstate().equals("5"))
				{
					return  JSONUtil.checkResult(501, "插件已经被下线，无法上传新版本！");
				}
			}
			
		}
		return null;
	}

	@Override
	protected JSONObject insertDB(PAppDetail detail) {
		boolean addNew = false;
		if(detail.getpAppdetailApkactionid() == null)
		{
			addNew = true;
		}
		try
		{
			PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
			pAppDetailDao.insert(detail);
			return super.returnJson(true);
		}
		catch(Exception e)
		{
			return super.returnJson(false);
		}
	}

	
}
