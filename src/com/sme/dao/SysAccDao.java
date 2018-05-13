package com.sme.dao;

import java.sql.SQLException;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.SysAcc;
@Mapper("sysAccDao")
@Repository
public interface SysAccDao extends BaseDao<SysAcc> {
	
	//================== begin ======================

	/**
	 * 删除用户 软删除
	 * @param id  用户id
	 * @throws SQLException SQL执行出错
	 */
	void deleteById(int id) throws SQLException;
	//================== end ======================
}
