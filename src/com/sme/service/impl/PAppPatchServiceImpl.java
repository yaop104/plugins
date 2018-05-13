package com.sme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.PAppPatchDao;
import com.sme.entity.PAppPatch;
import com.sme.service.PAppPatchService;
@Service
public class PAppPatchServiceImpl extends BaseService<PAppPatch> implements PAppPatchService{
	@Autowired
	private PAppPatchDao pAppPatchDao;

	@Override
	public BaseDao<PAppPatch> getDao() {
		return pAppPatchDao;
	}
	public PAppPatchDao getPAppPatchDao()
	{
		return pAppPatchDao;
	}
	public void setPAppPatchDao(PAppPatchDao pAppPatchDao)
	{
		this.pAppPatchDao = pAppPatchDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
