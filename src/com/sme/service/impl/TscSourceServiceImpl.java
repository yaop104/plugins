package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TscSourceDao;
import com.sme.entity.TscSource;
import com.sme.service.TscSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TscSourceServiceImpl extends BaseService<TscSource> implements TscSourceService{
	@Autowired
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
