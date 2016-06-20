package com.sme.service.interfaces;

import com.sme.entity.PAppDetail;
import com.sme.util.JSONObject;


public interface IPluginSaveStrategy {
	
	public JSONObject save(PAppDetail detail);
		
}
