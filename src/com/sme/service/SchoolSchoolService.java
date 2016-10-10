package com.sme.service;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.SchoolSchool;
import com.sme.entity.SysAcc;

import java.util.List;

public interface SchoolSchoolService  extends InterfaceBaseService<SchoolSchool> {
    //================== begin ======================
    List<SchoolSchool> selectByUsers(SysAcc sysAcc);

	//================== end ======================
}
