package com.sme.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.LoginLog;
import com.sme.service.LoginLogService;
import com.sme.util.RespUtil;
@Controller
@RequestMapping("/login")
public class LoginLogController extends BaseController<LoginLog>{
	
	@Autowired
	private LoginLogService loginLogService;

	@Override
	public InterfaceBaseService getService() {
		return null;
	}
	
	@RequestMapping(value = "/loginLog")
	public String view() {
		return "/sys/loginLog";
	}
	
	@RequestMapping(value = "/getLoginLog")
	@ResponseBody
	public Object logList(HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
		String accName = req.getParameter("accName");
		
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("page", getBegin());
		parm.put("pageCount", getEnd());
		parm.put("accName", accName);

		int count = loginLogService.count(parm);
		List<LoginLog> sysAccs = loginLogService.page(parm);
		return RespUtil.pageResult(count, sysAccs);
	}
	
	@RequestMapping(value = "/batchDelete")
	@ResponseBody
	public StringJSON delete(String ids) {
		try {
			loginLogService.batchDelete(ids);
			return getSuccess(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "系统异常");
		}
	}
	
}
