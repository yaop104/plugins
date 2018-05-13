package com.ncscyber.bigdt.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sme.entity.SysRole;
import com.sme.service.impl.SysRoleServiceImpl;

public class SysRoleServiceTest
{
	private ClassPathXmlApplicationContext context;
	private SysRoleServiceImpl service;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("spring_config/applicationContext.xml");
		service = (SysRoleServiceImpl)context.getBean("sysRoleServiceImpl");
	}
	
	@Test
	public void select(){
		List<SysRole> lists = service.select(new SysRole());
		System.out.println(lists.size());
		for(SysRole a : lists){
			System.out.println(a.getRolename());
		}
	}

	@Test
	public void insert(){
		SysRole sysRole = new SysRole();
		
		sysRole.setRolecdate(new Date());
		sysRole.setRolecuser("1111");
		sysRole.setRoledesc("111111111111");
		sysRole.setRolename("11111111111111");
		sysRole.setRoleorder(1);
		sysRole.setRolestate("1");
		sysRole.setRoletype("1");
		
		service.insert(sysRole);
	}
	
	@Test
	public void update(){
		SysRole sysRole = new SysRole();
		sysRole.setRoleid(1);
		sysRole.setRoledesc("111111111111");
		sysRole.setRolename("11111111111111");
		sysRole.setRoleorder(1);
		sysRole.setRolestate("1");
		sysRole.setRoletype("1");	
		sysRole.setRoleuuser("2");
		sysRole.setRoleudate(new Date());
		
		service.update(sysRole);
	}
	
	
	@Test
	public void delete(){
		SysRole sysRole = new SysRole();
		sysRole.setRoleid(1);
		service.delete(sysRole);
	}
	
	@Test
	public void getById(){
		SysRole sysRole = new SysRole();
		sysRole.setRoleid(2);
		sysRole = service.getById(sysRole);
		System.out.println(sysRole.getRolename());
	}
	
	
	
	
	
	
}
