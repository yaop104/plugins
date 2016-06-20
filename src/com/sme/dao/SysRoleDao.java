package com.sme.dao;

import java.sql.SQLException;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.SysRole;
@Mapper("sysRoleDao")
@Repository
public interface SysRoleDao extends BaseDao<SysRole>
{

	/**
	 * 删除 软删除
	 * @param id 角色id
	 * @throws SQLException 数据库异常
	 */
	void deleteById(int id) throws SQLException;
}
