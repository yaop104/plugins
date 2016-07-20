package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TstStatement;
@Mapper("tstStatementDao")
@Repository
public interface TstStatementDao extends BaseDao<TstStatement> {
	
	//================== begin ======================

	//================== end ======================
}
