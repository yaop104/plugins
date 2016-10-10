package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.SchoolSchoolDao;
import com.sme.entity.SchoolSchool;
import com.sme.service.SchoolSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolSchoolServiceImpl extends BaseService<SchoolSchool> implements SchoolSchoolService {
	@Autowired
	private SchoolSchoolDao schoolSchoolDao;

	@Override
	public BaseDao<SchoolSchool> getDao() {
		return schoolSchoolDao;
	}
	public SchoolSchoolDao getSchoolSchoolDao()
	{
		return schoolSchoolDao;
	}
	public void setSchoolSchoolDao(SchoolSchoolDao schoolSchoolDao)
	{
		this.schoolSchoolDao = schoolSchoolDao;
	}
	
	//================== begin ======================

	//================== end ======================
}
