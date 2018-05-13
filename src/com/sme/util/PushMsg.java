package com.sme.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PushMsg {
	
	/**
	 * 微态推送
	 * @param msgcontent 需发送内容列表
	 * systemmode (1友圈2公众号3profile4t推送)
	 * type(0评论1赞2微态3推送)
	 * isAdd(1新增0删除2推送)
	 * userid(用户id)
	 * mainid(微态id/评论id/赞id)
	 * extra(扩展字段备用)
	 * @param touserids 接收人id
	 */
	public static String push(Map<String,String> msgcontent,String[] touserids){
		String url = "http://218.205.81.29:80/msggw/server/sendmsg/sendxmlmsg";
		HttpRequester req = new HttpRequester();
		Map<String, String> map = new HashMap<String, String>();
		map.put("msgbiztype", "PUBLICACCOUNT_AUTOREPLY_PUSH");
		map.put("msgid", "msgid");
		map.put("msgdigest", "摘要");
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < touserids.length; i ++) {
			if(i==0){
				sb.append(touserids[i]);
			}else{
				sb.append(";").append(touserids[i]);
			}
		}
		map.put("receivers", sb.toString());
		map.put("appkey", "003300ac");
//		String json = JSONObject.toJSONString(msgcontent);
		map.put("msgcontent", msgcontent.toString());
		try {
			HttpResponse res;
			res = req.sendPost(url, map);
			return res.getContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
