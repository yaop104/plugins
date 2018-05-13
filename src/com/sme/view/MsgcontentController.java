package com.sme.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.Msgcontent;
import com.sme.service.MsgcontentService;
import com.sme.util.PushMsg;

@RequestMapping("/msgcontent")
@Controller
public class MsgcontentController extends BaseController<Msgcontent>{

	@Autowired
	private MsgcontentService msgcontentService;
	
	@Override
	public InterfaceBaseService<Msgcontent> getService() {
		return msgcontentService;
	}
	
	@RequestMapping(value="/push" ,method={RequestMethod.POST})
	@ResponseBody
	public String push(Long userid){
		String result = null;
		Msgcontent msg = new Msgcontent();
		msg.setUserid(userid);
		
		Map<String,String> map = new HashMap<String, String>();
		List<Msgcontent> msgcontents = msgcontentService.select(msg);
		for (Msgcontent msgcontent : msgcontents) {
			map.put("systemmode", msgcontent.getSystemmode()+"");
			map.put("type", msgcontent.getType()+"");
			map.put("isAdd", msgcontent.getIsAdd()+"");
			map.put("userid", msgcontent.getUserid()+"");
			map.put("mainid", msgcontent.getMainid()+"");
			map.put("extra", msgcontent.getExtra()+"");
			result = PushMsg.push(map, msgcontent.getTouserids().split(","));
			msgcontentService.delete(msgcontent);
		}
		
		return result;
	}
	
}
