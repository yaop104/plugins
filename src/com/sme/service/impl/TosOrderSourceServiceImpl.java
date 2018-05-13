package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TosOrderSourceDao;
import com.sme.entity.TosOrderSource;
import com.sme.service.TosOrderSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TosOrderSourceServiceImpl extends BaseService<TosOrderSource> implements TosOrderSourceService{
	@Autowired
	private TosOrderSourceDao tosOrderSourceDao;

	@Override
	public BaseDao<TosOrderSource> getDao() {
		return tosOrderSourceDao;
	}
	public TosOrderSourceDao getTosOrderSourceDao()
	{
		return tosOrderSourceDao;
	}
	public void setTosOrderSourceDao(TosOrderSourceDao tosOrderSourceDao)
	{
		this.tosOrderSourceDao = tosOrderSourceDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
