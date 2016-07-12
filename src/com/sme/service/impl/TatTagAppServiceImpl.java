package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TatTagAppDao;
import com.sme.entity.TatTagApp;
import com.sme.service.TatTagAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TatTagAppServiceImpl extends BaseService<TatTagApp> implements TatTagAppService{
	@Autowired
	private TatTagAppDao tatTagAppDao;

	@Override
	public BaseDao<TatTagApp> getDao() {
		return tatTagAppDao;
	}
	public TatTagAppDao getTatTagAppDao()
	{
		return tatTagAppDao;
	}
	public void setTatTagAppDao(TatTagAppDao tatTagAppDao)
	{
		this.tatTagAppDao = tatTagAppDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
