package com.sme.service.impl;

import java.util.List;

import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.AddService;
import com.sme.service.interfaces.IAPKType;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;
import com.sme.util.SpringContextUtil;


public class ModifyAPKStrategy extends AddService implements IAPKType{

	
	private List<String> chooseTerminal;
	
	@Override
	public void setTerminal(List<String> termial) {
		this.chooseTerminal = termial;
	}

	@Override
	protected JSONObject check(PAppDetail detail) {
		
		PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
		
		PAppDetail oldOne = pAppDetailDao.getById(detail);
		
		if(null == oldOne || !oldOne.getpAppdetailAdminid().equals(super.getAdminId()))
		{
			return  JSONUtil.checkResult(503, "请勿非法操作！");
		}
		
		if(!oldOne.getpAppdetailAuditstate().equals("2") && !oldOne.getpAppdetailAuditstate().equals("1"))
		{
			return  JSONUtil.checkResult(501, "只能修改未发布或者审核不通过的插件！");
		}
		
		//如果软件已经更新需要重新操作
		if(!oldOne.getpAppdetailMd5().equals(detail.getpAppdetailMd5()))
		{
			//复制文件
			if(!super.updateAppUrl(detail))
			{
				return  JSONUtil.checkResult(501, "保存文件失败！");
			}
			
		}
		return null;
	}

	@Override
	protected JSONObject insertDB(PAppDetail detail) {

		try
		{
			
			PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");

			pAppDetailDao.update(detail);
			
			
			return super.returnJson(true);
		}
		catch(Exception e)
		{
			return super.returnJson(false);
		}
	}


}
