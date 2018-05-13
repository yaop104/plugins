package com.sme.entity;

import java.util.Date;

import com.sme.core.model.BaseObject;

/**
 * 用户管理(PlgUser)模型对象
 */
public class PlgUser extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5242084226143307187L;

	//======================字段列表========================
	/** unid */
	private Integer	pusUnid;

	/** uuid */
	private String	pusUuid;

	/** 状态 */
	private String	pusState;

	/** 序号 */
	private Integer	pusOrder;

	/** 创建日期 */
	private Date	pusCdate;

	/** 最后修改日期 */
	private Date	pusUdate;

	/** 创建者 */
	private String	pusCuser;

	/** 最后修改者 */
	private String	pusUuser;

	/** 名字 */
	private String	pusName;

	/** 性别 */
	private String	pusSex;

	/** 年纪 */
	private Integer	pusAge;

	/** 电话 */
	private String	pusMobile;

	/** 地址 */
	private String	pusAddress;

	/** 分组 */
	private Integer	pusGroupid;

	/** 数据来源 */
	private String	pusFromtype;

	public Integer getPusUnid() {
		return this.pusUnid;
	}

	public void setPusUnid(Integer pusUnid) {
		this.pusUnid = pusUnid;
	}

	public String getPusUuid() {
		return this.pusUuid;
	}

	public void setPusUuid(String pusUuid) {
		this.pusUuid = pusUuid;
	}

	public String getPusState() {
		return this.pusState;
	}

	public void setPusState(String pusState) {
		this.pusState = pusState;
	}

	public Integer getPusOrder() {
		return this.pusOrder;
	}

	public void setPusOrder(Integer pusOrder) {
		this.pusOrder = pusOrder;
	}

	public Date getPusCdate() {
		return this.pusCdate;
	}

	public void setPusCdate(Date pusCdate) {
		this.pusCdate = pusCdate;
	}

	public Date getPusUdate() {
		return this.pusUdate;
	}

	public void setPusUdate(Date pusUdate) {
		this.pusUdate = pusUdate;
	}

	public String getPusCuser() {
		return this.pusCuser;
	}

	public void setPusCuser(String pusCuser) {
		this.pusCuser = pusCuser;
	}

	public String getPusUuser() {
		return this.pusUuser;
	}

	public void setPusUuser(String pusUuser) {
		this.pusUuser = pusUuser;
	}

	public String getPusName() {
		return this.pusName;
	}

	public void setPusName(String pusName) {
		this.pusName = pusName;
	}

	public String getPusSex() {
		return this.pusSex;
	}

	public void setPusSex(String pusSex) {
		this.pusSex = pusSex;
	}

	public Integer getPusAge() {
		return this.pusAge;
	}

	public void setPusAge(Integer pusAge) {
		this.pusAge = pusAge;
	}

	public String getPusMobile() {
		return this.pusMobile;
	}

	public void setPusMobile(String pusMobile) {
		this.pusMobile = pusMobile;
	}

	public String getPusAddress() {
		return this.pusAddress;
	}

	public void setPusAddress(String pusAddress) {
		this.pusAddress = pusAddress;
	}

	public Integer getPusGroupid() {
		return this.pusGroupid;
	}

	public void setPusGroupid(Integer pusGroupid) {
		this.pusGroupid = pusGroupid;
	}

	public String getPusFromtype() {
		return this.pusFromtype;
	}

	public void setPusFromtype(String pusFromtype) {
		this.pusFromtype = pusFromtype;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tpusUnid(unid):").append(pusUnid);
		buffer.append("\tpusUuid(uuid):").append(pusUuid);
		buffer.append("\tpusState(状态):").append(pusState);
		buffer.append("\tpusOrder(序号):").append(pusOrder);
		buffer.append("\n");
		buffer.append("\tpusCdate(创建日期):").append(pusCdate);
		buffer.append("\tpusUdate(最后修改日期):").append(pusUdate);
		buffer.append("\tpusCuser(创建者):").append(pusCuser);
		buffer.append("\tpusUuser(最后修改者):").append(pusUuser);
		buffer.append("\n");
		buffer.append("\tpusName(名字):").append(pusName);
		buffer.append("\tpusSex(性别):").append(pusSex);
		buffer.append("\tpusAge(年纪):").append(pusAge);
		buffer.append("\tpusMobile(电话):").append(pusMobile);
		buffer.append("\n");
		buffer.append("\tpusAddress(地址):").append(pusAddress);
		buffer.append("\tpusGroupid(分组):").append(pusGroupid);
		buffer.append("\tpusFromtype(数据来源):").append(pusFromtype);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
