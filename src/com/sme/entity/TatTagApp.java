package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.io.Serializable;

/**
 * 应用标签关联表(TatTagApp)模型对象
 */
public class TatTagApp extends BaseObject implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 4542286572599530833L;

	//======================字段列表========================
	/** id */
	private Integer	tatUnid;

	/** 标签id */
	private Integer	tagUnid;

	/** appid */
	private Integer	appUnid;

	public Integer getTatUnid() {
		return this.tatUnid;
	}

	public void setTatUnid(Integer tatUnid) {
		this.tatUnid = tatUnid;
	}

	public Integer getTagUnid() {
		return this.tagUnid;
	}

	public void setTagUnid(Integer tagUnid) {
		this.tagUnid = tagUnid;
	}

	public Integer getAppUnid() {
		return this.appUnid;
	}

	public void setAppUnid(Integer appUnid) {
		this.appUnid = appUnid;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttatUnid(id):").append(tatUnid);
		buffer.append("\ttagUnid(标签id):").append(tagUnid);
		buffer.append("\tappUnid(appid):").append(appUnid);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
