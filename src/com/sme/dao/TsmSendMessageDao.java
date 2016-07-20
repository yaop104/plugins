package com.sme.dao;


import com.sme.core.dao.BaseDao;
import com.sme.entity.TsmSendMessage;
import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;
@Mapper("tsmSendMessageDao")
@Repository
public interface TsmSendMessageDao extends BaseDao<TsmSendMessage> {

	
	//================== begin ======================
	TsmSendMessage selectLastCode(TsmSendMessage tsmSendMessage);

	//================== end ======================
}
