package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.PTerminalInfoDao;
import com.sme.entity.PTerminalInfo;
import com.sme.service.PTerminalInfoService;
@Service
public class PTerminalInfoServiceImpl extends BaseService<PTerminalInfo> implements PTerminalInfoService{
	@Autowired
	private PTerminalInfoDao pTerminalInfoDao;

	@Override
	public BaseDao<PTerminalInfo> getDao() {
		return pTerminalInfoDao;
	}
	public PTerminalInfoDao getPTerminalInfoDao()
	{
		return pTerminalInfoDao;
	}
	public void setPTerminalInfoDao(PTerminalInfoDao pTerminalInfoDao)
	{
		this.pTerminalInfoDao = pTerminalInfoDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
