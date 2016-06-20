package com.sme.service.checkApk.imp;


import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.checkApk.ICheck;
import com.sme.util.ApkinformatonUtil;
import com.sme.util.JSONObject;
import com.sme.util.SpringContextUtil;


public class NewDetailCheck implements ICheck {
	
	ICheck next = null;
	
	@Override
	public void setNext(ICheck check) {
		// TODO Auto-generated method stub
		this.next = check;
	}
	
	@Override
	public void check(JSONObject json, ApkinformatonUtil util, PAppDetail detail) {
		if(null ==detail.getpAppdetailId() && detail.getpAppdetailApkactionid() != null)
		{
			
			String version_check = "";
			String digest_check = "";
			String package_check= "";
			String name_check = "";
			
			PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
			PAppDetail oldOne = pAppDetailDao.selectOneByActionId(detail.getpAppdetailApkactionid());
			
			if(oldOne != null)
			{
				if(Integer.parseInt(util.getVersionCode()) <= oldOne.getpAppdetailVersion())
				{
					version_check = "必须高于已发布最新版本";
				}
				
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
