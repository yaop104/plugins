package com.ncscyber.bigdt.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sme.entity.SysOrg;
import com.sme.service.impl.SysOrgServiceImpl;

public class SysOrgServiceTest
{
	private ClassPathXmlApplicationContext context;
	private SysOrgServiceImpl service;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("spring_config/applicationContext.xml");
		service = (SysOrgServiceImpl)context.getBean("sysOrgServiceImpl");
	}
	
	@Test
	public void select(){
		List<SysOrg> lists = service.select(new SysOrg());
		System.out.println(lists.size());
		for(SysOrg a : lists){
			System.out.println(a.getSysOrgName());
		}
	}

	@Test
	public void insert(){
		SysOrg sysOrg = new SysOrg();
		
		sysOrg.setSysOrgCdate(new Date());
		sysOrg.setSysOrgCode("00000");
		sysOrg.setSysOrgCuser("1111");
		sysOrg.setSysOrgDesc("1231");
		sysOrg.setSysOrgName("1231");
		sysOrg.setSysOrgOrder(1);
		sysOrg.setSysOrgOutercode("1231");
		sysOrg.setSysOrgPcode("00000");
		sysOrg.setSysOrgState("1");
		sysOrg.setSysOrgType("1");
		
		service.insert(sysOrg);
	}
	
	@Test
	public void update(){
		SysOrg sysOrg = new SysOrg();
		
		sysOrg.setSysOrgId(2);
		sysOrg.setSysOrgUueser("222");
		sysOrg.setSysOrgUdate(new Date());
		sysOrg.setSysOrgCuser("1111");
		sysOrg.setSysOrgDesc("1231");
		sysOrg.setSysOrgName("1231");
		sysOrg.setSysOrgOrder(1);
		sysOrg.setSysOrgOutercode("1231");
		sysOrg.setSysOrgPcode("00000");
		sysOrg.setSysOrgState("1");
		sysOrg.setSysOrgType("1");
		
		service.update(sysOrg);
	}
	
	
	@Test
	public void delete(){
		SysOrg sysOrg = new SysOrg();
		
		sysOrg.setSysOrgId(2);	
		service.delete(sysOrg);
	}
	
	@Test
	public void getById(){
		SysOrg sysOrg = new SysOrg();
		
		sysOrg.setSysOrgId(1);	
		sysOrg = service.getById(sysOrg);
		System.out.println(sysOrg.getSysOrgName());
	}
	
	
	
	
	
	
}
