package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.util.Date;

/**
 * 发送列表(PlgSend)模型对象
 */
public class PlgSend extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4613604247135666269L;

	//======================字段列表========================
	/** unid */
	private Integer	psdUnid;

	/** uuid */
	private String	psdUuid;

	/** 状态 */
	private String	psdState;

	/** 序号 */
	private Integer	psdOrder;

	/** 创建日期 */
	private Date	psdCdate;

	/** 最后修改日期 */
	private Date	psdUdate;

	/** 创建者 */
	private String	psdCuser;

	/** 最后修改者 */
	private String	psdUuser;

	/** 插件名称 */
	private String	psdPlgname;

	/** 插件版本 */
	private String	psdPlgversion;

	/** 插件地址 */
	private String	psdPlgurl;

	/** 推送账号 */
	private String	psdAccount;

	/** 是否发送 */
	private String	psdIssend;

	/** 插件详情id */
	private Integer	psdAppid;
	
	
//	
	private String psdsize;
	private String psddesc;

	public Integer getPsdUnid() {
		return this.psdUnid;
	}

	public void setPsdUnid(Integer psdUnid) {
		this.psdUnid = psdUnid;
	}

	public String getPsdUuid() {
		return this.psdUuid;
	}

	public void setPsdUuid(String psdUuid) {
		this.psdUuid = psdUuid;
	}

	public String getPsdState() {
		return this.psdState;
	}

	public void setPsdState(String psdState) {
		this.psdState = psdState;
	}

	public Integer getPsdOrder() {
		return this.psdOrder;
	}

	public void setPsdOrder(Integer psdOrder) {
		this.psdOrder = psdOrder;
	}

	public Date getPsdCdate() {
		return this.psdCdate;
	}

	public void setPsdCdate(Date psdCdate) {
		this.psdCdate = psdCdate;
	}

	public Date getPsdUdate() {
		return this.psdUdate;
	}

	public void setPsdUdate(Date psdUdate) {
		this.psdUdate = psdUdate;
	}

	public String getPsdCuser() {
		return this.psdCuser;
	}

	public void setPsdCuser(String psdCuser) {
		this.psdCuser = psdCuser;
	}

	public String getPsdUuser() {
		return this.psdUuser;
	}

	public void setPsdUuser(String psdUuser) {
		this.psdUuser = psdUuser;
	}

	public String getPsdPlgname() {
		return this.psdPlgname;
	}

	public void setPsdPlgname(String psdPlgname) {
		this.psdPlgname = psdPlgname;
	}

	public String getPsdPlgversion() {
		return this.psdPlgversion;
	}

	public void setPsdPlgversion(String psdPlgversion) {
		this.psdPlgversion = psdPlgversion;
	}

	public String getPsdPlgurl() {
		return this.psdPlgurl;
	}

	public void setPsdPlgurl(String psdPlgurl) {
		this.psdPlgurl = psdPlgurl;
	}

	public String getPsdAccount() {
		return this.psdAccount;
	}

	public void setPsdAccount(String psdAccount) {
		this.psdAccount = psdAccount;
	}

	public String getPsdIssend() {
		return this.psdIssend;
	}

	public void setPsdIssend(String psdIssend) {
		this.psdIssend = psdIssend;
	}

	public Integer getPsdAppid() {
		return this.psdAppid;
	}

	public void setPsdAppid(Integer psdAppid) {
		this.psdAppid = psdAppid;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tpsdUnid(unid):").append(psdUnid);
		buffer.append("\tpsdUuid(uuid):").append(psdUuid);
		buffer.append("\tpsdState(状态):").append(psdState);
		buffer.append("\tpsdOrder(序号):").append(psdOrder);
		buffer.append("\n");
		buffer.append("\tpsdCdate(创建日期):").append(psdCdate);
		buffer.append("\tpsdUdate(最后修改日期):").append(psdUdate);
		buffer.append("\tpsdCuser(创建者):").append(psdCuser);
		buffer.append("\tpsdUuser(最后修改者):").append(psdUuser);
		buffer.append("\n");
		buffer.append("\tpsdPlgname(插件名称):").append(psdPlgname);
		buffer.append("\tpsdPlgversion(插件版本):").append(psdPlgversion);
		buffer.append("\tpsdPlgurl(插件地址):").append(psdPlgurl);
		buffer.append("\tpsdAccount(推送账号):").append(psdAccount);
		buffer.append("\n");
		buffer.append("\tpsdIssend(是否发送):").append(psdIssend);
		buffer.append("\tpsdAppid(插件详情id):").append(psdAppid);
		return buffer.toString();
	}
	//================== begin ======================

	public String getPsdsize()
	{
		return psdsize;
	}

	public void setPsdsize(String psdsize)
	{
		this.psdsize = psdsize;
	}

	public String getPsddesc()
	{
		return psddesc;
	}

	public void setPsddesc(String psddesc)
	{
		this.psddesc = psddesc;
	}

	//================== end ======================
}
