package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TptPositionDao;
import com.sme.entity.TptPosition;
import com.sme.service.TptPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TptPositionServiceImpl extends BaseService<TptPosition> implements TptPositionService{
	@Autowired
	private TptPositionDao tptPositionDao;

	@Override
	public BaseDao<TptPosition> getDao() {
		return tptPositionDao;
	}
	public TptPositionDao getTptPositionDao()
	{
		return tptPositionDao;
	}
	public void setTptPositionDao(TptPositionDao tptPositionDao)
	{
		this.tptPositionDao = tptPositionDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
