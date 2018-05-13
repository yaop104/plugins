package com.sme.service.checkApk.imp;

import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.checkApk.ICheck;
import com.sme.util.ApkinformatonUtil;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;
import com.sme.util.SpringContextUtil;


public class ModifyCheck implements ICheck {
	
	ICheck next = null;
	@Override
	public void setNext(ICheck check) {
		// TODO Auto-generated method stub
		this.next = check;
	}
	
	@Override
	public void check(JSONObject json, ApkinformatonUtil util, PAppDetail detail) {
		if(null !=detail.getpAppdetailId() && null != detail.getpAppdetailApkactionid())
		{
			
			String version_check = "";
			String digest_check = "";
			String package_check= "";
			String name_check = "";
			
			PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
			
			int postMin = pAppDetailDao.getPostMinVersion(detail.getpAppdetailApkactionid(), detail.getpAppdetailId());
			
			
			Integer version = Integer.parseInt(util.getVersionCode());
			if (postMin > 0 && version >= postMin) {
				version_check = "修改插件时版本号必须小于之后一个版本";
			}
			else 
			{
				int preMax = pAppDetailDao.getPreMaxVersion(detail.getpAppdetailApkactionid(), detail.getpAppdetailId());
				
				if (version <= preMax) {
					version_check = "修改插件时版本号必须高于之前一个版本";
				}
			}
			
			PAppDetail oldOne = pAppDetailDao.getById(detail);

			if(null != oldOne)
			{
				if(oldOne.getpAppdetailPackagename()!=null &&!oldOne.getpAppdetailPackagename().equals(util.getPackagename()))
				{
					package_check = "该版本包名与已发布版本不一致";
				}
				
				if(null == util.getAppname())
				{
					name_check = "插件名称不存在";
				}
				else if ( !util.getAppname().equals( oldOne.getpAppdetailName()  ) ) 
				{
					name_check = "该版本名称与已发布版本不一致";
				}
				
				if(oldOne.getpAppdetailCertdigest()!=null &&!oldOne.getpAppdetailCertdigest().equals(util.getDigest()))
				{
					digest_check = "该版本数字签名与已发布版本不一致";
				}
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
