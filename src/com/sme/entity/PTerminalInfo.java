package com.sme.entity;

import java.io.Serializable;
import java.util.Date;

import com.sme.core.model.BaseObject;

/**
 * 终端信息(PTerminalInfo)模型对象
 */
public class PTerminalInfo extends BaseObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6689076825632177401L;

	//======================字段列表========================
	/** id */
	private Integer	pTerminfoId;

	/** 终端名称 */
	private String	pTerminfoName;

	/** 终端特征 */
	private String	pTerminfoFeature;

	/** demo */
	private String	pTerminfoDemo;

	/** factor */
	private String	pTerminfoFactor;

	/** 是否是厂商字段 */
	private String	pTerminfoIsfactory;

	/** 版本 */
	private Integer	pTerminfoVersion;

	/** 创建者 */
	private Integer	pTerminfoCuser;

	/** 更新者 */
	private Integer	pTerminfoUuser;

	/** 创建时间 */
	private Date	pTerminfoCdate;

	/** 更新时间 */
	private Date	pTerminfoUdate;

	/** 删除标记 */
	private String	pTerminfoDelflag;


	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tpTerminfoId(id):").append(pTerminfoId);
		buffer.append("\tpTerminfoName(终端名称):").append(pTerminfoName);
		buffer.append("\tpTerminfoFeature(终端特征):").append(pTerminfoFeature);
		buffer.append("\tpTerminfoDemo(demo):").append(pTerminfoDemo);
		buffer.append("\n");
		buffer.append("\tpTerminfoFactor(factor):").append(pTerminfoFactor);
		buffer.append("\tpTerminfoIsfactory(是否是厂商字段):").append(pTerminfoIsfactory);
		buffer.append("\tpTerminfoVersion(版本):").append(pTerminfoVersion);
		buffer.append("\tpTerminfoCuser(创建者):").append(pTerminfoCuser);
		buffer.append("\n");
		buffer.append("\tpTerminfoUuser(更新者):").append(pTerminfoUuser);
		buffer.append("\tpTerminfoCdate(创建时间):").append(pTerminfoCdate);
		buffer.append("\tpTerminfoUdate(更新时间):").append(pTerminfoUdate);
		buffer.append("\tpTerminfoDelflag(删除标记):").append(pTerminfoDelflag);
		return buffer.toString();
	}
	//================== begin ======================


	public Integer getpTerminfoId()
	{
		return pTerminfoId;
	}


	public void setpTerminfoId(Integer pTerminfoId)
	{
		this.pTerminfoId = pTerminfoId;
	}


	public String getpTerminfoName()
	{
		return pTerminfoName;
	}


	public void setpTerminfoName(String pTerminfoName)
	{
		this.pTerminfoName = pTerminfoName;
	}


	public String getpTerminfoFeature()
	{
		return pTerminfoFeature;
	}


	public void setpTerminfoFeature(String pTerminfoFeature)
	{
		this.pTerminfoFeature = pTerminfoFeature;
	}


	public String getpTerminfoDemo()
	{
		return pTerminfoDemo;
	}


	public void setpTerminfoDemo(String pTerminfoDemo)
	{
		this.pTerminfoDemo = pTerminfoDemo;
	}


	public String getpTerminfoFactor()
	{
		return pTerminfoFactor;
	}


	public void setpTerminfoFactor(String pTerminfoFactor)
	{
		this.pTerminfoFactor = pTerminfoFactor;
	}


	public String getpTerminfoIsfactory()
	{
		return pTerminfoIsfactory;
	}


	public void setpTerminfoIsfactory(String pTerminfoIsfactory)
	{
		this.pTerminfoIsfactory = pTerminfoIsfactory;
	}


	public Integer getpTerminfoVersion()
	{
		return pTerminfoVersion;
	}


	public void setpTerminfoVersion(Integer pTerminfoVersion)
	{
		this.pTerminfoVersion = pTerminfoVersion;
	}


	public Integer getpTerminfoCuser()
	{
		return pTerminfoCuser;
	}


	public void setpTerminfoCuser(Integer pTerminfoCuser)
	{
		this.pTerminfoCuser = pTerminfoCuser;
	}


	public Integer getpTerminfoUuser()
	{
		return pTerminfoUuser;
	}


	public void setpTerminfoUuser(Integer pTerminfoUuser)
	{
		this.pTerminfoUuser = pTerminfoUuser;
	}


	public Date getpTerminfoCdate()
	{
		return pTerminfoCdate;
	}


	public void setpTerminfoCdate(Date pTerminfoCdate)
	{
		this.pTerminfoCdate = pTerminfoCdate;
	}


	public Date getpTerminfoUdate()
	{
		return pTerminfoUdate;
	}


	public void setpTerminfoUdate(Date pTerminfoUdate)
	{
		this.pTerminfoUdate = pTerminfoUdate;
	}


	public String getpTerminfoDelflag()
	{
		return pTerminfoDelflag;
	}


	public void setpTerminfoDelflag(String pTerminfoDelflag)
	{
		this.pTerminfoDelflag = pTerminfoDelflag;
	}

	//================== end ======================
}
