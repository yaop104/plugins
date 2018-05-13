package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.io.Serializable;

import java.util.Date;

/**
 * 订单管理(TodOrder)模型对象
 */
public class TodOrder extends BaseObject implements Serializable {
	//======================字段列表========================
	/** id */
	private Integer	todOrderUnid;

	/** 订单编号 */
	private String	todOrderOrdernum;

	/** 状态 */
	private String	todOrderState;

	/** 记录日期 */
	private Date	todOrderCdate;

	/** 更新日期 */
	private Date	todOrderUdate;

	/** 记录人 */
	private Integer	todOrderCuser;

	/** 创建人 */
	private Integer	todOrderUuser;

	/** 客户id */
	private Integer	todOrderCustomid;

	/** 套餐id */
	private Integer	odOrderPackageid;

	private Integer todOrderPositionprice;

	private String todOrderPositionname;

	public String getTodOrderPositionurl() {
		return todOrderPositionurl;
	}

	public void setTodOrderPositionurl(String todOrderPositionurl) {
		this.todOrderPositionurl = todOrderPositionurl;
	}

	private String todOrderPositionurl;
	private Integer todOrderTotaldays;

	public Integer getTodOrderPositionprice() {
		return todOrderPositionprice;
	}

	public void setTodOrderPositionprice(Integer todOrderPositionprice) {
		this.todOrderPositionprice = todOrderPositionprice;
	}

	public String getTodOrderPositionname() {
		return todOrderPositionname;
	}

	public void setTodOrderPositionname(String todOrderPositionname) {
		this.todOrderPositionname = todOrderPositionname;
	}

	public Integer getTodOrderTotaldays() {
		return todOrderTotaldays;
	}

	public void setTodOrderTotaldays(Integer todOrderTotaldays) {
		this.todOrderTotaldays = todOrderTotaldays;
	}

	public Integer getTodOrderUnid() {
		return this.todOrderUnid;
	}

	public void setTodOrderUnid(Integer todOrderUnid) {
		this.todOrderUnid = todOrderUnid;
	}

	public String getTodOrderOrdernum() {
		return this.todOrderOrdernum;
	}

	public void setTodOrderOrdernum(String todOrderOrdernum) {
		this.todOrderOrdernum = todOrderOrdernum;
	}

	public String getTodOrderState() {
		return this.todOrderState;
	}

	public void setTodOrderState(String todOrderState) {
		this.todOrderState = todOrderState;
	}

	public Date getTodOrderCdate() {
		return this.todOrderCdate;
	}

	public void setTodOrderCdate(Date todOrderCdate) {
		this.todOrderCdate = todOrderCdate;
	}

	public Date getTodOrderUdate() {
		return this.todOrderUdate;
	}

	public void setTodOrderUdate(Date todOrderUdate) {
		this.todOrderUdate = todOrderUdate;
	}

	public Integer getTodOrderCuser() {
		return this.todOrderCuser;
	}

	public void setTodOrderCuser(Integer todOrderCuser) {
		this.todOrderCuser = todOrderCuser;
	}

	public Integer getTodOrderUuser() {
		return this.todOrderUuser;
	}

	public void setTodOrderUuser(Integer todOrderUuser) {
		this.todOrderUuser = todOrderUuser;
	}

	public Integer getTodOrderCustomid() {
		return this.todOrderCustomid;
	}

	public void setTodOrderCustomid(Integer todOrderCustomid) {
		this.todOrderCustomid = todOrderCustomid;
	}

	public Integer getOdOrderPackageid() {
		return this.odOrderPackageid;
	}

	public void setOdOrderPackageid(Integer odOrderPackageid) {
		this.odOrderPackageid = odOrderPackageid;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttodOrderUnid(id):").append(todOrderUnid);
		buffer.append("\ttodOrderOrdernum(订单编号):").append(todOrderOrdernum);
		buffer.append("\ttodOrderState(状态):").append(todOrderState);
		buffer.append("\ttodOrderCdate(记录日期):").append(todOrderCdate);
		buffer.append("\n");
		buffer.append("\ttodOrderUdate(更新日期):").append(todOrderUdate);
		buffer.append("\ttodOrderCuser(记录人):").append(todOrderCuser);
		buffer.append("\ttodOrderUuser(创建人):").append(todOrderUuser);
		buffer.append("\ttodOrderCustomid(客户id):").append(todOrderCustomid);
		buffer.append("\n");
		buffer.append("\todOrderPackageid(套餐id):").append(odOrderPackageid);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
