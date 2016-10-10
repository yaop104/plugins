package com.sme.dao;

import com.sme.core.dao.BaseDao;
import com.sme.entity.SchoolSchool;
import com.sme.entity.SysAcc;
import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper("schoolSchoolDao")
@Repository
public interface SchoolSchoolDao extends BaseDao<SchoolSchool> {
    //================== begin ======================

    List<SchoolSchool> selectByUsers(SysAcc sysAcc);

	//================== end ======================
}
