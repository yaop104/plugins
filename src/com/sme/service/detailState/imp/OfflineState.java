package com.sme.service.detailState.imp;


import com.sme.entity.PAppDetail;
import com.sme.service.detailState.Istate;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;


public class OfflineState implements Istate {
	
	@Override
	public JSONObject testPublish(PAppDetail app) {
		
		return JSONUtil.checkResult(501,"只能测试发布待审核插件！");
		
	}
	
	@Override
	public JSONObject formalPublis(PAppDetail app) {
		
		return JSONUtil.checkResult(501, "请先测试发布！");
		
	}
	
	@Override
	public JSONObject noPass(PAppDetail app) {
		
		return JSONUtil.checkResult(501, "数据异常操作失败！");

	}
	
	@Override
	public JSONObject stop(PAppDetail app) {

		return JSONUtil.checkResult(501, "该插件已被下线！");

	}
	
	@Override
	public JSONObject start(PAppDetail app) {
		
		return JSONUtil.checkResult(501,"该插件已被下线！");
		
	}
	
	@Override
	public JSONObject del(PAppDetail app) {
		
		return JSONUtil.checkResult(503,"该插件已经下线,不能删除！");
		
	}
	
}
