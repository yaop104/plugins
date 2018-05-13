package com.ncscyber.bigdt.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sme.entity.SysAcc;
import com.sme.service.impl.SysAccServiceImpl;

public class SysAccServiceTest
{
	private ClassPathXmlApplicationContext context;
	private SysAccServiceImpl service;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("spring_config/applicationContext.xml");
		service = (SysAccServiceImpl)context.getBean("sysAccServiceImpl");
	}
	
	@Test
	public void select(){
		List<SysAcc> lists = service.select(new SysAcc());
		System.out.println(lists.size());
		for(SysAcc a : lists){
			System.out.println(a.getSysAccName());
		}
	}

	@Test
	public void insert(){
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccCdate(new Date());
		sysAcc.setSysAccCuser("1111111");
		sysAcc.setSysAccDesc("123123");
		sysAcc.setSysAccName("123");
		
		service.insert(sysAcc);
	}
	
	@Test
	public void update(){
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccId(2);
		sysAcc.setSysAccUdate(new Date());
		sysAcc.setSysAccUueser("22222222222");
		sysAcc.setSysAccDesc("123123");
		sysAcc.setSysAccName("123");
		
		service.update(sysAcc);
	}
	
	
	@Test
	public void delete(){
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccId(2);
		service.delete(sysAcc);
	}
	
	@Test
	public void getById(){
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccId(1);
		sysAcc = service.getById(sysAcc);
		System.out.println(sysAcc.getSysAccName());
	}
	
	
	
	@Test
	public void getByAccPwd(){
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccName("admin");
		sysAcc.setSysAccPassword("1");
		List<SysAcc> lists = service.select(sysAcc);
		System.out.println(lists.size());
	}
	
	
}
