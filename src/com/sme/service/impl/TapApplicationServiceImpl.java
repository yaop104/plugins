package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TapApplicationDao;
import com.sme.entity.TapApplication;
import com.sme.service.TapApplicationService;
@Service
public class TapApplicationServiceImpl extends BaseService<TapApplication> implements TapApplicationService{
	@Autowired
	private TapApplicationDao tapApplicationDao;

	@Override
	public BaseDao<TapApplication> getDao() {
		return tapApplicationDao;
	}
	public TapApplicationDao getTapApplicationDao()
	{
		return tapApplicationDao;
	}
	public void setTapApplicationDao(TapApplicationDao tapApplicationDao)
	{
		this.tapApplicationDao = tapApplicationDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
