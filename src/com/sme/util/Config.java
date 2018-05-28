package com.sme.util;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * 配置文件
 * @author yao
 *
 */
public class Config {
	
	private static Properties confProperties = new Properties();
	
	static {
		try {
			String PATH = Thread.currentThread()
					.getContextClassLoader().getResource("config.properties").getFile();
			
			
			confProperties.load(new FileInputStream(PATH));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static String getConfigProperty(String key) 
	{
		return confProperties.getProperty(key);
	}
	
	public static boolean isBoolean(String key)
	{
		return "true".equals(getConfigProperty(key));
	}
	
	public static Integer getInteger(String key) 
	{
		return Integer.parseInt(getConfigProperty(key));
	}
	
	public static final Integer TREE_ROOT_ID = 1;
	
	public static final boolean LOG_SWITCH =  isBoolean("log_switch");
	
	public static final String EMAIL_HOST = getConfigProperty("EMAIL_HOST");
	
	public static final String EMAIL_PORT = getConfigProperty("EMAIL_PORT");
	
	public static final String EMAIL_USERNAME = getConfigProperty("EMAIL_USERNAME");
	
	public static final String EAMIL_PASSWORD = getConfigProperty("EAMIL_PASSWORD");
	
	public static final String EMAIL_FROM = getConfigProperty("EMAIL_FROM");
	
	public static final String HOST = getConfigProperty("HOST");
	
	public static final String PORT = getConfigProperty("PORT");
	
	public static final String FILE_PATH = getConfigProperty("FILE_PATH");
	
	public static final String URL_PREFIX = getConfigProperty("URL_PREFIX");
	
	public static final String DIFF_SWITCH = getConfigProperty("DIFF_SWITCH");
	
	public static final Integer openCountPeriod = getInteger("openCountPeriod");
	
	public static final String DEFAULT_PASSWD = getConfigProperty("DEFAULT_PASSWD");

	public static final String DEFAULT_APK_PATH = getConfigProperty("defult.apk.path");

	public static final String DEFAULT_APK_ONLINEPATH = getConfigProperty("defult.apk.onlinepath");

	public static final String DEFAULT_APK_IMGPATH = getConfigProperty("defult.apk.picpath");

	public static final String HEAD_IMG_PATH = getConfigProperty("head.img.path");
	public static final String HEAD_IMG_REALPATH = getConfigProperty("head.img.realPath");
	//email
	public static final String MailServerHost = getConfigProperty("defult.MailServerHost");
	public static final String MailServerPort = getConfigProperty("defult.MailServerPort");
	public static final String UserName = getConfigProperty("defult.UserName");
	public static final String Password = getConfigProperty("defult.Password");
	public static final String FromAddress = getConfigProperty("defult.FromAddress");
	public static final String Subject = getConfigProperty("defult.Subject");
	public static final String Content1 = getConfigProperty("defult.Content1");
	public static final String Content2 = getConfigProperty("defult.Content2");
	public static final String CodeContent1 = getConfigProperty("defult.codeContent1");
	public static final String CodeContent2 = getConfigProperty("defult.codeContent2");

	//message
	public static final String MessageServerHost = getConfigProperty("defult.Message.serverHost");
	public static final String MessagePath = getConfigProperty("defult.Message.path");
	public static final String MessageAppcode = getConfigProperty("defult.Message.appcode");


	//message
	public static final String alipayGateway = getConfigProperty("defult.alipay.gateway");
	public static final String alipayAppId = getConfigProperty("defult.alipay.app_id");
	public static final String alipayPrivateKey = getConfigProperty("defult.alipay.private_key");
	public static final String alipayCharset = getConfigProperty("defult.alipay.charset");
	public static final String alipayFormat = getConfigProperty("defult.alipay.format");
	public static final String alipayPublicKey = getConfigProperty("defult.alipay.alipay_public_key");
	public static final String alipayAignType = getConfigProperty("defult.alipay.sign_type");
	public static final String QUICK_MSECURITY_PAY = getConfigProperty("defult.alipay.product_code");
	public static final String alipayMethod = getConfigProperty("defult.alipay.method");
	public static final String alipayVersion = getConfigProperty("defult.alipay.version");
	public static final String alipayNotifyUrl = getConfigProperty("defult.alipay.notify_url");

}
