package com.sme.service;

import java.util.List;
import java.util.Map;
import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.PAppDetail;
import com.sme.util.JSONObject;

public interface PAppDetailService extends InterfaceBaseService<PAppDetail>  {

	
	//================== begin ======================
	JSONObject addHtmlDetail(PAppDetail pAppDetail);
	//================== end ======================

	List<PAppDetail> pageForGetCheckAppList(Map<String, Object> parm);

	Integer countForGetCheckAppList(Map<String, Object> parm);
	
	public Object auditApp(PAppDetail t);

	List<PAppDetail> selectDetail(PAppDetail pAppDetail);
	//================== end ======================
}
