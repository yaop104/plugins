package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.PTerminalRelate;
@Mapper("pTerminalRelateDao")
@Repository
public interface PTerminalRelateDao extends BaseDao<PTerminalRelate> {
	
	//================== begin ======================

	//================== end ======================
}
