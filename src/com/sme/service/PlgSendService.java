package com.sme.service;

import java.util.List;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.PlgSend;

public interface PlgSendService  extends InterfaceBaseService<PlgSend>{

	
	//================== begin ======================
		Boolean insertForType(PlgSend plgSend);
		
		void updateForSend(Integer psdUnid);
		
		List<PlgSend> selectForSend(PlgSend pSend);
	//================== end ======================

		

		
}
