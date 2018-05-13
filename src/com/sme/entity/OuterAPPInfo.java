package com.sme.entity;

import com.sme.core.model.BaseObject;

public class OuterAPPInfo extends BaseObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8863338327845491036L;

	private String versionName;
	private String versionNo;
	private String releaseNote;
	private String size;
	private String url;
	private String ext;
	public String getVersionName()
	{
		return versionName;
	}
	public void setVersionName(String versionName)
	{
		this.versionName = versionName;
	}
	public String getVersionNo()
	{
		return versionNo;
	}
	public void setVersionNo(String versionNo)
	{
		this.versionNo = versionNo;
	}
	public String getReleaseNote()
	{
		return releaseNote;
	}
	public void setReleaseNote(String releaseNote)
	{
		this.releaseNote = releaseNote;
	}
	public String getSize()
	{
		return size;
	}
	public void setSize(String size)
	{
		this.size = size;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getExt()
	{
		return ext;
	}
	public void setExt(String ext)
	{
		this.ext = ext;
	}
	
}
