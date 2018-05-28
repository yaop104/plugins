package com.sme.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TsmSendMessageDao;
import com.sme.entity.MessageBody;
import com.sme.entity.TsmSendMessage;
import com.sme.service.TsmSendMessageService;
import com.sme.util.Config;
import com.sme.util.HttpSendExecutor;
import com.sme.util.HttpUtils;
import com.sme.util.mail.MailSenderInfo;
import com.sme.util.mail.SimpleMailSender;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.sme.util.Config.*;

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
	public void sendMessage(TsmSendMessage tsmSendMessage) {
		try {
			tsmSendMessage.setTsmSendState("2");
			tsmSendMessage.setTsmSendTime(new Date());
			tsmSendMessage.setTsmSendUuid(UUID.randomUUID().toString());
			tsmSendMessageDao.insert(tsmSendMessage);

//			String flag = MessageSendUtil.sendMessage(tsmSendMessage.getTsmSendMobile(), tsmSendMessage.getTsmSendCode());
			HttpSendExecutor.sendMessageInfo(tsmSendMessage.getTsmSendMobile(), tsmSendMessage.getTsmSendCode());


		} catch (Exception e) {

		}
		
	}

	public static boolean sendPhone(String phone, String pwd){

		Boolean flag = false;

		String host = MessageServerHost;
		String path = MessagePath;
		String method = "GET";
		Map<String, String> headers = new HashMap<String, String>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + MessageAppcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("param", pwd);
		querys.put("phone", phone);
		querys.put("sign", "1");
		querys.put("skin", "2");


		try {
			/**
			 * 重要提示如下:
			 * HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			//System.out.println(response.toString());
			//获取response的body
			String responseBody = EntityUtils.toString(response.getEntity());
			MessageBody messageBody = JSONObject.parseObject(responseBody, MessageBody.class);
			if(messageBody.getCode().equals("OK")){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  flag;
	}


	public static boolean sendMail(String mail, String pwd){

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
