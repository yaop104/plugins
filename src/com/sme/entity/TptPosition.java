package com.sme.entity;

import java.io.Serializable;

import java.util.Date;

/**
 * 投放位(TptPosition)模型对象
 */
public class TptPosition implements Serializable {
	//======================字段列表========================
	/** unid */
	private Integer	tptUnid;

	/** 状态 */
	private String	tptState;

	/** 序号 */
	private Integer	tptOrder;

	/** 创建日期 */
	private Date	tptCdate;

	/** 最后修改日期 */
	private Date	tptUdate;

	/** 创建者 */
	private String	tptCuser;

	/** 最后修改者 */
	private String	tptUuser;

	/** 渠道id */
	private String	tptModeid;

	/** 投放位名 */
	private String	tptName;

	/** 限制生效时间 */
	private String	tptNumstime;

	/** 原价 */
	private Integer	tptPrice;

	/** 示例图地址 */
	private String	tptDemourl;

	/** 描述 */
	private String	tptDesc;

	public Integer getTptUnid() {
		return this.tptUnid;
	}

	public void setTptUnid(Integer tptUnid) {
		this.tptUnid = tptUnid;
	}

	public String getTptState() {
		return this.tptState;
	}

	public void setTptState(String tptState) {
		this.tptState = tptState;
	}

	public Integer getTptOrder() {
		return this.tptOrder;
	}

	public void setTptOrder(Integer tptOrder) {
		this.tptOrder = tptOrder;
	}

	public Date getTptCdate() {
		return this.tptCdate;
	}

	public void setTptCdate(Date tptCdate) {
		this.tptCdate = tptCdate;
	}

	public Date getTptUdate() {
		return this.tptUdate;
	}

	public void setTptUdate(Date tptUdate) {
		this.tptUdate = tptUdate;
	}

	public String getTptCuser() {
		return this.tptCuser;
	}

	public void setTptCuser(String tptCuser) {
		this.tptCuser = tptCuser;
	}

	public String getTptUuser() {
		return this.tptUuser;
	}

	public void setTptUuser(String tptUuser) {
		this.tptUuser = tptUuser;
	}

	public String getTptModeid() {
		return this.tptModeid;
	}

	public void setTptModeid(String tptModeid) {
		this.tptModeid = tptModeid;
	}

	public String getTptName() {
		return this.tptName;
	}

	public void setTptName(String tptName) {
		this.tptName = tptName;
	}

	public String getTptNumstime() {
		return this.tptNumstime;
	}

	public void setTptNumstime(String tptNumstime) {
		this.tptNumstime = tptNumstime;
	}

	public Integer getTptPrice() {
		return this.tptPrice;
	}

	public void setTptPrice(Integer tptPrice) {
		this.tptPrice = tptPrice;
	}

	public String getTptDemourl() {
		return this.tptDemourl;
	}

	public void setTptDemourl(String tptDemourl) {
		this.tptDemourl = tptDemourl;
	}

	public String getTptDesc() {
		return this.tptDesc;
	}

	public void setTptDesc(String tptDesc) {
		this.tptDesc = tptDesc;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttptUnid(unid):").append(tptUnid);
		buffer.append("\ttptState(状态):").append(tptState);
		buffer.append("\ttptOrder(序号):").append(tptOrder);
		buffer.append("\ttptCdate(创建日期):").append(tptCdate);
		buffer.append("\n");
		buffer.append("\ttptUdate(最后修改日期):").append(tptUdate);
		buffer.append("\ttptCuser(创建者):").append(tptCuser);
		buffer.append("\ttptUuser(最后修改者):").append(tptUuser);
		buffer.append("\ttptModeid(渠道id):").append(tptModeid);
		buffer.append("\n");
		buffer.append("\ttptName(投放位名):").append(tptName);
		buffer.append("\ttptNumstime(限制生效时间):").append(tptNumstime);
		buffer.append("\ttptPrice(原价):").append(tptPrice);
		buffer.append("\ttptDemourl(示例图地址):").append(tptDemourl);
		buffer.append("\n");
		buffer.append("\ttptDesc(描述):").append(tptDesc);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
