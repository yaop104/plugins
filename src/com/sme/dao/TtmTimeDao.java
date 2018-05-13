package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TtmTime;
import org.springframework.stereotype.Repository;

@Mapper("ttmTimeDao")
@Repository
public interface TtmTimeDao extends BaseDao<TtmTime> {
	
	//================== begin ======================

	//================== end ======================
}
