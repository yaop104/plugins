package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.PTerminalRelateDao;
import com.sme.entity.PTerminalRelate;
import com.sme.service.PTerminalRelateService;
@Service
public class PTerminalRelateServiceImpl extends BaseService<PTerminalRelate> implements PTerminalRelateService{
	@Autowired
	private PTerminalRelateDao pTerminalRelateDao;

	@Override
	public BaseDao<PTerminalRelate> getDao() {
		return pTerminalRelateDao;
	}
	public PTerminalRelateDao getPTerminalRelateDao()
	{
		return pTerminalRelateDao;
	}
	public void setPTerminalRelateDao(PTerminalRelateDao pTerminalRelateDao)
	{
		this.pTerminalRelateDao = pTerminalRelateDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
