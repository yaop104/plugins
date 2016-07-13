package com.sme.dao;

import java.util.List;
import java.util.Map;

import com.sme.entity.PApplication;
import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.core.dao.BaseDao;
import com.sme.entity.PAppDetail;
@Mapper("pAppDetailDao")
@Repository
public interface PAppDetailDao extends BaseDao<PAppDetail> {
	
	//================== begin ======================

	public List<PAppDetail> selectLowerVersionApk(PAppDetail detail);
	
	public void offLineApp(PAppDetail detail);
	
	public Integer getCheckAppSize(Map<String, Object> map);
	
	public List<PAppDetail> getCheckAppList(Map<String, Object> map);
	
	public void updateState(PAppDetail detail);
	
	public Integer canTestPublish(PAppDetail detail);

	public PAppDetail selectOneByActionId(Integer getpAppdetailApkactionid);

	public int getPostMinVersion(Integer getpAppdetailApkactionid,
			Integer getpAppdetailId);

	public int getPreMaxVersion(Integer getpAppdetailApkactionid, Integer getpAppdetailId);

    PAppDetail selectLatestVersion(String packageName);

    String selectLatestApkName(String packageName);

	List<PAppDetail> selectDetail(PAppDetail pAppDetail);

	List<PApplication> selectHot(PApplication pApplication);

	//================== end ======================
}
