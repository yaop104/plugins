package com.sme.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.PApplication;
@Mapper("pApplicationDao")
@Repository
public interface PApplicationDao extends BaseDao<PApplication> {
	
	//================== begin ======================

	public int getOperateAppSize(Map<String, Object> map);
	
	public List<PApplication> getOperateAppList(Map<String, Object> map);
	
	public void updateAppDisplaySort(Integer id);
	
	public List<PApplication> selectOrderApp();
	
	public void batchUpdateAppSort(List<PApplication> list);
	//================== end ======================
}
