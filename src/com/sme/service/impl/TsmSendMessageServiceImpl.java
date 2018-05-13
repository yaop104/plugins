package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TsmSendMessageDao;
import com.sme.entity.TsmSendMessage;
import com.sme.service.TsmSendMessageService;
import com.sme.util.Config;
import com.sme.util.mail.MailSenderInfo;
import com.sme.util.mail.SimpleMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class TsmSendMessageServiceImpl extends BaseService<TsmSendMessage> implements TsmSendMessageService {
	@Autowired
	private TsmSendMessageDao tsmSendMessageDao;

	@Override
	public BaseDao<TsmSendMessage> getDao() {
		return tsmSendMessageDao;
	}
	public TsmSendMessageDao getTsmSendMessageDao()
	{
		return tsmSendMessageDao;
	}
	public void setTsmSendMessageDao(TsmSendMessageDao tsmSendMessageDao)
	{
		this.tsmSendMessageDao = tsmSendMessageDao;
	}
	
	//================== begin ======================

	@Override
	@Transactional
	public String sendMessage(TsmSendMessage tsmSendMessage) {
		String message="0";
		try {
			tsmSendMessage.setTsmSendState("2");
			tsmSendMessage.setTsmSendTime(new Date());
			tsmSendMessage.setTsmSendUuid(UUID.randomUUID().toString());
			
//			String flag = MessageSendUtil.sendMessage(tsmSendMessage.getTsmSendMobile(), tsmSendMessage.getTsmSendCode());
			boolean flag = sendMail(tsmSendMessage.getTsmSendMobile(), tsmSendMessage.getTsmSendCode());

			if(flag){
				message = "1";
				tsmSendMessageDao.insert(tsmSendMessage);
			}
			return message;
		} catch (Exception e) {
			return message;
		}
		
	}

	public boolean sendMail(String mail, String pwd){

		//这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		//服务器端口
		mailInfo.setMailServerHost(Config.MailServerHost);
		//或者是通过qq邮箱发送
//        mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort(Config.MailServerPort);
		mailInfo.setValidate(true);
		//您的邮箱用户名
		mailInfo.setUserName(Config.UserName);
		//您的邮箱密码
		mailInfo.setPassword(Config.Password);
		//发送邮件源地址
		mailInfo.setFromAddress(Config.FromAddress);
		//发送邮件目的地址
		mailInfo.setToAddress(mail);
		//主题
		mailInfo.setSubject(Config.Subject);
		//内容
		mailInfo.setContent(Config.CodeContent1 + pwd + Config.CodeContent2);
		//这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		Boolean flag = sms.sendTextMail(mailInfo);//发送文体格式
		return  flag;
	}

	@Override
	@Transactional
	public String selectLastCode(TsmSendMessage tsmSendMessage) {
		String message="0";
		try {
			TsmSendMessage tsmSendMessage2 = new TsmSendMessage();
			tsmSendMessage2 = tsmSendMessageDao.selectLastCode(tsmSendMessage);
			if(tsmSendMessage2!=null){
				tsmSendMessage2.setTsmSendState("1");
				tsmSendMessageDao.update(tsmSendMessage2);
				
				message = "1";
			}
			return message;
		} catch (Exception e) {
			return message;
		}
	}
	//================== end ======================
}
