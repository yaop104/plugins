package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TdcDictionary;
import org.springframework.stereotype.Repository;

@Mapper("tdcDictionaryDao")
@Repository
public interface TdcDictionaryDao extends BaseDao<TdcDictionary> {
	
	//================== begin ======================

	//================== end ======================
}
