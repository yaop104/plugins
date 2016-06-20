package com.sme.service.impl;

import java.util.List;
import java.util.Map;

import com.sme.util.ApkinformatonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.PAppDetailDao;
import com.sme.dao.PAppPatchDao;
import com.sme.dao.PApplicationDao;
import com.sme.entity.PAppDetail;
import com.sme.service.AuditApp;
import com.sme.service.PAppDetailService;
import com.sme.service.StrategyFactory;
import com.sme.service.interfaces.IPluginSaveStrategy;
import com.sme.util.JSONObject;
import com.sme.util.RespMessage;
@Service("pAppDetailService")
public class PAppDetailServiceImpl extends BaseService<PAppDetail> implements PAppDetailService{
	@Autowired
	private PAppDetailDao pAppDetailDao;
	
	@Autowired
	private PApplicationDao pApplicationDao;
	
	@Autowired
	private PAppPatchDao pAppPatchDao;

	@Override
	public BaseDao<PAppDetail> getDao() {
		return pAppDetailDao;
	}
	public PAppDetailDao getPAppDetailDao()
	{
		return pAppDetailDao;
	}
	public void setPAppDetailDao(PAppDetailDao pAppDetailDao)
	{
		this.pAppDetailDao = pAppDetailDao;
	}
	@Override
	public List<PAppDetail> pageForGetCheckAppList(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return pAppDetailDao.getCheckAppList(parm);
	}
	@Override
	public Integer countForGetCheckAppList(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return pAppDetailDao.getCheckAppSize(parm);
	}
	@Override
	public JSONObject addHtmlDetail(PAppDetail pAppDetail)
	{
		IPluginSaveStrategy saver = StrategyFactory.getStrategyByDetail(pAppDetail);
		
		
//		cor.setAdminid(super.getAdminId());
		
		JSONObject result =	saver.save(pAppDetail);
		
		return result;
	}
	@Override
	public Object auditApp(PAppDetail t) {
		try {
			String state = t.getpAppdetailAuditstate();
			AuditApp audit = null;
			if ("2".equals(state)) {
				audit = new AppNoPassAudit(pAppDetailDao);
			} else if ("3".equals(state)) {
				audit = new AppFormalAudit(pAppDetailDao, pApplicationDao);
			} else if ("6".equals(state)) {
				audit = new AppTestAudit(pAppDetailDao, pAppPatchDao);
			}
			return new Context(audit, t).execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//================== begin ======================

	class Context {
		private AuditApp audit;
		private PAppDetail detail;
		
		public Context(AuditApp audit, PAppDetail detail) {
			this.audit = audit;
			this.detail = detail;
		}
		
		public Object execute() {
			RespMessage msg = new RespMessage();
			if (audit != null && detail != null)
				return audit.audit(detail);
			msg.setCode("500");
			return msg;
		}
	}

    //================== end ======================
}
