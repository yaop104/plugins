package com.sme.service;


import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.TsmSendMessage;

public interface TsmSendMessageService extends InterfaceBaseService<TsmSendMessage> {

	//================== begin ======================

	String sendMessage(TsmSendMessage tsmSendMessage);
	String selectLastCode(TsmSendMessage tsmSendMessage);
	//================== end ======================

}
