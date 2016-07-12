package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TatTagApp;
import org.springframework.stereotype.Repository;

@Mapper("tatTagAppDao")
@Repository
public interface TatTagAppDao extends BaseDao<TatTagApp> {
	
	//================== begin ======================

	//================== end ======================
}
