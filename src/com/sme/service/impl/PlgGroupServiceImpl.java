package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.PlgGroupDao;
import com.sme.entity.PlgGroup;
import com.sme.service.PlgGroupService;
@Service
public class PlgGroupServiceImpl extends BaseService<PlgGroup> implements PlgGroupService{
	@Autowired
	private PlgGroupDao plgGroupDao;

	@Override
	public BaseDao<PlgGroup> getDao() {
		return plgGroupDao;
	}
	public PlgGroupDao getPlgGroupDao()
	{
		return plgGroupDao;
	}
	public void setPlgGroupDao(PlgGroupDao plgGroupDao)
	{
		this.plgGroupDao = plgGroupDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
