package com.sme.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.dao.OperateLogDao;
import com.sme.dao.SysMenuDao;
import com.sme.entity.OperateLog;
import com.sme.entity.SysMenu;
import com.sme.service.OperateLogService;
@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogService {
	
	@Autowired
	private OperateLogDao operateLogDao;
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public void insert(OperateLog t) {
		try {
			operateLogDao.insert(t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(OperateLog t) {
		
	}

	@Override
	public void update(OperateLog t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OperateLog> select(OperateLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateLog getById(OperateLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperateLog> page(Map<String, Object> parm) {
		try {
			return operateLogDao.page(parm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer count(Map<String, Object> parm) {
		try {
			return operateLogDao.count(parm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void batchDelete(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] idsArra = ids.split(",");
			List<Integer> list = new ArrayList<Integer>(idsArra.length);
			for (String id : idsArra) {
				list.add(Integer.valueOf(id));
			}
			try {
				operateLogDao.batchDelete(list);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<SysMenu> getOperateMenus() {
		try {
			return sysMenuDao.selectMenus();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void log(OperateLog log) {
		try {
			operateLogDao.insert(log);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
