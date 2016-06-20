package com.sme.service;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.LoginLog;

public interface LoginLogService extends InterfaceBaseService<LoginLog>{

	void batchDelete(String ids);
}
