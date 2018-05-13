package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TbcInfoDao;
import com.sme.entity.TbcInfo;
import com.sme.service.TbcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbcInfoServiceImpl extends BaseService<TbcInfo> implements TbcInfoService{
	@Autowired
	private TbcInfoDao tbcInfoDao;

	@Override
	public BaseDao<TbcInfo> getDao() {
		return tbcInfoDao;
	}
	public TbcInfoDao getTbcInfoDao()
	{
		return tbcInfoDao;
	}
	public void setTbcInfoDao(TbcInfoDao tbcInfoDao)
	{
		this.tbcInfoDao = tbcInfoDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
