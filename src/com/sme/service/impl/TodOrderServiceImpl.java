package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TodOrderDao;
import com.sme.entity.TodOrder;
import com.sme.service.TodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodOrderServiceImpl extends BaseService<TodOrder> implements TodOrderService{
	@Autowired
	private TodOrderDao todOrderDao;

	@Override
	public BaseDao<TodOrder> getDao() {
		return todOrderDao;
	}
	public TodOrderDao getTodOrderDao()
	{
		return todOrderDao;
	}
	public void setTodOrderDao(TodOrderDao todOrderDao)
	{
		this.todOrderDao = todOrderDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
