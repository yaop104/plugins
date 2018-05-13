package com.sme.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.SysMenu;
@Mapper("sysMenuDao")
@Repository
public interface SysMenuDao extends BaseDao<SysMenu> {
	
	//================== begin ======================

	/**
	 * 删除 软删除
	 * @param sysMenuId 菜单主键
	 * @throws SQLException 数据库异常
	 */
	void deleteById(int sysMenuId) throws SQLException;
	
	List<SysMenu> selectMenus() throws SQLException;
	//================== end ======================
}
