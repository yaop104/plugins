package com.sme.service.impl;

import com.sme.dao.PAppDetailDao;
import com.sme.dao.PApplicationDao;
import com.sme.entity.PAppDetail;
import com.sme.service.AuditApp;
import com.sme.util.RespMessage;

public class AppFormalAudit implements AuditApp {

	private PAppDetailDao pAppDetailDao;

	private PApplicationDao pApplicationDao;

	public AppFormalAudit(PAppDetailDao pAppDetailDao,
			PApplicationDao pApplicationDao) {
		super();
		this.pAppDetailDao = pAppDetailDao;
		this.pApplicationDao = pApplicationDao;
	}

	public AppFormalAudit() {
		super();
	}

	@Override
	public Object audit(PAppDetail detail) {
		RespMessage msg = new RespMessage();
		try {
			pAppDetailDao.updateState(detail);
			
			detail = pAppDetailDao.getById(detail);

			/* 把最新的上线的app放到最上面显示 */
			pApplicationDao.updateAppDisplaySort(detail.getpAppdetailApkactionid());
			
			msg.setCode("500");
			msg.setMessage("上线成功");
		} catch (Exception e) {
			msg.setCode("500");
			msg.setMessage("出现异常");
			e.printStackTrace();
		}

		return msg;
	}

}
