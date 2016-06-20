package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.PAppPatch;
@Mapper("pAppPatchDao")
@Repository
public interface PAppPatchDao extends BaseDao<PAppPatch> {
	
	//================== begin ======================

	public void deletePatch(int id);
	//================== end ======================
}
