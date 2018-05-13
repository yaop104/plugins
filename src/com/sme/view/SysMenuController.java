package com.sme.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.spring.Log;
import com.sme.core.view.BaseController;
import com.sme.entity.SysAcc;
import com.sme.entity.SysMenu;
import com.sme.entity.SysRoleMenu;
import com.sme.entity.TreeNode;
import com.sme.service.impl.SysMenuServiceImpl;
import com.sme.service.impl.SysRoleMenuServiceImpl;
import com.sme.util.RespMessage;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController<SysMenu> {
	@Autowired
	private SysMenuServiceImpl sysMenuServiceImpl;
	@Autowired
	private SysRoleMenuServiceImpl sysRoleMenuServiceImpl;

	private Logger log = LoggerFactory.getLogger(SysMenuController.class);

	@RequestMapping(value = "/sysmenu")
	public String sysMenuListPost(SysMenu sysMenu, HttpServletRequest req) {
		return "/sys/menulist";
	}

	@RequestMapping(value = "/sysmenulist")
	@ResponseBody
	public Map<String, Object> sysMenuList(SysMenu sysMenu, HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
		String menuName = req.getParameter("sysMenuName");
		String menuState = req.getParameter("sysMenuState");
		try {
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("sysMenuName", menuName);
			parm.put("sysMenuState", menuState);
			int count = sysMenuServiceImpl.count(parm);
			List<SysMenu> sysMenus = sysMenuServiceImpl.page(parm);
			return RespUtil.pageResult(count, sysMenus);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "/deleteById")
	@ResponseBody
	@Log(type = "菜单管理", desc = "删除菜单")
	public StringJSON sysMenuDelete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					SysMenu sysMenu = new SysMenu();
					sysMenu.setSysMenuId(Integer.valueOf(id));
					sysMenuServiceImpl.delete(sysMenu);
				}

				return getSuccess(true, "删除成功！", null);
			} else {
				return getSuccess(false, "删除内容为空！", null);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return getSuccess(false, "系统异常");
		}
	}

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	@ResponseBody
	@Log(type = "菜单管理", desc = "保存更改菜单")
	public StringJSON sysMenuSave(SysMenu sysMenu, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		SysAcc session = (SysAcc) request.getSession().getAttribute("loginUser");
		try {
			if (sysMenu.getSysMenuId() != null) {
				SysMenu sMenu = new SysMenu();
				
				sMenu = sysMenuServiceImpl.getById(sysMenu);

				if (session != null) {
					sMenu.setSysMenuUueser(session.getSysAccName());
				}
				
				sMenu.setSysMenuUdate(new Date());
				sMenu.setSysMenuDesc(sysMenu.getSysMenuDesc());
				sMenu.setSysMenuName(sysMenu.getSysMenuName());
				sMenu.setSysMenuOrder(sysMenu.getSysMenuOrder());
				sMenu.setSysMenuState(sysMenu.getSysMenuState());
				sMenu.setSysMenuType(sysMenu.getSysMenuType());
				sMenu.setSysMenuUrl(sysMenu.getSysMenuUrl());
				sysMenuServiceImpl.update(sMenu);

				return getSuccess(true, "修改成功");
			} else {
//				int key = sysMenu.getSysMenuId();
//				
//				if (log.isDebugEnabled()) {
//					log.debug("当前要修改的菜单id为{}", key);
//				}
				
				sysMenu.setSysMenuCdate(new Date());
				if (session != null)
					sysMenu.setSysMenuCuser(session.getSysAccName());
				
				sysMenuServiceImpl.insert(sysMenu);
				
				
				return getSuccess(true, "添加成功");
			}

		} catch (Exception e) {
			log.error(e.getMessage());

			return getSuccess(false, "系统异常");
		}
	}

	public String sysMenuAdd(Model model, SysMenu sysMenu) {
		List<SysMenu> sysMenus = new ArrayList<SysMenu>();
		sysMenus = sysMenuServiceImpl.select(null);
		model.addAttribute("sysMenu", sysMenu);
		model.addAttribute("upmenus", sysMenus);
		return "/sys/menuform";
	}

	@RequestMapping("/{id}/info")
	public ModelAndView sysMenuInfo(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("/sys/menuview");
		SysMenu sysMenu = new SysMenu();
		sysMenu.setSysMenuId(id);
		sysMenu = sysMenuServiceImpl.getById(sysMenu);
		mav.addObject("sysMenu", sysMenu);
		return mav;
	}

	@RequestMapping(value = "/menutree", method = { RequestMethod.POST })
	@ResponseBody
	public TreeNode menuTree(String roleid) {

		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<SysRoleMenu> sysRoleMenus = new ArrayList<SysRoleMenu>();
		List<String> sysMenuIds = new ArrayList<String>();
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		sysRoleMenu.setSysRoleId(Integer.valueOf(roleid));
		sysRoleMenus = sysRoleMenuServiceImpl.select(sysRoleMenu);

		for (int i = 0; i < sysRoleMenus.size(); i++) {
			sysMenuIds.add(String.valueOf(sysRoleMenus.get(i).getSysMenuId()));
		}

		nodes = sysMenuServiceImpl.menuTree(sysMenuIds);

		return nodes.get(0);
	}

	@RequestMapping(value = "/menutreeupdate", method = { RequestMethod.POST })
	@ResponseBody
	@com.sme.core.spring.Log(type = "角色管理", desc = "分配角色权限")
	public RespMessage menuTreeUpdate(String delData, String addData, String rolId) {
		String message = "";
		RespMessage respMessage = new RespMessage();
		try {
			message = sysRoleMenuServiceImpl.menuTreeUpdate(delData, addData, rolId);
		} catch (Exception e) {
			log.error(e.getMessage());
			respMessage.setCode("1");
			respMessage.setBody(null);
			respMessage.setMessage("角色与菜单关联失败，请重新尝试。");
		}

		if ("0".equals(message)) {
			respMessage.setCode("0");
			respMessage.setBody(null);
			respMessage.setMessage("角色与菜单关联成功。");
		} else {
			respMessage.setCode("1");
			respMessage.setBody(null);
			respMessage.setMessage("角色与菜单关联失败，请重新尝试。");
		}
		return respMessage;
	}
	
	@RequestMapping(value="/selectMenus")
	@ResponseBody
	public List<SysMenu> getAll() {
		return sysMenuServiceImpl.select(null);
	}

	public SysMenuServiceImpl getSysMenuServiceImpl() {
		return sysMenuServiceImpl;
	}

	public void setSysMenuServiceImpl(SysMenuServiceImpl sysMenuServiceImpl) {
		this.sysMenuServiceImpl = sysMenuServiceImpl;
	}

	public SysRoleMenuServiceImpl getSysRoleMenuServiceImpl() {
		return sysRoleMenuServiceImpl;
	}

	public void setSysRoleMenuServiceImpl(SysRoleMenuServiceImpl sysRoleMenuServiceImpl) {
		this.sysRoleMenuServiceImpl = sysRoleMenuServiceImpl;
	}

	@Override
	public InterfaceBaseService getService() {
		// TODO Auto-generated method stub
		return null;
	}
}
