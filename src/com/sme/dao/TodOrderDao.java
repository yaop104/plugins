package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TodOrder;
import org.springframework.stereotype.Repository;

@Mapper("todOrderDao")
@Repository
public interface TodOrderDao extends BaseDao<TodOrder> {
	
	//================== begin ======================

	//================== end ======================
}
