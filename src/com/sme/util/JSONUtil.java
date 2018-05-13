package com.sme.util;

/**
 * json对象工具
 * @author yao
 *
 */
public class JSONUtil {
	
	public static JSONObject checkResult(int code,String message)
	{
		JSONObject result = new JSONObject();
		result.setCode(code);
		result.setInfo(message);
		return result;
	}
	
}
