package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.PlgUserDao;
import com.sme.entity.PlgUser;
import com.sme.service.PlgUserService;
@Service
public class PlgUserServiceImpl extends BaseService<PlgUser> implements PlgUserService{
	@Autowired
	private PlgUserDao plgUserDao;

	@Override
	public BaseDao<PlgUser> getDao() {
		return plgUserDao;
	}
	public PlgUserDao getPlgUserDao()
	{
		return plgUserDao;
	}
	public void setPlgUserDao(PlgUserDao plgUserDao)
	{
		this.plgUserDao = plgUserDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
