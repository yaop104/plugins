package com.sme.service;

import java.util.List;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.OperateLog;
import com.sme.entity.SysMenu;

public interface OperateLogService extends InterfaceBaseService<OperateLog> {
	
	void batchDelete(String ids);
	
	List<SysMenu> getOperateMenus();
	
	void log(OperateLog log);

}
