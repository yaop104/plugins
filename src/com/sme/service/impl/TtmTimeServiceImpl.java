package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TtmTimeDao;
import com.sme.entity.TtmTime;
import com.sme.service.TtmTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TtmTimeServiceImpl extends BaseService<TtmTime> implements TtmTimeService{
	@Autowired
	private TtmTimeDao ttmTimeDao;

	@Override
	public BaseDao<TtmTime> getDao() {
		return ttmTimeDao;
	}
	public TtmTimeDao getTtmTimeDao()
	{
		return ttmTimeDao;
	}
	public void setTtmTimeDao(TtmTimeDao ttmTimeDao)
	{
		this.ttmTimeDao = ttmTimeDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
