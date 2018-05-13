package com.sme.entity;

import java.io.Serializable;
import java.util.Date;

import com.sme.core.model.BaseObject;

/**
 * 用户分组管理(PlgGroup)模型对象
 */
public class PlgGroup extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6098760069195765102L;

	//======================字段列表========================
	/** unid */
	private Integer	pgpUnid;

	/** uuid */
	private String	pgpUuid;

	/** 状态 */
	private String	pgpState;

	/** 序号 */
	private Integer	pgpOrder;

	/** 创建日期 */
	private Date	pgpCdate;

	/** 最后修改日期 */
	private Date	pgpUdate;

	/** 创建者 */
	private String	pgpCuser;

	/** 最后修改者 */
	private String	pgpUuser;

	/** 分组名称 */
	private String	pgpName;

	public Integer getPgpUnid() {
		return this.pgpUnid;
	}

	public void setPgpUnid(Integer pgpUnid) {
		this.pgpUnid = pgpUnid;
	}

	public String getPgpUuid() {
		return this.pgpUuid;
	}

	public void setPgpUuid(String pgpUuid) {
		this.pgpUuid = pgpUuid;
	}

	public String getPgpState() {
		return this.pgpState;
	}

	public void setPgpState(String pgpState) {
		this.pgpState = pgpState;
	}

	public Integer getPgpOrder() {
		return this.pgpOrder;
	}

	public void setPgpOrder(Integer pgpOrder) {
		this.pgpOrder = pgpOrder;
	}

	public Date getPgpCdate() {
		return this.pgpCdate;
	}

	public void setPgpCdate(Date pgpCdate) {
		this.pgpCdate = pgpCdate;
	}

	public Date getPgpUdate() {
		return this.pgpUdate;
	}

	public void setPgpUdate(Date pgpUdate) {
		this.pgpUdate = pgpUdate;
	}

	public String getPgpCuser() {
		return this.pgpCuser;
	}

	public void setPgpCuser(String pgpCuser) {
		this.pgpCuser = pgpCuser;
	}

	public String getPgpUuser() {
		return this.pgpUuser;
	}

	public void setPgpUuser(String pgpUuser) {
		this.pgpUuser = pgpUuser;
	}

	public String getPgpName() {
		return this.pgpName;
	}

	public void setPgpName(String pgpName) {
		this.pgpName = pgpName;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tpgpUnid(unid):").append(pgpUnid);
		buffer.append("\tpgpUuid(uuid):").append(pgpUuid);
		buffer.append("\tpgpState(状态):").append(pgpState);
		buffer.append("\tpgpOrder(序号):").append(pgpOrder);
		buffer.append("\n");
		buffer.append("\tpgpCdate(创建日期):").append(pgpCdate);
		buffer.append("\tpgpUdate(最后修改日期):").append(pgpUdate);
		buffer.append("\tpgpCuser(创建者):").append(pgpCuser);
		buffer.append("\tpgpUuser(最后修改者):").append(pgpUuser);
		buffer.append("\n");
		buffer.append("\tpgpName(分组名称):").append(pgpName);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
