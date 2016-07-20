package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.util.Date;

/**
 * 申请表(TapApplication)模型对象
 */
public class TapApplication extends BaseObject {
	//======================字段列表========================
	/** unid */
	private Integer	tapApplicationUnid;

	/** 状态 */
	private String	tapApplicationState;

	/** 序号 */
	private Integer	tapApplicationOrder;

	/** 创建日期 */
	private Date	tapApplicationCdate;

	/** 最后修改日期 */
	private Date	tapApplicationUdate;

	/** 创建者 */
	private Integer	tapApplicationCuser;

	/** 最后修改者 */
	private Integer	tapApplicationUuser;

	/** 选项名称 */
	private String	tapApplicationMoneyid;

	/** 选项值 */
	private Integer	tapApplicationAppname;

	/** 凭证url */
	private String	tapApplicationUrl;

	/** 审核人 */
	private String	tapApplicationCheckname;

	/** 审核状态 */
	private String	tapApplicationCheckstate;

	/** 审核时间 */
	private Date	tapApplicationChecktime;

	/** 审核备注 */
	private String	tapApplicationCheckdesc;

	public Integer getTapApplicationUnid() {
		return this.tapApplicationUnid;
	}

	public void setTapApplicationUnid(Integer tapApplicationUnid) {
		this.tapApplicationUnid = tapApplicationUnid;
	}

	public String getTapApplicationState() {
		return this.tapApplicationState;
	}

	public void setTapApplicationState(String tapApplicationState) {
		this.tapApplicationState = tapApplicationState;
	}

	public Integer getTapApplicationOrder() {
		return this.tapApplicationOrder;
	}

	public void setTapApplicationOrder(Integer tapApplicationOrder) {
		this.tapApplicationOrder = tapApplicationOrder;
	}

	public Date getTapApplicationCdate() {
		return this.tapApplicationCdate;
	}

	public void setTapApplicationCdate(Date tapApplicationCdate) {
		this.tapApplicationCdate = tapApplicationCdate;
	}

	public Date getTapApplicationUdate() {
		return this.tapApplicationUdate;
	}

	public void setTapApplicationUdate(Date tapApplicationUdate) {
		this.tapApplicationUdate = tapApplicationUdate;
	}

	public Integer getTapApplicationCuser() {
		return this.tapApplicationCuser;
	}

	public void setTapApplicationCuser(Integer tapApplicationCuser) {
		this.tapApplicationCuser = tapApplicationCuser;
	}

	public Integer getTapApplicationUuser() {
		return this.tapApplicationUuser;
	}

	public void setTapApplicationUuser(Integer tapApplicationUuser) {
		this.tapApplicationUuser = tapApplicationUuser;
	}

	public String getTapApplicationMoneyid() {
		return this.tapApplicationMoneyid;
	}

	public void setTapApplicationMoneyid(String tapApplicationMoneyid) {
		this.tapApplicationMoneyid = tapApplicationMoneyid;
	}

	public Integer getTapApplicationAppname() {
		return this.tapApplicationAppname;
	}

	public void setTapApplicationAppname(Integer tapApplicationAppname) {
		this.tapApplicationAppname = tapApplicationAppname;
	}

	public String getTapApplicationUrl() {
		return this.tapApplicationUrl;
	}

	public void setTapApplicationUrl(String tapApplicationUrl) {
		this.tapApplicationUrl = tapApplicationUrl;
	}

	public String getTapApplicationCheckname() {
		return this.tapApplicationCheckname;
	}

	public void setTapApplicationCheckname(String tapApplicationCheckname) {
		this.tapApplicationCheckname = tapApplicationCheckname;
	}

	public String getTapApplicationCheckstate() {
		return this.tapApplicationCheckstate;
	}

	public void setTapApplicationCheckstate(String tapApplicationCheckstate) {
		this.tapApplicationCheckstate = tapApplicationCheckstate;
	}

	public Date getTapApplicationChecktime() {
		return this.tapApplicationChecktime;
	}

	public void setTapApplicationChecktime(Date tapApplicationChecktime) {
		this.tapApplicationChecktime = tapApplicationChecktime;
	}

	public String getTapApplicationCheckdesc() {
		return this.tapApplicationCheckdesc;
	}

	public void setTapApplicationCheckdesc(String tapApplicationCheckdesc) {
		this.tapApplicationCheckdesc = tapApplicationCheckdesc;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttapApplicationUnid(unid):").append(tapApplicationUnid);
		buffer.append("\ttapApplicationState(状态):").append(tapApplicationState);
		buffer.append("\ttapApplicationOrder(序号):").append(tapApplicationOrder);
		buffer.append("\ttapApplicationCdate(创建日期):").append(tapApplicationCdate);
		buffer.append("\n");
		buffer.append("\ttapApplicationUdate(最后修改日期):").append(tapApplicationUdate);
		buffer.append("\ttapApplicationCuser(创建者):").append(tapApplicationCuser);
		buffer.append("\ttapApplicationUuser(最后修改者):").append(tapApplicationUuser);
		buffer.append("\ttapApplicationMoneyid(选项名称):").append(tapApplicationMoneyid);
		buffer.append("\n");
		buffer.append("\ttapApplicationAppname(选项值):").append(tapApplicationAppname);
		buffer.append("\ttapApplicationUrl(凭证url):").append(tapApplicationUrl);
		buffer.append("\ttapApplicationCheckname(审核人):").append(tapApplicationCheckname);
		buffer.append("\ttapApplicationCheckstate(审核状态):").append(tapApplicationCheckstate);
		buffer.append("\n");
		buffer.append("\ttapApplicationChecktime(审核时间):").append(tapApplicationChecktime);
		buffer.append("\ttapApplicationCheckdesc(审核备注):").append(tapApplicationCheckdesc);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
