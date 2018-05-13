package com.sme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.SysRoleMenuDao;
import com.sme.entity.SysRoleMenu;
import com.sme.service.SysRoleMenuService;
@Service
public class SysRoleMenuServiceImpl extends BaseService<SysRoleMenu> implements SysRoleMenuService{
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	public BaseDao<SysRoleMenu> getDao() {
		return sysRoleMenuDao;
	}
	public SysRoleMenuDao getSysRoleMenuDao()
	{
		return sysRoleMenuDao;
	}
	public void setSysRoleMenuDao(SysRoleMenuDao sysRoleMenuDao)
	{
		this.sysRoleMenuDao = sysRoleMenuDao;
	}
	
	//================== begin ======================
	@Transactional
	public String menuTreeUpdate(String delData, String addData, String rolId)
	{
		try
		{
			String [] addstr;
			String [] delstr;
			if (!"*".equals(addData) && !"".equals(addData)){
			 	addstr = addData.split("\\*");
			 	insertByIds(rolId, addstr);
			}
			if (!"*".equals(delData) && !"".equals(delData)) {
			 	delstr = delData.split("\\*");
			 	deleteByIds(rolId, delstr);
			}
			return "0";
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return "1";
		}
		
	}
	
	private void deleteByIds(String rolId, String[] delstr)
	{
		SysRoleMenu sysRoleMenu;
		for ( String id :  delstr) {
			sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setSysMenuId(Integer.valueOf(id));
			sysRoleMenu.setSysRoleId(Integer.valueOf(rolId));
			sysRoleMenuDao.deleteByMenuAndRole(sysRoleMenu);
		}
		
	}
	
	private void insertByIds(String rolId, String[] addstr)
	{
				SysRoleMenu sysRoleMenu;
				for ( String id :  addstr) {
					sysRoleMenu = new SysRoleMenu();
					sysRoleMenu.setSysMenuId(Integer.valueOf(id));
					sysRoleMenu.setSysRoleId(Integer.valueOf(rolId));
					if (check(sysRoleMenu)) {
						sysRoleMenuDao.insert(sysRoleMenu);
					}
				}
		
	}
	
	private boolean check(SysRoleMenu sysRoleMenu){
		long count = sysRoleMenuDao.isEixts(sysRoleMenu);
		if (count > 0) {
			return false;
		} else {
			return true;
		}
	}
	public List<String> selectMenuIds(SysRoleMenu sysRoleMenu)
	{
		return sysRoleMenuDao.selectMenuIds(sysRoleMenu);
	}
	
	//================== end ======================
}
