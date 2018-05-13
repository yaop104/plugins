package com.sme.entity;


import com.sme.core.model.BaseObject;

/**
 * 版本管理(PAppPatch)模型对象
 */
public class PAppPatch extends BaseObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -137253694425185282L;

	//======================字段列表========================
	/** id */
	private Integer	pPatchId;

	/** 当前版本 */
	private Integer	pPatchCurrentversion;

	/** 版本号 */
	private Integer	pPatchPatchversion;

	/** apk版本号 */
	private Integer	pPatchPatchapkversion;

	/** 当前版本id */
	private Integer	pPatchCurrentversionid;

	/** 版本id */
	private Integer	pPatchPatchversionid;

	/** 版本大小 */
	private Integer	pPatchPatchsize;

	/** 当前版本大小 */
	private Integer	pPatchCurrentsize;

	/** 状态 */
	private String	pAppPatchstate;

	/** 版本路径 */
	private String	pAppPatchfilepath;

	/** 老版本大小 */
	private Integer	pAppOldversionsize;

	public Integer getpPatchId()
	{
		return pPatchId;
	}


	public void setpPatchId(Integer pPatchId)
	{
		this.pPatchId = pPatchId;
	}


	public Integer getpPatchCurrentversion()
	{
		return pPatchCurrentversion;
	}


	public void setpPatchCurrentversion(Integer pPatchCurrentversion)
	{
		this.pPatchCurrentversion = pPatchCurrentversion;
	}


	public Integer getpPatchPatchversion()
	{
		return pPatchPatchversion;
	}


	public void setpPatchPatchversion(Integer pPatchPatchversion)
	{
		this.pPatchPatchversion = pPatchPatchversion;
	}


	public Integer getpPatchPatchapkversion()
	{
		return pPatchPatchapkversion;
	}


	public void setpPatchPatchapkversion(Integer pPatchPatchapkversion)
	{
		this.pPatchPatchapkversion = pPatchPatchapkversion;
	}


	public Integer getpPatchCurrentversionid()
	{
		return pPatchCurrentversionid;
	}


	public void setpPatchCurrentversionid(Integer pPatchCurrentversionid)
	{
		this.pPatchCurrentversionid = pPatchCurrentversionid;
	}


	public Integer getpPatchPatchversionid()
	{
		return pPatchPatchversionid;
	}


	public void setpPatchPatchversionid(Integer pPatchPatchversionid)
	{
		this.pPatchPatchversionid = pPatchPatchversionid;
	}


	public Integer getpPatchPatchsize()
	{
		return pPatchPatchsize;
	}


	public void setpPatchPatchsize(Integer pPatchPatchsize)
	{
		this.pPatchPatchsize = pPatchPatchsize;
	}


	public Integer getpPatchCurrentsize()
	{
		return pPatchCurrentsize;
	}


	public void setpPatchCurrentsize(Integer pPatchCurrentsize)
	{
		this.pPatchCurrentsize = pPatchCurrentsize;
	}


	public String getpAppPatchstate()
	{
		return pAppPatchstate;
	}


	public void setpAppPatchstate(String pAppPatchstate)
	{
		this.pAppPatchstate = pAppPatchstate;
	}


	public String getpAppPatchfilepath()
	{
		return pAppPatchfilepath;
	}


	public void setpAppPatchfilepath(String pAppPatchfilepath)
	{
		this.pAppPatchfilepath = pAppPatchfilepath;
	}


	public Integer getpAppOldversionsize()
	{
		return pAppOldversionsize;
	}


	public void setpAppOldversionsize(Integer pAppOldversionsize)
	{
		this.pAppOldversionsize = pAppOldversionsize;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tpPatchId(id):").append(pPatchId);
		buffer.append("\tpPatchCurrentversion(当前版本):").append(pPatchCurrentversion);
		buffer.append("\tpPatchPatchversion(版本号):").append(pPatchPatchversion);
		buffer.append("\tpPatchPatchapkversion(apk版本号):").append(pPatchPatchapkversion);
		buffer.append("\n");
		buffer.append("\tpPatchCurrentversionid(当前版本id):").append(pPatchCurrentversionid);
		buffer.append("\tpPatchPatchversionid(版本id):").append(pPatchPatchversionid);
		buffer.append("\tpPatchPatchsize(版本大小):").append(pPatchPatchsize);
		buffer.append("\tpPatchCurrentsize(当前版本大小):").append(pPatchCurrentsize);
		buffer.append("\n");
		buffer.append("\tpAppPatchstate(状态):").append(pAppPatchstate);
		buffer.append("\tpAppPatchfilepath(版本路径):").append(pAppPatchfilepath);
		buffer.append("\tpAppOldversionsize(老版本大小):").append(pAppOldversionsize);
		return buffer.toString();
	}
	//================== begin ======================
	

	//================== end ======================
}
