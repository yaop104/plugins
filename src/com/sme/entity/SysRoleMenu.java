package com.sme.entity;


import com.sme.core.model.BaseObject;

/**
 * 角色菜单(SysRoleMenu)模型对象
 */
public class SysRoleMenu extends BaseObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7812236233462915280L;

	//======================字段列表========================
	/** id */
	private Integer	sysRmId;

	/** 角色ID */
	private Integer	sysRoleId;

	/** 菜单ID */
	private Integer	sysMenuId;

	public Integer getSysRmId() {
		return this.sysRmId;
	}

	public void setSysRmId(Integer sysRmId) {
		this.sysRmId = sysRmId;
	}

	public Integer getSysRoleId() {
		return this.sysRoleId;
	}

	public void setSysRoleId(Integer sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public Integer getSysMenuId() {
		return this.sysMenuId;
	}

	public void setSysMenuId(Integer sysMenuId) {
		this.sysMenuId = sysMenuId;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tsysRmId(id):").append(sysRmId);
		buffer.append("\tsysRoleId(角色ID):").append(sysRoleId);
		buffer.append("\tsysMenuId(菜单ID):").append(sysMenuId);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
