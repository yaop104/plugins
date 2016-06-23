package com.sme.service;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.SysAcc;

public interface SysAccService extends InterfaceBaseService<SysAcc> {

	//================== begin ======================
	SysAcc getSysAccForLogin(String sysAccName, String oldpwd);

	Boolean getSysAcc(SysAcc sysAcc);
	//================== end ======================
}
