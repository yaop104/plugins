package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.PTerminalInfo;
@Mapper("pTerminalInfoDao")
@Repository
public interface PTerminalInfoDao extends BaseDao<PTerminalInfo> {
	
	//================== begin ======================
	
	//================== end ======================
}
