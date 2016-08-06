package com.sme.service.detailState.imp;

import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.detailState.Istate;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;
import com.sme.util.SpringContextUtil;


public class NoPassState implements Istate {
	
//	IDetailDao dDao = DaoFactory.getDetail();
	
	@Override
	public JSONObject testPublish(PAppDetail app) {
		
		return JSONUtil.checkResult(501, "只能测试发布待审核插件！");
		
	}
	
	@Override
	public JSONObject formalPublis(PAppDetail app) {

		return JSONUtil.checkResult(501,"请先测试发布！");
		
	}
	
	@Override
	public JSONObject noPass(PAppDetail app) {
		
		return JSONUtil.checkResult(501,"数据异常操作失败！");
		
	}
	
	@Override
	public JSONObject stop(PAppDetail app) {

		return JSONUtil.checkResult(501, "请勿非法操作！");

	}
	
	@Override
	public JSONObject start(PAppDetail app) {

		return JSONUtil.checkResult(501, "请勿非法操作！");

		
	}
	
	@Override
	public JSONObject del(PAppDetail detail) {
		try {
			PAppDetailDao pAppDetailDao = (PAppDetailDao) SpringContextUtil.getBean("pAppDetailDao");
			pAppDetailDao.delete(detail);
//			dDao.deleteAppDetail(detail);
//			
//			LogProxy.log(new ApplicationLogBuilder(detail, "删除插件|", null));
			return JSONUtil.checkResult(500,"删除成功");
			
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			return JSONUtil.checkResult(501, "删除插件出错！");
			
		}
	}
	

}
