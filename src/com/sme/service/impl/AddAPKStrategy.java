package com.sme.service.impl;

import java.util.List;




import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.AddService;
import com.sme.service.interfaces.IAPKType;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;
import com.sme.util.SpringContextUtil;


public class AddAPKStrategy extends AddService implements IAPKType{

	private List<String> chooseTerminal;
	
	@Override
	public void setTerminal(List<String> termial) {
		this.chooseTerminal = termial;
	}

	
	@Override
	protected JSONObject check(PAppDetail detail) {

		PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
		//新增一个插件的版本
		if(null != detail.getpAppdetailApkactionid())
		{	
			PAppDetail oldOne = pAppDetailDao.selectOneByActionId(detail.getpAppdetailApkactionid());
			
			if(null != oldOne){
				if(!oldOne.getpAppdetailAdminid().equals(super.getAdminId()))
				{
					return JSONUtil.checkResult(503, "请勿非法操作！");
				}
				
				if(oldOne.getpAppdetailAuditstate().equals("5"))
				{
					return JSONUtil.checkResult(501,"插件已经被下线，无法上传新版本！");
				}	
			}
			
		}
		
		if(!super.updateAppUrl(detail))
		{
			return JSONUtil.checkResult(501, "保存文件失败！");
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
