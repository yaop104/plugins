package com.sme.view;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.sme.core.view.BaseController;
import com.sme.entity.SysRole;
import com.sme.service.impl.SysRoleServiceImpl;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController<SysRole> {
	@Override
	public InterfaceBaseService getService() {
		// TODO Auto-generated method stub
		return sysRoleServiceImpl;
	}

	@Autowired
	private SysRoleServiceImpl sysRoleServiceImpl;

	private Log log = LogFactory.getLog(SysRoleController.class);

	@RequestMapping(value = "/selectRoles", method = { RequestMethod.POST })
	@ResponseBody
	public List<SysRole> selectRoles() {
		return sysRoleServiceImpl.select(null);
	}

	@RequestMapping(value = "/roleView")
	public String sysRoleListPost() {
		if (log.isDebugEnabled()) {
			log.debug("角色管理");
		}
		return "/sys/sysRolelist";
	}

	@RequestMapping(value = "/sysRolelist")
	@ResponseBody
	public Object sysRoleList(SysRole sysRole, HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
		String rolename = req.getParameter("rolename");
		String rolestate = req.getParameter("rolestate");
		try {
			log.info("<=====执行sysmenulist====>");
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("rolename", rolename);
			parm.put("rolestate", rolestate);
			int count = sysRoleServiceImpl.count(parm);
			List<SysRole> sysRoles = sysRoleServiceImpl.page(parm);

			return RespUtil.pageResult(count, sysRoles);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "/deleteById")
	@ResponseBody
	@com.sme.core.spring.Log(type = "角色管理", desc = "删除角色")
	public StringJSON sysRoleDelete(String ids) {
		try {
			log.info("<=====执行delete口====>" + id);
			if (StringUtils.isNotBlank(ids)) {
				String[] idArra = ids.split(",");
				for (String id : idArra) {
					SysRole sysRole = new SysRole();
					sysRole.setRoleid(Integer.valueOf(id));
					sysRoleServiceImpl.delete(sysRole);
				}
			}
			return getSuccess(true, "删除成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			return getSuccess(false, "系统异常");
		}
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	@com.sme.core.spring.Log(type = "角色管理", desc = "修改角色")
	public StringJSON sysRoleSave(SysRole sysRole, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (sysRole.getRoleid() != null) {

				SysRole sysRole2 = new SysRole();

				sysRole2 = sysRoleServiceImpl.getById(sysRole);

				sysRole2.setRoledesc(sysRole.getRoledesc());
				sysRole2.setRolename(sysRole.getRolename());
				sysRole2.setRoleorder(sysRole.getRoleorder());
				sysRole2.setRolestate(sysRole.getRolestate());
				sysRole2.setRoletype(sysRole.getRoletype());
				sysRole2.setRoleudate(new Date());
				sysRole2.setRoleuuser("admin2");

				sysRoleServiceImpl.update(sysRole2);

				return getSuccess(true, "修改成功");
			} else {

				sysRole.setRolecdate(new Date());
				sysRole.setRolecuser("admin");

				sysRoleServiceImpl.insert(sysRole);

				return getSuccess(true, "添加成功");
			}

		} catch (Exception e) {
			log.error(e.getMessage());

			return getSuccess(false, "系统异常");
		}
	}

	public String sysRoleAdd(Model model, SysRole sysRole) {
		model.addAttribute("sysRole", sysRole);
		return "/sys/sysRoleform";
	}

	@RequestMapping("/{id}/info")
	public ModelAndView sysRoleInfo(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("/sys/sysRoleview");
		SysRole sysRole = new SysRole();
		sysRole.setRoleid(id);
		sysRole = sysRoleServiceImpl.getById(sysRole);
		mav.addObject("sysRole", sysRole);
		return mav;
	}

	public SysRoleServiceImpl getSysRoleServiceImpl() {
		return sysRoleServiceImpl;
	}

	public void setSysRoleServiceImpl(SysRoleServiceImpl sysRoleServiceImpl) {
		this.sysRoleServiceImpl = sysRoleServiceImpl;
	}

	// ================== begin ======================

	// ================== end ======================
}
