package com.sme.service;

import com.sme.entity.PAppDetail;
import com.sme.service.impl.AddAPKStrategy;
import com.sme.service.impl.AddHTMLStrategy;
import com.sme.service.impl.ModifyAPKStrategy;
import com.sme.service.impl.ModifyHTMLStrategy;
import com.sme.service.interfaces.IPluginSaveStrategy;


public class StrategyFactory {
	
	public static IPluginSaveStrategy getStrategyByDetail(PAppDetail detail)
	{
		//新增插件
		if(detail.getpAppdetailId()==null)
		{
			if("1".equals(detail.getpAppdetailPlugintype()))
			{
				return new AddHTMLStrategy();
			}
			else
			{
				return new AddAPKStrategy();
			}
		}
		//修改插件
		else
		{
			if("1".equals(detail.getpAppdetailPlugintype()))
			{
				return new ModifyHTMLStrategy();
			}
			else
			{
				return new ModifyAPKStrategy();
			}
		}
	}
}
