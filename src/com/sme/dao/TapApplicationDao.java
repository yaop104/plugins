package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TapApplication;
@Mapper("tapApplicationDao")
@Repository
public interface TapApplicationDao extends BaseDao<TapApplication> {
	
	//================== begin ======================

	//================== end ======================
}
