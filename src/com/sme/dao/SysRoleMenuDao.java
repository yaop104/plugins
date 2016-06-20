package com.sme.dao;

import java.util.List;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.SysRoleMenu;
@Mapper("sysRoleMenuDao")
@Repository
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {

	//================== begin ======================
		long isEixts(SysRoleMenu sysRoleMenu);
		
		void deleteByMenuAndRole(SysRoleMenu sysRoleMenu);

		List<String> selectMenuIds(SysRoleMenu sysRoleMenu);
		
	//================== end ======================

		
}
