package com.sme.dao;

import com.sme.core.dao.BaseDao;
import com.sme.entity.FruitOrder;
import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

@Mapper("fruitOrderDao")
@Repository
public interface FruitOrderDao extends BaseDao<FruitOrder> {
	
	//================== begin ======================

	//================== end ======================
}
