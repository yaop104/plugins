package com.sme.service;

import java.util.List;
import java.util.Map;
import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.PAppDetail;
import com.sme.entity.PApplication;

public interface PApplicationService extends InterfaceBaseService<PApplication> {
	
	/**
	 * 应用下线
	 * @param appId 应用id
	 */
	public void offLine(Integer appId);

	public int countForList(Map<String, Object> parm);

	public List<PApplication> pageForList(Map<String, Object> parm);
	
	public List<PApplication> getOrderApp();
	
	public void saveOrder(String ids);

    PAppDetail hasNewVersion();

    String getLatestApkName(String packageName);

	List<PApplication> selectHot(PApplication pApplication);
}
