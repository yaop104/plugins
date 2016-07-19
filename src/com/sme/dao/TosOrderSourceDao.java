package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TosOrderSource;
import org.springframework.stereotype.Repository;

@Mapper("tosOrderSourceDao")
@Repository
public interface TosOrderSourceDao extends BaseDao<TosOrderSource> {
	
	//================== begin ======================

	//================== end ======================
}
