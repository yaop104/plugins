package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.util.Date;

/**
 * 流水表(TstStatement)模型对象
 */
public class TstStatement extends BaseObject {
	//======================字段列表========================
	/** id */
	private Integer	tstStatementUnid;

	/** 记录日期 */
	private Date	tstStatementCdate;

	/** 类型1收入2支出 */
	private String	tstStatementType;

	/** 类型1充值2转账3导入 */
	private String	tstStatementCounttype;

	/** 金额 */
	private Float	tstStatementAccount;

	/** 合同id */
	private Integer	tstStatementContractid;

	/** 订单id */
	private Integer	tstStatementOrderid;

	/** 余额 */
	private Float	tstStatementRemainder;

	/** 利润 */
	private Float	tstStatementProfit;

	/** 备注 */
	private String	tstStatementRemark;

	/** 产生人id */
	private Integer	tstStatementBasicinfoid;

	public Integer getTstStatementUnid() {
		return this.tstStatementUnid;
	}

	public void setTstStatementUnid(Integer tstStatementUnid) {
		this.tstStatementUnid = tstStatementUnid;
	}

	public Date getTstStatementCdate() {
		return this.tstStatementCdate;
	}

	public void setTstStatementCdate(Date tstStatementCdate) {
		this.tstStatementCdate = tstStatementCdate;
	}

	public String getTstStatementType() {
		return this.tstStatementType;
	}

	public void setTstStatementType(String tstStatementType) {
		this.tstStatementType = tstStatementType;
	}

	public String getTstStatementCounttype() {
		return this.tstStatementCounttype;
	}

	public void setTstStatementCounttype(String tstStatementCounttype) {
		this.tstStatementCounttype = tstStatementCounttype;
	}

	public Float getTstStatementAccount() {
		return this.tstStatementAccount;
	}

	public void setTstStatementAccount(Float tstStatementAccount) {
		this.tstStatementAccount = tstStatementAccount;
	}

	public Integer getTstStatementContractid() {
		return this.tstStatementContractid;
	}

	public void setTstStatementContractid(Integer tstStatementContractid) {
		this.tstStatementContractid = tstStatementContractid;
	}

	public Integer getTstStatementOrderid() {
		return this.tstStatementOrderid;
	}

	public void setTstStatementOrderid(Integer tstStatementOrderid) {
		this.tstStatementOrderid = tstStatementOrderid;
	}

	public Float getTstStatementRemainder() {
		return this.tstStatementRemainder;
	}

	public void setTstStatementRemainder(Float tstStatementRemainder) {
		this.tstStatementRemainder = tstStatementRemainder;
	}

	public Float getTstStatementProfit() {
		return this.tstStatementProfit;
	}

	public void setTstStatementProfit(Float tstStatementProfit) {
		this.tstStatementProfit = tstStatementProfit;
	}

	public String getTstStatementRemark() {
		return this.tstStatementRemark;
	}

	public void setTstStatementRemark(String tstStatementRemark) {
		this.tstStatementRemark = tstStatementRemark;
	}

	public Integer getTstStatementBasicinfoid() {
		return this.tstStatementBasicinfoid;
	}

	public void setTstStatementBasicinfoid(Integer tstStatementBasicinfoid) {
		this.tstStatementBasicinfoid = tstStatementBasicinfoid;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttstStatementUnid(id):").append(tstStatementUnid);
		buffer.append("\ttstStatementCdate(记录日期):").append(tstStatementCdate);
		buffer.append("\ttstStatementType(类型1收入2支出):").append(tstStatementType);
		buffer.append("\ttstStatementCounttype(类型1充值2转账3导入):").append(tstStatementCounttype);
		buffer.append("\n");
		buffer.append("\ttstStatementAccount(金额):").append(tstStatementAccount);
		buffer.append("\ttstStatementContractid(合同id):").append(tstStatementContractid);
		buffer.append("\ttstStatementOrderid(订单id):").append(tstStatementOrderid);
		buffer.append("\ttstStatementRemainder(余额):").append(tstStatementRemainder);
		buffer.append("\n");
		buffer.append("\ttstStatementProfit(利润):").append(tstStatementProfit);
		buffer.append("\ttstStatementRemark(备注):").append(tstStatementRemark);
		buffer.append("\ttstStatementBasicinfoid(产生人id):").append(tstStatementBasicinfoid);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
