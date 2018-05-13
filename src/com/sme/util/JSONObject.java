package com.sme.util;

import com.sme.entity.AppNode;

/**
 * json对象
 * @author yao
 *
 */
public class JSONObject
{
	private int code;
	private String info;
	private AppNode appNode;
	
	public String getInfo()
	{
		return info;
	}
	public void setInfo(String info)
	{
		this.info = info;
	}
	public int getCode()
	{
		return code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public AppNode getAppNode()
	{
		return appNode;
	}
	public void setAppNode(AppNode appNode)
	{
		this.appNode = appNode;
	}
}
