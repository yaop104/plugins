package com.sme.entity;

import java.io.Serializable;

import com.sme.core.model.BaseObject;

/**
 * 终端应用关联表(PTerminalRelate)模型对象
 */
public class PTerminalRelate extends BaseObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8132143761976879641L;

	//======================字段列表========================
	/** id */
	private Integer	pTermrelateId;

	/** 终端id */
	private Integer	pTermrelateTermid;

	/** appid */
	private Integer	pTermrelateAppid;


	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tpTermrelateId(id):").append(pTermrelateId);
		buffer.append("\tpTermrelateTermid(终端id):").append(pTermrelateTermid);
		buffer.append("\tpTermrelateAppid(appid):").append(pTermrelateAppid);
		return buffer.toString();
	}
	//================== begin ======================


	public Integer getpTermrelateId()
	{
		return pTermrelateId;
	}


	public void setpTermrelateId(Integer pTermrelateId)
	{
		this.pTermrelateId = pTermrelateId;
	}


	public Integer getpTermrelateTermid()
	{
		return pTermrelateTermid;
	}


	public void setpTermrelateTermid(Integer pTermrelateTermid)
	{
		this.pTermrelateTermid = pTermrelateTermid;
	}


	public Integer getpTermrelateAppid()
	{
		return pTermrelateAppid;
	}


	public void setpTermrelateAppid(Integer pTermrelateAppid)
	{
		this.pTermrelateAppid = pTermrelateAppid;
	}

	//================== end ======================
}
