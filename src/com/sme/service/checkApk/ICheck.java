package com.sme.service.checkApk;

import com.sme.entity.PAppDetail;
import com.sme.util.ApkinformatonUtil;
import com.sme.util.JSONObject;


public interface ICheck {
	
	public void setNext(ICheck check);
	
	public void check(JSONObject json, ApkinformatonUtil util, PAppDetail detail);
	
}
