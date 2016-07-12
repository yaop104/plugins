package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TscSource;
import org.springframework.stereotype.Repository;

@Mapper("tscSourceDao")
@Repository
public interface TscSourceDao extends BaseDao<TscSource> {
	
	//================== begin ======================

	//================== end ======================
}
