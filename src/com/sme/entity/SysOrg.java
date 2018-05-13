package com.sme.entity;

import java.util.Date;

import com.sme.core.model.BaseObject;

/**
 * 组织(SysOrg)模型对象
 */
public class SysOrg extends BaseObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -113389821959035997L;

	//======================字段列表========================
	/** id */
	private Integer	sysOrgId;

	/** 编号 */
	private String	sysOrgCode;

	/** 名称 */
	private String	sysOrgName;

	/** 父编号 */
	private String	sysOrgPcode;

	/** 状态 */
	private String	sysOrgState;

	/** 排序 */
	private Integer	sysOrgOrder;

	/** 描述 */
	private String	sysOrgDesc;

	/** 类型 */
	private String	sysOrgType;

	/** 外部编号 */
	private String	sysOrgOutercode;

	/** 记录日期 */
	private Date	sysOrgCdate;

	/** 更新日期 */
	private Date	sysOrgUdate;

	/** 记录人 */
	private String	sysOrgCuser;

	/** 创建人 */
	private String	sysOrgUueser;

	public Integer getSysOrgId() {
		return this.sysOrgId;
	}

	public void setSysOrgId(Integer sysOrgId) {
		this.sysOrgId = sysOrgId;
	}

	public String getSysOrgCode() {
		return this.sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public String getSysOrgName() {
		return this.sysOrgName;
	}

	public void setSysOrgName(String sysOrgName) {
		this.sysOrgName = sysOrgName;
	}

	public String getSysOrgPcode() {
		return this.sysOrgPcode;
	}

	public void setSysOrgPcode(String sysOrgPcode) {
		this.sysOrgPcode = sysOrgPcode;
	}

	public String getSysOrgState() {
		return this.sysOrgState;
	}

	public void setSysOrgState(String sysOrgState) {
		this.sysOrgState = sysOrgState;
	}

	public Integer getSysOrgOrder() {
		return this.sysOrgOrder;
	}

	public void setSysOrgOrder(Integer sysOrgOrder) {
		this.sysOrgOrder = sysOrgOrder;
	}

	public String getSysOrgDesc() {
		return this.sysOrgDesc;
	}

	public void setSysOrgDesc(String sysOrgDesc) {
		this.sysOrgDesc = sysOrgDesc;
	}

	public String getSysOrgType() {
		return this.sysOrgType;
	}

	public void setSysOrgType(String sysOrgType) {
		this.sysOrgType = sysOrgType;
	}

	public String getSysOrgOutercode() {
		return this.sysOrgOutercode;
	}

	public void setSysOrgOutercode(String sysOrgOutercode) {
		this.sysOrgOutercode = sysOrgOutercode;
	}

	public Date getSysOrgCdate() {
		return this.sysOrgCdate;
	}

	public void setSysOrgCdate(Date sysOrgCdate) {
		this.sysOrgCdate = sysOrgCdate;
	}

	public Date getSysOrgUdate() {
		return this.sysOrgUdate;
	}

	public void setSysOrgUdate(Date sysOrgUdate) {
		this.sysOrgUdate = sysOrgUdate;
	}

	public String getSysOrgCuser() {
		return this.sysOrgCuser;
	}

	public void setSysOrgCuser(String sysOrgCuser) {
		this.sysOrgCuser = sysOrgCuser;
	}

	public String getSysOrgUueser() {
		return this.sysOrgUueser;
	}

	public void setSysOrgUueser(String sysOrgUueser) {
		this.sysOrgUueser = sysOrgUueser;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tsysOrgId(id):").append(sysOrgId);
		buffer.append("\tsysOrgCode(编号):").append(sysOrgCode);
		buffer.append("\tsysOrgName(名称):").append(sysOrgName);
		buffer.append("\tsysOrgPcode(父编号):").append(sysOrgPcode);
		buffer.append("\n");
		buffer.append("\tsysOrgState(状态):").append(sysOrgState);
		buffer.append("\tsysOrgOrder(排序):").append(sysOrgOrder);
		buffer.append("\tsysOrgDesc(描述):").append(sysOrgDesc);
		buffer.append("\tsysOrgType(类型):").append(sysOrgType);
		buffer.append("\n");
		buffer.append("\tsysOrgOutercode(外部编号):").append(sysOrgOutercode);
		buffer.append("\tsysOrgCdate(记录日期):").append(sysOrgCdate);
		buffer.append("\tsysOrgUdate(更新日期):").append(sysOrgUdate);
		buffer.append("\tsysOrgCuser(记录人):").append(sysOrgCuser);
		buffer.append("\n");
		buffer.append("\tsysOrgUueser(创建人):").append(sysOrgUueser);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
