package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.io.Serializable;

import java.util.Date;

/**
 * 用户app下载历史(TapDownload)模型对象
 */
public class TapDownload extends BaseObject implements Serializable {
	//======================字段列表========================
	/** unid */
	private Integer	tapUnid;

	/** 状态 */
	private String	tapState;

	/** 最近安装时间 */
	private Date	tapUdate;

	/** 安装者 */
	private Integer	tapUuser;

	/** appid */
	private Integer	tapAppid;

	/** 历史app名字 */
	private String	tapTitle;

	/** 历史app图片 */
	private String	tapImg;

	/** 历史app版本 */
	private String	tapNumber;

	/** 开发商信息 */
	private String	tapOptname;

	public Integer getTapUnid() {
		return this.tapUnid;
	}

	public void setTapUnid(Integer tapUnid) {
		this.tapUnid = tapUnid;
	}

	public String getTapState() {
		return this.tapState;
	}

	public void setTapState(String tapState) {
		this.tapState = tapState;
	}

	public Date getTapUdate() {
		return this.tapUdate;
	}

	public void setTapUdate(Date tapUdate) {
		this.tapUdate = tapUdate;
	}

	public Integer getTapUuser() {
		return this.tapUuser;
	}

	public void setTapUuser(Integer tapUuser) {
		this.tapUuser = tapUuser;
	}

	public Integer getTapAppid() {
		return this.tapAppid;
	}

	public void setTapAppid(Integer tapAppid) {
		this.tapAppid = tapAppid;
	}

	public String getTapTitle() {
		return this.tapTitle;
	}

	public void setTapTitle(String tapTitle) {
		this.tapTitle = tapTitle;
	}

	public String getTapImg() {
		return this.tapImg;
	}

	public void setTapImg(String tapImg) {
		this.tapImg = tapImg;
	}

	public String getTapNumber() {
		return this.tapNumber;
	}

	public void setTapNumber(String tapNumber) {
		this.tapNumber = tapNumber;
	}

	public String getTapOptname() {
		return this.tapOptname;
	}

	public void setTapOptname(String tapOptname) {
		this.tapOptname = tapOptname;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttapUnid(unid):").append(tapUnid);
		buffer.append("\ttapState(状态):").append(tapState);
		buffer.append("\ttapUdate(最近安装时间):").append(tapUdate);
		buffer.append("\ttapUuser(安装者):").append(tapUuser);
		buffer.append("\n");
		buffer.append("\ttapAppid(appid):").append(tapAppid);
		buffer.append("\ttapTitle(历史app名字):").append(tapTitle);
		buffer.append("\ttapImg(历史app图片):").append(tapImg);
		buffer.append("\ttapNumber(历史app版本):").append(tapNumber);
		buffer.append("\n");
		buffer.append("\ttapOptname(开发商信息):").append(tapOptname);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
