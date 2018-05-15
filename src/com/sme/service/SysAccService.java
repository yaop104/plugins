package com.sme.service;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.SysAcc;

public interface SysAccService extends InterfaceBaseService<SysAcc> {

	//================== begin ======================
	SysAcc getSysAccForLogin(String sysAccName, String oldpwd);

	Boolean getSysAcc(SysAcc sysAcc);

	SysAcc getSysAccByToken(SysAcc sysAcc);


	SysAcc getSysAccForLoginRepwd(String mobile, String oldpwd);

	SysAcc getSysAccForLoginByRepwd(String mobile, String oldpwd);

	//================== end ======================
}
