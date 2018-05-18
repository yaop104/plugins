package com.sme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sme.core.model.BaseObject;

import java.util.Date;

/**
 * 账号(SysAcc)模型对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysAcc extends BaseObject{
	/**
	 *
	 */
	private static final long serialVersionUID = -8013792321824512698L;

	//======================字段列表========================
	/** id */
	private Integer	sysAccId;

	/** 昵称 */
	private String	sysAccName;

	/** 真实姓名 */
	private String	sysAccRealname;

	/** 状态 */
	private String	sysAccState;

	/** 密码 */
	private String	sysAccPassword;

	/** 描述 */
	private String	sysAccDesc;

	/** 角色ID */
	private String	sysAccRoleid;

	/** 组织ID */
	private String	sysAccOrgid;

	/** 记录日期 */
	private Date	sysAccCdate;

	/** 更新日期 */
	private Date	sysAccUdate;

	/** 记录人 */
	private String	sysAccCuser;

	/** 创建人 */
	private String	sysAccUueser;

	/** 类型 1用户2开发商3财务4系统 **/
	private String sysAccType;

	/** 手机号 **/
	private String sysAccMobile;

	/** 类型 1后台2app **/
	private String sysAccUserType;

	/** token **/
	private String sysAccToken;

	private String orgname;

	private String rolename;

	private String firstPassword;

	private String secondPassword;

	private String tsmSendCode;

	public String getTsmSendCode() {
		return tsmSendCode;
	}

	public void setTsmSendCode(String tsmSendCode) {
		this.tsmSendCode = tsmSendCode;
	}

	public String getFirstPassword() {
		return firstPassword;
	}

	public void setFirstPassword(String firstPassword) {
		this.firstPassword = firstPassword;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}

	public String getSysAccUserType() {
		return sysAccUserType;
	}

	public void setSysAccUserType(String sysAccUserType) {
		this.sysAccUserType = sysAccUserType;
	}

	public String getSysAccToken() {
		return sysAccToken;
	}

	public void setSysAccToken(String sysAccToken) {
		this.sysAccToken = sysAccToken;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getSysAccEmail() {
		return sysAccEmail;
	}

	public void setSysAccEmail(String sysAccEmail) {
		this.sysAccEmail = sysAccEmail;
	}

	public String getSysAccHead() {
		return sysAccHead;
	}

	public void setSysAccHead(String sysAccHead) {
		this.sysAccHead = sysAccHead;
	}

	private  String sysAccEmail;

	private  String sysAccHead;

	public Integer getSysAccMoney() {
		return sysAccMoney;
	}

	public void setSysAccMoney(Integer sysAccMoney) {
		this.sysAccMoney = sysAccMoney;
	}

	private Integer sysAccMoney;

	public String getSysAccMobile() {
		return sysAccMobile;
	}

	public void setSysAccMobile(String sysAccMobile) {
		this.sysAccMobile = sysAccMobile;
	}



	public String getSysAccType() {
		return sysAccType;
	}

	public void setSysAccType(String sysAccType) {
		this.sysAccType = sysAccType;
	}

	public Integer getSysAccId() {
		return this.sysAccId;
	}

	public void setSysAccId(Integer sysAccId) {
		this.sysAccId = sysAccId;
	}

	public String getSysAccName() {
		return this.sysAccName;
	}

	public void setSysAccName(String sysAccName) {
		this.sysAccName = sysAccName;
	}

	public String getSysAccRealname() {
		return this.sysAccRealname;
	}

	public void setSysAccRealname(String sysAccRealname) {
		this.sysAccRealname = sysAccRealname;
	}

	public String getSysAccState() {
		return this.sysAccState;
	}

	public void setSysAccState(String sysAccState) {
		this.sysAccState = sysAccState;
	}

	public String getSysAccPassword() {
		return this.sysAccPassword;
	}

	public void setSysAccPassword(String sysAccPassword) {
		this.sysAccPassword = sysAccPassword;
	}

	public String getSysAccDesc() {
		return this.sysAccDesc;
	}

	public void setSysAccDesc(String sysAccDesc) {
		this.sysAccDesc = sysAccDesc;
	}

	public String getSysAccRoleid() {
		return this.sysAccRoleid;
	}

	public void setSysAccRoleid(String sysAccRoleid) {
		this.sysAccRoleid = sysAccRoleid;
	}

	public String getSysAccOrgid() {
		return this.sysAccOrgid;
	}

	public void setSysAccOrgid(String sysAccOrgid) {
		this.sysAccOrgid = sysAccOrgid;
	}

	public Date getSysAccCdate() {
		return this.sysAccCdate;
	}

	public void setSysAccCdate(Date sysAccCdate) {
		this.sysAccCdate = sysAccCdate;
	}

	public Date getSysAccUdate() {
		return this.sysAccUdate;
	}

	public void setSysAccUdate(Date sysAccUdate) {
		this.sysAccUdate = sysAccUdate;
	}

	public String getSysAccCuser() {
		return this.sysAccCuser;
	}

	public void setSysAccCuser(String sysAccCuser) {
		this.sysAccCuser = sysAccCuser;
	}

	public String getSysAccUueser() {
		return this.sysAccUueser;
	}

	public void setSysAccUueser(String sysAccUueser) {
		this.sysAccUueser = sysAccUueser;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tsysAccId(id):").append(sysAccId);
		buffer.append("\tsysAccName(昵称):").append(sysAccName);
		buffer.append("\tsysAccRealname(真实姓名):").append(sysAccRealname);
		buffer.append("\tsysAccState(状态):").append(sysAccState);
		buffer.append("\n");
		buffer.append("\tsysAccPassword(密码):").append(sysAccPassword);
		buffer.append("\tsysAccDesc(描述):").append(sysAccDesc);
		buffer.append("\tsysAccRoleid(角色ID):").append(sysAccRoleid);
		buffer.append("\tsysAccOrgid(组织ID):").append(sysAccOrgid);
		buffer.append("\n");
		buffer.append("\tsysAccCdate(记录日期):").append(sysAccCdate);
		buffer.append("\tsysAccUdate(更新日期):").append(sysAccUdate);
		buffer.append("\tsysAccCuser(记录人):").append(sysAccCuser);
		buffer.append("\tsysAccUueser(创建人):").append(sysAccUueser);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
