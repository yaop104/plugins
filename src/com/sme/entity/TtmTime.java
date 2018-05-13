package com.sme.entity;

import java.io.Serializable;

import java.util.Date;

/**
 * 订单投放时间管理(TtmTime)模型对象
 */
public class TtmTime implements Serializable {
	//======================字段列表========================
	/** id */
	private Integer	ttmTimeUnid;

	/** 状态1全部2删除3增加 */
	private String	ttmTimeState;

	/** 记录日期 */
	private Date	ttmTimeCdate;

	/** 更新日期 */
	private Date	ttmTimeUdate;

	/** 记录人 */
	private Integer	ttmTimeCuser;

	/** 创建人 */
	private Integer	ttmTimeUuser;

	/** 投放位id */
	private Integer	ttmTimePositionid;

	/** 投放时间 */
	private Date	ttmTimeTime;

	/** 订单id */
	private Integer	ttmTimeOrderid;

	public Integer getTtmTimeUnid() {
		return this.ttmTimeUnid;
	}

	public void setTtmTimeUnid(Integer ttmTimeUnid) {
		this.ttmTimeUnid = ttmTimeUnid;
	}

	public String getTtmTimeState() {
		return this.ttmTimeState;
	}

	public void setTtmTimeState(String ttmTimeState) {
		this.ttmTimeState = ttmTimeState;
	}

	public Date getTtmTimeCdate() {
		return this.ttmTimeCdate;
	}

	public void setTtmTimeCdate(Date ttmTimeCdate) {
		this.ttmTimeCdate = ttmTimeCdate;
	}

	public Date getTtmTimeUdate() {
		return this.ttmTimeUdate;
	}

	public void setTtmTimeUdate(Date ttmTimeUdate) {
		this.ttmTimeUdate = ttmTimeUdate;
	}

	public Integer getTtmTimeCuser() {
		return this.ttmTimeCuser;
	}

	public void setTtmTimeCuser(Integer ttmTimeCuser) {
		this.ttmTimeCuser = ttmTimeCuser;
	}

	public Integer getTtmTimeUuser() {
		return this.ttmTimeUuser;
	}

	public void setTtmTimeUuser(Integer ttmTimeUuser) {
		this.ttmTimeUuser = ttmTimeUuser;
	}

	public Integer getTtmTimePositionid() {
		return this.ttmTimePositionid;
	}

	public void setTtmTimePositionid(Integer ttmTimePositionid) {
		this.ttmTimePositionid = ttmTimePositionid;
	}

	public Date getTtmTimeTime() {
		return this.ttmTimeTime;
	}

	public void setTtmTimeTime(Date ttmTimeTime) {
		this.ttmTimeTime = ttmTimeTime;
	}

	public Integer getTtmTimeOrderid() {
		return this.ttmTimeOrderid;
	}

	public void setTtmTimeOrderid(Integer ttmTimeOrderid) {
		this.ttmTimeOrderid = ttmTimeOrderid;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tttmTimeUnid(id):").append(ttmTimeUnid);
		buffer.append("\tttmTimeState(状态1全部2删除3增加):").append(ttmTimeState);
		buffer.append("\tttmTimeCdate(记录日期):").append(ttmTimeCdate);
		buffer.append("\tttmTimeUdate(更新日期):").append(ttmTimeUdate);
		buffer.append("\n");
		buffer.append("\tttmTimeCuser(记录人):").append(ttmTimeCuser);
		buffer.append("\tttmTimeUuser(创建人):").append(ttmTimeUuser);
		buffer.append("\tttmTimePositionid(投放位id):").append(ttmTimePositionid);
		buffer.append("\tttmTimeTime(投放时间):").append(ttmTimeTime);
		buffer.append("\n");
		buffer.append("\tttmTimeOrderid(订单id):").append(ttmTimeOrderid);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
