package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.FruitOrderDao;
import com.sme.entity.FruitOrder;
import com.sme.service.FruitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitOrderServiceImpl extends BaseService<FruitOrder> implements FruitOrderService {
	@Autowired
	private FruitOrderDao fruitOrderDao;

	@Override
	public BaseDao<FruitOrder> getDao() {
		return fruitOrderDao;
	}
	public FruitOrderDao getFruitOrderDao()
	{
		return fruitOrderDao;
	}
	public void setFruitOrderDao(FruitOrderDao fruitOrderDao)
	{
		this.fruitOrderDao = fruitOrderDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
