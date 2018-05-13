package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.SysOrgDao;
import com.sme.entity.SysOrg;
import com.sme.service.SysOrgService;
@Service
public class SysOrgServiceImpl extends BaseService<SysOrg> implements SysOrgService{
	@Autowired
	private SysOrgDao sysOrgDao;

	@Override
	public BaseDao<SysOrg> getDao() {
		return sysOrgDao;
	}
	public SysOrgDao getSysOrgDao()
	{
		return sysOrgDao;
	}
	public void setSysOrgDao(SysOrgDao sysOrgDao)
	{
		this.sysOrgDao = sysOrgDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
