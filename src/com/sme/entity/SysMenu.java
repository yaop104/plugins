package com.sme.entity;

import java.util.Date;

import com.sme.core.model.BaseObject;

/**
 * 菜单(SysMenu)模型对象
 */
public class SysMenu extends BaseObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -283878602545252664L;

	//======================字段列表========================
	/** id */
	private Integer	sysMenuId;

	/** 名称 */
	private String	sysMenuName;

	/** 父id */
	private Integer	sysMenuPid;
	
	private transient String sysMenuPname;

	/** 状态 */
	private String	sysMenuState;

	/** 排序 */
	private Integer	sysMenuOrder;

	/** 描述 */
	private String	sysMenuDesc;

	/** 类型 */
	private String	sysMenuType;

	/** url地址 */
	private String	sysMenuUrl;

	/** 记录日期 */
	private Date	sysMenuCdate;

	/** 更新日期 */
	private Date	sysMenuUdate;

	/** 记录人 */
	private String	sysMenuCuser;

	/** 创建人 */
	private String	sysMenuUueser;

	public Integer getSysMenuId() {
		return this.sysMenuId;
	}

	public void setSysMenuId(Integer sysMenuId) {
		this.sysMenuId = sysMenuId;
	}

	public String getSysMenuName() {
		return this.sysMenuName;
	}

	public void setSysMenuName(String sysMenuName) {
		this.sysMenuName = sysMenuName;
	}

	public Integer getSysMenuPid() {
		return this.sysMenuPid;
	}

	public void setSysMenuPid(Integer sysMenuPid) {
		this.sysMenuPid = sysMenuPid;
	}

	public String getSysMenuState() {
		return this.sysMenuState;
	}

	public void setSysMenuState(String sysMenuState) {
		this.sysMenuState = sysMenuState;
	}

	public Integer getSysMenuOrder() {
		return this.sysMenuOrder;
	}

	public void setSysMenuOrder(Integer sysMenuOrder) {
		this.sysMenuOrder = sysMenuOrder;
	}

	public String getSysMenuDesc() {
		return this.sysMenuDesc;
	}

	public void setSysMenuDesc(String sysMenuDesc) {
		this.sysMenuDesc = sysMenuDesc;
	}

	public String getSysMenuType() {
		return this.sysMenuType;
	}

	public void setSysMenuType(String sysMenuType) {
		this.sysMenuType = sysMenuType;
	}

	public String getSysMenuUrl() {
		return this.sysMenuUrl;
	}

	public void setSysMenuUrl(String sysMenuUrl) {
		this.sysMenuUrl = sysMenuUrl;
	}

	public Date getSysMenuCdate() {
		return this.sysMenuCdate;
	}

	public void setSysMenuCdate(Date sysMenuCdate) {
		this.sysMenuCdate = sysMenuCdate;
	}

	public Date getSysMenuUdate() {
		return this.sysMenuUdate;
	}

	public void setSysMenuUdate(Date sysMenuUdate) {
		this.sysMenuUdate = sysMenuUdate;
	}

	public String getSysMenuCuser() {
		return this.sysMenuCuser;
	}

	public void setSysMenuCuser(String sysMenuCuser) {
		this.sysMenuCuser = sysMenuCuser;
	}

	public String getSysMenuUueser() {
		return this.sysMenuUueser;
	}

	public void setSysMenuUueser(String sysMenuUueser) {
		this.sysMenuUueser = sysMenuUueser;
	}

	public String getSysMenuPname() {
		return sysMenuPname;
	}

	public void setSysMenuPname(String sysMenuPname) {
		this.sysMenuPname = sysMenuPname;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tsysMenuId(id):").append(sysMenuId);
		buffer.append("\tsysMenuName(名称):").append(sysMenuName);
		buffer.append("\tsysMenuPid(父id):").append(sysMenuPid);
		buffer.append("\tsysMenuState(状态):").append(sysMenuState);
		buffer.append("\n");
		buffer.append("\tsysMenuOrder(排序):").append(sysMenuOrder);
		buffer.append("\tsysMenuDesc(描述):").append(sysMenuDesc);
		buffer.append("\tsysMenuType(类型):").append(sysMenuType);
		buffer.append("\tsysMenuUrl(url地址):").append(sysMenuUrl);
		buffer.append("\n");
		buffer.append("\tsysMenuCdate(记录日期):").append(sysMenuCdate);
		buffer.append("\tsysMenuUdate(更新日期):").append(sysMenuUdate);
		buffer.append("\tsysMenuCuser(记录人):").append(sysMenuCuser);
		buffer.append("\tsysMenuUueser(创建人):").append(sysMenuUueser);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
