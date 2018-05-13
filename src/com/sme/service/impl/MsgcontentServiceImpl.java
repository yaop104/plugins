package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.MsgcontentDao;
import com.sme.entity.Msgcontent;
import com.sme.service.MsgcontentService;

@Service("msgcontentService")
public class MsgcontentServiceImpl extends BaseService<Msgcontent>implements MsgcontentService {

	@Autowired
	private MsgcontentDao msgcontent;

	@Override
	public BaseDao<Msgcontent> getDao() {
		return msgcontent;
	}

	public MsgcontentDao getMsgcontent()
	{
		return msgcontent;
	}

	public void setMsgcontent(MsgcontentDao msgcontent)
	{
		this.msgcontent = msgcontent;
	}

}
