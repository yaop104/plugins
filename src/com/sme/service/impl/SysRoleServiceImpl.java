package com.sme.service.impl;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.SysRoleDao;
import com.sme.entity.SysRole;
import com.sme.service.SysRoleService;
@Service
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService 
{
	@Autowired
	private SysRoleDao sysRoleDao;
	@Override
	public BaseDao<SysRole> getDao()
	{
		return sysRoleDao;
	}
	public SysRoleDao getSysRoleDao()
	{
		return sysRoleDao;
	}
	public void setSysRoleDao(SysRoleDao sysRoleDao)
	{
		this.sysRoleDao = sysRoleDao;
	}
	@Override
	public void delete(SysRole t) {
		try {
			sysRoleDao.deleteById(t.getRoleid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
