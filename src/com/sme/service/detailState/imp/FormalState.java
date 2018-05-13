package com.sme.service.detailState.imp;

import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.detailState.Istate;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;
import com.sme.util.SpringContextUtil;


public class FormalState implements Istate {
	
//	IDetailDao dDao = DaoFactory.getDetail();
//	
//	IMessageService service = MessageServiceFactory.getService();
	@Override
	public JSONObject testPublish(PAppDetail app) {

		return JSONUtil.checkResult(501, "只能测试发布待审核插件！");

	}
	
	@Override
	public JSONObject formalPublis(PAppDetail app) {
		
		return JSONUtil.checkResult(500, "该插件已经正式上线！");

		
	}
	
	@Override
	public JSONObject noPass(PAppDetail app) {
		return JSONUtil.checkResult(501, "数据异常操作失败！");
	}
	
	@Override
	public JSONObject stop(PAppDetail app) {
		
		try {
			PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
			pAppDetailDao.updateState(app);
//			LogProxy.log(new ApplicationLogBuilder(app, "启用插件|", null));
			return JSONUtil.checkResult(500,"停用成功！");
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
//			log.error("formalState stop appDetail throw e",e);
			return JSONUtil.checkResult(501, "停用失败！");

		}
	}
	
	@Override
	public JSONObject start(PAppDetail app) {
		
		return JSONUtil.checkResult(501, "该插件已经是启用状态！");

	}
	
	@Override
	public JSONObject del(PAppDetail app) {
		return JSONUtil.checkResult(503, "该插件已经上线,不能删除！");
	}
	
	
}
