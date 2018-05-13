package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.PlgUser;
@Mapper("plgUserDao")
@Repository
public interface PlgUserDao extends BaseDao<PlgUser> {
	
	//================== begin ======================

	//================== end ======================
}
