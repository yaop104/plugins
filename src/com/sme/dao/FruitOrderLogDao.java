package com.sme.dao;

import com.sme.core.dao.BaseDao;
import com.sme.entity.FruitOrderLog;
import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

@Mapper("fruitOrderLogDao")
@Repository
public interface FruitOrderLogDao extends BaseDao<FruitOrderLog> {
	
	//================== begin ======================

	//================== end ======================
}
