package com.ncscyber.bigdt.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sme.entity.SysMenu;
import com.sme.entity.TreeNode;
import com.sme.service.impl.SysMenuServiceImpl;

public class SysMenuServiceTest
{
	private ClassPathXmlApplicationContext context;
	private SysMenuServiceImpl service;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("spring_config/applicationContext.xml");
		service = (SysMenuServiceImpl)context.getBean("sysMenuServiceImpl");
	}
	
	@Test
	public void select(){
		List<SysMenu> lists = service.select(new SysMenu());
		System.out.println(lists.size());
		for(SysMenu a : lists){
			System.out.println(a.getSysMenuName());
		}
	}

	@Test
	public void insert(){
		SysMenu sysMenu = new SysMenu();
		sysMenu.setSysMenuCdate(new Date());
		sysMenu.setSysMenuCuser("1111111");
		sysMenu.setSysMenuDesc("123123");
		sysMenu.setSysMenuName("123");
		sysMenu.setSysMenuOrder(1);
		sysMenu.setSysMenuPid(0);
		sysMenu.setSysMenuState("1");
		sysMenu.setSysMenuType("1");
		sysMenu.setSysMenuUrl("www.baidu.com");
		
		service.insert(sysMenu);
	}
	
	@Test
	public void update(){
		SysMenu sysMenu = new SysMenu();
		sysMenu.setSysMenuId(2);
		sysMenu.setSysMenuUdate(new Date());
		sysMenu.setSysMenuUueser("22222222222");
		sysMenu.setSysMenuDesc("123123");
		sysMenu.setSysMenuName("123");
		sysMenu.setSysMenuOrder(1);
		sysMenu.setSysMenuPid(0);
		sysMenu.setSysMenuState("1");
		sysMenu.setSysMenuType("1");
		sysMenu.setSysMenuUrl("www.baidu.com");
		
		service.update(sysMenu);
	}
	
	
	@Test
	public void delete(){
		SysMenu sysMenu = new SysMenu();
		sysMenu.setSysMenuId(2);
		service.delete(sysMenu);
	}
	
	@Test
	public void getById(){
		SysMenu sysMenu = new SysMenu();
		sysMenu.setSysMenuId(1);
		sysMenu = service.getById(sysMenu);
		System.out.println(sysMenu.getSysMenuName());
	}
	
	@Test
	public void getTree(){
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		
		treeNodes = service.menuTree(null);
		
		TreeNode treeNode = treeNodes.get(0);
		
		System.out.println(treeNode);
		
//		for (int i = 0; i < treeNodes.size(); i++)
//		{
//			System.out.println(treeNodes.get(i).getName());
//		}
	}
	
	
	
}
