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
import com.sme.entity.OperateLog;
import com.sme.entity.SysMenu;
import com.sme.service.OperateLogService;
import com.sme.util.RespUtil;
@Controller
@RequestMapping("/operate")
public class OperateLogController extends BaseController<OperateLog> {
	
	@Autowired
	private OperateLogService operateLogService;

	@Override
	public InterfaceBaseService getService() {
		return null;
	}
	
	@RequestMapping(value = "/viewOperateLog")
	public String opreateLog() {
		return "/sys/operateLog";
	}

	@RequestMapping(value = "/getOperateLog")
	@ResponseBody
	public Object getOperateLog(HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
		String accName = req.getParameter("accName");
		String beginTime = req.getParameter("beginTime");
		String endTime = req.getParameter("endTime");
		String operateType = req.getParameter("operateType");
		
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("page", getBegin());
		parm.put("pageCount", getEnd());
		parm.put("accName", accName);
		parm.put("beginTime", beginTime);
		parm.put("endTime", endTime);
		parm.put("operateType", operateType);

		int count = operateLogService.count(parm);
		List<OperateLog> sysAccs = operateLogService.page(parm);
		return RespUtil.pageResult(count, sysAccs);
	}
	
	@RequestMapping(value = "/batchDelete")
	@ResponseBody
	public StringJSON delete(String ids) {
		try {
			operateLogService.batchDelete(ids);
			return getSuccess(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "系统异常");
		}
	}
	
	@RequestMapping(value = "/getMenus")
	@ResponseBody
	public List<SysMenu> getMenus() {
		List<SysMenu> list = operateLogService.getOperateMenus();
		
		SysMenu menu = new SysMenu();
		menu.setSysMenuName("修改密码");
		list.add(menu);
		
		return list;
	}
}
