package com.sme.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.SysMenuDao;
import com.sme.entity.SysMenu;
import com.sme.entity.TreeNode;
import com.sme.service.SysMenuService;
@Service
public class SysMenuServiceImpl extends BaseService<SysMenu> implements
		SysMenuService
{
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public BaseDao<SysMenu> getDao()
	{
		return sysMenuDao;
	}

	public SysMenuDao getSysMenuDao()
	{
		return sysMenuDao;
	}

	public void setSysMenuDao(SysMenuDao sysMenuDao)
	{
		this.sysMenuDao = sysMenuDao;
	}

	@SuppressWarnings("rawtypes")
	public List<TreeNode> menuTree(List<String> sysMenuIds)
	{
		// TODO Auto-generated method stub

		List<TreeNode> nodes = new ArrayList<TreeNode>();

		List<SysMenu> list = sysMenuDao.select(null);
		List<String> codes = new ArrayList<String>();
		for (SysMenu t : list)
		{
			codes.add(String.valueOf(t.getSysMenuId()));
		}

		// 定义变量
		Map<String, TreeNode> map = new LinkedHashMap<String, TreeNode>();
		TreeNode parentNode = null;
		SysMenu sysMenu2 = null;
		TreeNode treeNode = null;
		for (int i = 0; i < list.size(); i++)
		{

			sysMenu2 = list.get(i);

			if(sysMenuIds.contains(String.valueOf(sysMenu2.getSysMenuId()))){
				treeNode = new TreeNode(String.valueOf(sysMenu2.getSysMenuId()),
						sysMenu2.getSysMenuId(), sysMenu2.getSysMenuName(),
						sysMenu2.getSysMenuName(), null, "open", true, null);
			}else{
				treeNode = new TreeNode(String.valueOf(sysMenu2.getSysMenuId()),
						sysMenu2.getSysMenuId(), sysMenu2.getSysMenuName(),
						sysMenu2.getSysMenuName(), null, "open", false, null);
			}
			

			if (String.valueOf(sysMenu2.getSysMenuPid()) != null && !"-1".equals(String.valueOf(sysMenu2.getSysMenuPid())))
			{ 

				parentNode = map.get(String.valueOf(sysMenu2.getSysMenuPid())); 
				parentNode.appendChild(treeNode);
				parentNode.setState("open"); 
			}
			map.put(String.valueOf(sysMenu2.getSysMenuId()), treeNode); 
		}

		Set<String> keySet = map.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();)
		{
			String code = (String) iterator.next();
//			if (code.length() == 1)
//			{
				//
				if (map.get(code).getChildren().size() != 0)
				{
					nodes.add(map.get(code));
				}
				else
				{
					//
					if (codes.contains(code))
					{
						nodes.add(map.get(code));
					}
				}
//			}
		}
		return nodes;
	}

}
