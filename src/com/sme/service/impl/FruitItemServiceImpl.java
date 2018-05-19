package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.FruitItemDao;
import com.sme.entity.FruitItem;
import com.sme.service.FruitItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitItemServiceImpl extends BaseService<FruitItem> implements FruitItemService {
	@Autowired
	private FruitItemDao fruitItemDao;

	@Override
	public BaseDao<FruitItem> getDao() {
		return fruitItemDao;
	}
	public FruitItemDao getFruitItemDao()
	{
		return fruitItemDao;
	}
	public void setFruitItemDao(FruitItemDao fruitItemDao)
	{
		this.fruitItemDao = fruitItemDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
