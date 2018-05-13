package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TptPosition;
import org.springframework.stereotype.Repository;

@Mapper("tptPositionDao")
@Repository
public interface TptPositionDao extends BaseDao<TptPosition> {
	
	//================== begin ======================

	//================== end ======================
}
