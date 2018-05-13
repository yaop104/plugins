package com.sme.entity;

import java.util.Date;

import com.sme.core.model.BaseObject;

/**
 * 应用基本信息(PApplication)模型对象
 */
public class PApplication extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5223321054009642147L;

	//======================字段列表========================
	/** id */
	private Integer	pAppId;

	/** 上传路径 */
	private String	pAppActionname;

	/** 包路径 */
	private String	pAppPackagename;

	/** 插件名称 */
	private String	pAppPluginname;

	/** 排序 */
	private Integer	pAppDisplaysort;

	/** 类型 */
	private String	pAppPlugintype;

	/** 使用数 */
	private Integer	pAppOpen;

	/** 点赞数 */
	private Integer	pAppPraise;

	/** 创建者 */
	private Integer	pAppCuser;

	/** 更新者 */
	private Integer	pAppUuper;

	/** 创建时间 */
	private Date	pAppCdate;

	/** 更新时间 */
	private Date	pAppUdate;
	
	/** 状态 */
	private String pAppAuditstate;

	/** 类型 */
	private String pAppBigType;

	/** 备注 */
	private String pAppRemark;

	public Integer getTagUnid() {
		return tagUnid;
	}

	public void setTagUnid(Integer tagUnid) {
		this.tagUnid = tagUnid;
	}

	private Integer tagUnid;

	private String pAppdetailVersionname;
	private String pAppdetailDesc;
	private String pAppdetailAdmindesc;
	private String pAppdetailAdminame;
	private Date pAppdetailDate;
	private String pAppdetailId;
	private String pAppdetailVersion;
	private String pAppdetailAuditstate;

	public Integer getpAppId()
	{
		return pAppId;
	}


	public void setpAppId(Integer pAppId)
	{
		this.pAppId = pAppId;
	}


	public String getpAppActionname()
	{
		return pAppActionname;
	}


	public void setpAppActionname(String pAppActionname)
	{
		this.pAppActionname = pAppActionname;
	}


	public String getpAppPackagename()
	{
		return pAppPackagename;
	}


	public void setpAppPackagename(String pAppPackagename)
	{
		this.pAppPackagename = pAppPackagename;
	}


	public String getpAppPluginname()
	{
		return pAppPluginname;
	}


	public void setpAppPluginname(String pAppPluginname)
	{
		this.pAppPluginname = pAppPluginname;
	}


	public Integer getpAppDisplaysort()
	{
		return pAppDisplaysort;
	}


	public void setpAppDisplaysort(Integer pAppDisplaysort)
	{
		this.pAppDisplaysort = pAppDisplaysort;
	}


	public String getpAppPlugintype()
	{
		return pAppPlugintype;
	}


	public void setpAppPlugintype(String pAppPlugintype)
	{
		this.pAppPlugintype = pAppPlugintype;
	}


	public Integer getpAppOpen()
	{
		return pAppOpen;
	}


	public void setpAppOpen(Integer pAppOpen)
	{
		this.pAppOpen = pAppOpen;
	}


	public Integer getpAppPraise()
	{
		return pAppPraise;
	}


	public void setpAppPraise(Integer pAppPraise)
	{
		this.pAppPraise = pAppPraise;
	}


	public Integer getpAppCuser()
	{
		return pAppCuser;
	}


	public void setpAppCuser(Integer pAppCuser)
	{
		this.pAppCuser = pAppCuser;
	}


	public Integer getpAppUuper()
	{
		return pAppUuper;
	}


	public void setpAppUuper(Integer pAppUuper)
	{
		this.pAppUuper = pAppUuper;
	}


	public Date getpAppCdate()
	{
		return pAppCdate;
	}


	public void setpAppCdate(Date pAppCdate)
	{
		this.pAppCdate = pAppCdate;
	}


	public Date getpAppUdate()
	{
		return pAppUdate;
	}


	public void setpAppUdate(Date pAppUdate)
	{
		this.pAppUdate = pAppUdate;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tpAppId(id):").append(pAppId);
		buffer.append("\tpAppActionname(上传路径):").append(pAppActionname);
		buffer.append("\tpAppPackagename(包路径):").append(pAppPackagename);
		buffer.append("\tpAppPluginname(插件名称):").append(pAppPluginname);
		buffer.append("\n");
		buffer.append("\tpAppDisplaysort(排序):").append(pAppDisplaysort);
		buffer.append("\tpAppPlugintype(类型):").append(pAppPlugintype);
		buffer.append("\tpAppOpen(状态):").append(pAppOpen);
		buffer.append("\tpAppPraise(评价):").append(pAppPraise);
		buffer.append("\n");
		buffer.append("\tpAppCuser(创建者):").append(pAppCuser);
		buffer.append("\tpAppUuper(更新者):").append(pAppUuper);
		buffer.append("\tpAppCdate(创建时间):").append(pAppCdate);
		buffer.append("\tpAppUdate(更新时间):").append(pAppUdate);
		buffer.append("\tpAppAuditstate(状态):").append(pAppAuditstate);
		buffer.append("\tpAppRemark(说明):").append(pAppRemark);
		return buffer.toString();
	}
	//================== begin ======================


	public String getpAppdetailVersionname() {
		return pAppdetailVersionname;
	}


	public void setpAppdetailVersionname(String pAppdetailVersionname) {
		this.pAppdetailVersionname = pAppdetailVersionname;
	}


	public String getpAppdetailDesc() {
		return pAppdetailDesc;
	}


	public void setpAppdetailDesc(String pAppdetailDesc) {
		this.pAppdetailDesc = pAppdetailDesc;
	}


	public String getpAppdetailAdmindesc() {
		return pAppdetailAdmindesc;
	}


	public void setpAppdetailAdmindesc(String pAppdetailAdmindesc) {
		this.pAppdetailAdmindesc = pAppdetailAdmindesc;
	}


	public String getpAppdetailAdminame() {
		return pAppdetailAdminame;
	}


	public void setpAppdetailAdminame(String pAppdetailAdminame) {
		this.pAppdetailAdminame = pAppdetailAdminame;
	}


	public Date getpAppdetailDate() {
		return pAppdetailDate;
	}


	public void setpAppdetailDate(Date pAppdetailDate) {
		this.pAppdetailDate = pAppdetailDate;
	}


	public String getpAppdetailId() {
		return pAppdetailId;
	}


	public void setpAppdetailId(String pAppdetailId) {
		this.pAppdetailId = pAppdetailId;
	}


	public String getpAppdetailVersion() {
		return pAppdetailVersion;
	}


	public void setpAppdetailVersion(String pAppdetailVersion) {
		this.pAppdetailVersion = pAppdetailVersion;
	}


	public String getpAppdetailAuditstate() {
		return pAppdetailAuditstate;
	}


	public void setpAppdetailAuditstate(String pAppdetailAuditstate) {
		this.pAppdetailAuditstate = pAppdetailAuditstate;
	}


	public String getpAppAuditstate()
	{
		return pAppAuditstate;
	}


	public void setpAppAuditstate(String pAppAuditstate)
	{
		this.pAppAuditstate = pAppAuditstate;
	}


	public String getpAppRemark()
	{
		return pAppRemark;
	}


	public void setpAppRemark(String pAppRemark)
	{
		this.pAppRemark = pAppRemark;
	}


	public String getpAppBigType() {
		return pAppBigType;
	}

	public void setpAppBigType(String pAppBigType) {
		this.pAppBigType = pAppBigType;
	}

	//================== end ======================
}
