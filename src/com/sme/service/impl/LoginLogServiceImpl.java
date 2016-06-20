package com.sme.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.dao.LoginLogDao;
import com.sme.entity.LoginLog;
import com.sme.service.LoginLogService;
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {
	
	@Autowired
	private LoginLogDao loginLogDao;

	@Override
	public void batchDelete(String ids) {
		try {
			if (StringUtils.isNotBlank(ids)) {
				String[] idsArra = ids.split(",");
				List<Integer> list = new ArrayList<Integer>(idsArra.length);
				for (String id : idsArra) {
					list.add(Integer.valueOf(id));
				}
				
				loginLogDao.batchDelete(list);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(LoginLog t) {
		try {
			loginLogDao.insert(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(LoginLog t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(LoginLog t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LoginLog> select(LoginLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginLog getById(LoginLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoginLog> page(Map<String, Object> parm) {
		
		try {
			return loginLogDao.page(parm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer count(Map<String, Object> parm) {
		
		try {
			return loginLogDao.count(parm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
