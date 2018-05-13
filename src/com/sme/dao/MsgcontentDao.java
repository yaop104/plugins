package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.Msgcontent;

@Mapper("msgcontent")
@Repository
public interface MsgcontentDao extends BaseDao<Msgcontent>{
	
}
