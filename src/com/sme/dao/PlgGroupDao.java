package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.PlgGroup;
@Mapper("plgGroupDao")
@Repository
public interface PlgGroupDao extends BaseDao<PlgGroup> {
	
	//================== begin ======================

	//================== end ======================
}
