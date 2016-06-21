package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TscSourceDao;
import com.sme.entity.TscSource;
import com.sme.service.TscSourceService;

public class TscSourceServiceImpl extends BaseService<TscSource> implements TscSourceService{
	private TscSourceDao tscSourceDao;

	@Override
	public BaseDao<TscSource> getDao() {
		return tscSourceDao;
	}
	public TscSourceDao getTscSourceDao()
	{
		return tscSourceDao;
	}
	public void setTscSourceDao(TscSourceDao tscSourceDao)
	{
		this.tscSourceDao = tscSourceDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
