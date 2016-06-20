package com.sme.service.checkApk.imp;


import java.util.HashMap;
import java.util.Map;

import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.checkApk.ICheck;
import com.sme.util.ApkinformatonUtil;
import com.sme.util.JSONObject;
import com.sme.util.SpringContextUtil;


public class NewApplicationCheck implements ICheck {
	
	
	
	ICheck next = null;
	@Override
	public void setNext(ICheck check) {
		// TODO Auto-generated method stub
		this.next = check;
	}
	
	@Override
	public void check(JSONObject json, ApkinformatonUtil util, PAppDetail detail) {
		if(null ==detail.getpAppdetailApkactionid())
		{
			
			String version_check = "";
			String digest_check = "";
			String package_check= "";
			String name_check = "";
			PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
			Map<String, Object> parm = new HashMap<String, Object>();
			if(null == util.getAppname())
			{
				name_check = "插件名称不存在";
			}
			else
			{
				parm.put("pAppdetailName", util.getAppname());
				int count = pAppDetailDao.count(parm);
				
				if (count > 0) {
					name_check = "该插件名称与已存在的插件冲突";
				}
				
			}
			parm.clear();
			parm.put("pAppdetailPackagename", util.getPackagename());
			int count = pAppDetailDao.count(parm);
			
			if (count > 0) {
				package_check = "该插件包名与已存在的插件冲突";
			}
			
			json.getAppNode().setVersion_check(version_check);
			json.getAppNode().setPackage_check(package_check);
			json.getAppNode().setName_check(name_check);
			json.getAppNode().setDigest_check(digest_check);

		}
		else
		{
			if(next != null)
			{
				this.next.check(json, util, detail);
			}

		}
	}
	
}
