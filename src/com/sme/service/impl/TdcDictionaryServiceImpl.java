package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TdcDictionaryDao;
import com.sme.entity.TdcDictionary;
import com.sme.service.TdcDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdcDictionaryServiceImpl extends BaseService<TdcDictionary> implements TdcDictionaryService{
	@Autowired
	private TdcDictionaryDao tdcDictionaryDao;

	@Override
	public BaseDao<TdcDictionary> getDao() {
		return tdcDictionaryDao;
	}
	public TdcDictionaryDao getTdcDictionaryDao()
	{
		return tdcDictionaryDao;
	}
	public void setTdcDictionaryDao(TdcDictionaryDao tdcDictionaryDao)
	{
		this.tdcDictionaryDao = tdcDictionaryDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
