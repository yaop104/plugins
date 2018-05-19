package com.sme.dao;

import com.sme.core.dao.BaseDao;
import com.sme.entity.FruitItem;
import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

@Mapper("fruitItemDao")
@Repository
public interface FruitItemDao extends BaseDao<FruitItem> {
	
	//================== begin ======================

	//================== end ======================
}
