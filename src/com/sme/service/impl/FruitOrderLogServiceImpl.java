package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.FruitOrderLogDao;
import com.sme.entity.FruitOrderLog;
import com.sme.service.FruitOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitOrderLogServiceImpl extends BaseService<FruitOrderLog> implements FruitOrderLogService {
	@Autowired
	private FruitOrderLogDao fruitOrderLogDao;

	@Override
	public BaseDao<FruitOrderLog> getDao() {
		return fruitOrderLogDao;
	}
	public FruitOrderLogDao getFruitOrderLogDao()
	{
		return fruitOrderLogDao;
	}
	public void setFruitOrderLogDao(FruitOrderLogDao fruitOrderLogDao)
	{
		this.fruitOrderLogDao = fruitOrderLogDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
