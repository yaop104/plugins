package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.SysAccDao;
import com.sme.entity.SysAcc;
import com.sme.service.SysAccService;
import com.sme.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class SysAccServiceImpl extends BaseService<SysAcc> implements SysAccService{
	@Autowired
	private SysAccDao sysAccDao;

	@Override
	public BaseDao<SysAcc> getDao() {
		return sysAccDao;
	}
	public SysAccDao getSysAccDao()
	{
		return sysAccDao;
	}
	public void setSysAccDao(SysAccDao sysAccDao)
	{
		this.sysAccDao = sysAccDao;
	}
	
	
	@Override
	public void delete(SysAcc t) {
		try {
			sysAccDao.deleteById(t.getSysAccId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//================== begin ======================
	public SysAcc getSysAccForLogin(String account, String password)
	{
		password = MD5.encryByMD5(password);
		SysAcc sysAcc =new SysAcc();
		sysAcc.setSysAccName(account);
		sysAcc.setSysAccPassword(password);
		List<SysAcc> list = new ArrayList<SysAcc>();
		list = sysAccDao.select(sysAcc);
		if(list.size() > 0){
			sysAcc = list.get(0);
		}else{
			sysAcc = null;
		}
		return sysAcc;
	}

	/**
	 * 判断重复
	 */
	@Override
	public Boolean getSysAcc(SysAcc sysAcc) {
		List<SysAcc> list = new ArrayList<SysAcc>();
		list = sysAccDao.select(sysAcc);
		if (list.size() > 0) {
			sysAcc = list.get(0);
		} else {
			sysAcc = null;
		}
		return sysAcc == null ? false : true;
	}

	/**
	 * 判断重复
	 */
	@Override
	public SysAcc getSysAccByToken(SysAcc sysAcc) {
		List<SysAcc> list = new ArrayList<SysAcc>();
		list = sysAccDao.select(sysAcc);
		if (list.size() > 0) {
			sysAcc = list.get(0);
		} else {
			sysAcc = null;
		}
		return sysAcc;
	}

	/**
	 * 根据邮箱修改密码
	 * @param mobile
	 * @param oldpwd
	 * @return
	 */
	@Override
	public SysAcc getSysAccForLoginRepwd(String mobile, String oldpwd) {
		oldpwd = MD5.encryByMD5(oldpwd);
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccEmail(mobile);
		sysAcc.setSysAccPassword(oldpwd);
		List<SysAcc> list = new ArrayList<SysAcc>();
		list = sysAccDao.select(sysAcc);
		if (list.size() > 0) {
			sysAcc = list.get(0);
		} else {
			sysAcc = null;
		}
		return sysAcc;
	}

	/**
	 * 根据手机修改密码
	 * @param mobile
	 * @param oldpwd
	 * @return
	 */
	@Override
	public SysAcc getSysAccForLoginByRepwd(String mobile, String oldpwd) {
//		oldpwd = MD5.encryByMD5(oldpwd);
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccEmail(mobile);
//		sysAcc.setSysAccPassword(oldpwd);
		List<SysAcc> list = new ArrayList<SysAcc>();
		list = sysAccDao.select(sysAcc);
		if (list.size() > 0) {
			sysAcc = list.get(0);
		} else {
			sysAcc = null;
		}
		return sysAcc;
	}
	//================== end ======================
}
