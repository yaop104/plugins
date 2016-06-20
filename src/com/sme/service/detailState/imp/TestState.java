package com.sme.service.detailState.imp;

import com.sme.entity.PAppDetail;
import com.sme.service.detailState.Istate;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;


public class TestState implements Istate {
	
//	IDetailDao dDao = DaoFactory.getDetail();
//	
//	IApplicationDao aDao = DaoFactory.getApplication();
//	
//	IMessageService service = MessageServiceFactory.getService();
	
	@Override
	public JSONObject testPublish(PAppDetail app) {
		
		return JSONUtil.checkResult(500, "插件已测试发布！");
		
	}
	
	@Override
	public JSONObject formalPublis(PAppDetail app) {
		JSONObject json = new JSONObject();
		try {
			
//			dDao.update(app);
//			
//			aDao.updateMinDisplaysort(app.getApp_apkactionid());
//			
//			service.formalPublish(app);
//			
//			// 记录日志
//			LogProxy.log(new ReviewLogBuilder(app, null, LogBeanInterface.REVIEW_PLUGIN, "正式发布发布|"));
			
			return JSONUtil.checkResult(500, "上线成功！");

		}
		catch(Exception e) {
			
			
			return JSONUtil.checkResult(501,"上线失败！");
			
		}
		
	}
	
	@Override
	public JSONObject noPass(PAppDetail detail) {
		
		try {
			
//			dDao.update(detail);
//			
//			// 审核不通过测试状态的插件
//			service.nopassTest(detail);
//			
//			LogProxy.log(new ReviewLogBuilder(detail, null, LogBeanInterface.REVIEW_PLUGIN, "审核不通过|"));
			
			return JSONUtil.checkResult(500,"审核成功！");
			
			
		}
		catch(Exception e) {
			
			return JSONUtil.checkResult(501, "审核失败！");
			
		
		}
		
	}
	
	@Override
	public JSONObject stop(PAppDetail app) {
		
		return JSONUtil.checkResult(503, "请勿非法操作！");
		
	}
	
	@Override
	public JSONObject start(PAppDetail app) {
		
		return JSONUtil.checkResult(503, "请勿非法操作！");

	}
	
	@Override
	public JSONObject del(PAppDetail app) {
		
		return JSONUtil.checkResult(503, "该插件已经测试发布,不能删除！");
		
	}

}
