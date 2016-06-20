package com.sme.service.impl;

import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.AuditApp;
import com.sme.util.RespMessage;

public class AppNoPassAudit implements AuditApp {

	private PAppDetailDao pAppDetailDao;

	public AppNoPassAudit() {
		super();
	}

	public AppNoPassAudit(PAppDetailDao pAppDetailDao) {
		super();
		this.pAppDetailDao = pAppDetailDao;
	}

	@Override
	public Object audit(PAppDetail detail) {
		RespMessage msg = new RespMessage();
		
		try {
			pAppDetailDao.updateState(detail);
			msg.setCode("500");
			msg.setMessage("操作成功");
		} catch (Exception e) {
			msg.setCode("500");
			msg.setMessage("出现异常");
			e.printStackTrace();
		}

		return msg;
	}

}
