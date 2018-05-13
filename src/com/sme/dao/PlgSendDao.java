package com.sme.dao;

import java.util.List;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.PlgSend;
@Mapper("plgSendDao")
@Repository
public interface PlgSendDao extends BaseDao<PlgSend> {

	//================== begin ======================
	void updateForSend(PlgSend plgSend);
	List<PlgSend> selectForSend(PlgSend pSend);
	//================== end ======================

	
}
