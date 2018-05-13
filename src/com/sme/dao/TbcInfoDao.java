package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TbcInfo;
import org.springframework.stereotype.Repository;

@Mapper("tbcInfoDao")
@Repository
public interface TbcInfoDao extends BaseDao<TbcInfo> {
	
	//================== begin ======================

	//================== end ======================
}
