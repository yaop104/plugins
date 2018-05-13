package com.sme.service;

import java.util.List;

import com.sme.entity.SysRoleMenu;

public interface SysRoleMenuService  {
	//================== begin ======================

	List<String> selectMenuIds(SysRoleMenu sysRoleMenu);
	//================== end ======================
}
