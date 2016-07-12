package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TapDownloadDao;
import com.sme.entity.TapDownload;
import com.sme.service.TapDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TapDownloadServiceImpl extends BaseService<TapDownload> implements TapDownloadService{
	@Autowired
	private TapDownloadDao tapDownloadDao;

	@Override
	public BaseDao<TapDownload> getDao() {
		return tapDownloadDao;
	}
	public TapDownloadDao getTapDownloadDao()
	{
		return tapDownloadDao;
	}
	public void setTapDownloadDao(TapDownloadDao tapDownloadDao)
	{
		this.tapDownloadDao = tapDownloadDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
