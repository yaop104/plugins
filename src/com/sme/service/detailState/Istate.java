package com.sme.service.detailState;

import com.sme.entity.PAppDetail;
import com.sme.util.JSONObject;



public interface Istate {
	
	public JSONObject testPublish(PAppDetail app);
	
	public JSONObject formalPublis(PAppDetail app);
	
	public JSONObject noPass(PAppDetail app);
	
	public JSONObject stop(PAppDetail app);
	
	public JSONObject start(PAppDetail app);
	
	public JSONObject del(PAppDetail app);
	
}
