package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TstStatementDao;
import com.sme.entity.TstStatement;
import com.sme.service.TstStatementService;
@Service
public class TstStatementServiceImpl extends BaseService<TstStatement> implements TstStatementService{
	@Autowired
	private TstStatementDao tstStatementDao;

	@Override
	public BaseDao<TstStatement> getDao() {
		return tstStatementDao;
	}
	public TstStatementDao getTstStatementDao()
	{
		return tstStatementDao;
	}
	public void setTstStatementDao(TstStatementDao tstStatementDao)
	{
		this.tstStatementDao = tstStatementDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
