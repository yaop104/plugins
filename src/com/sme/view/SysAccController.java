package com.sme.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sme.entity.SysAcc;
import com.sme.entity.SysRole;
import com.sme.service.impl.SysAccServiceImpl;
import com.sme.service.impl.SysRoleServiceImpl;
import com.sme.util.MD5;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/sysAcc")
public class SysAccController extends BaseController<SysAcc> {
	@Autowired
	private SysAccServiceImpl sysAccServiceImpl;
	@Autowired
	private SysRoleServiceImpl sysRoleServiceImpl;

	private Log log = LogFactory.getLog(SysAccController.class);

	@RequestMapping(value = "/myAccountInfo", method = { RequestMethod.GET })
	public String myAccountInfo(Model model, HttpServletRequest req) {
		SysAcc sysAcc = (SysAcc)getLoginUser(req);
		sysAcc = sysAccServiceImpl.getById(sysAcc);
		model.addAttribute("sysAcc", sysAcc);
		return "/sys/myAccountInfo";
	}

	@RequestMapping(value = "/sysAcclist", method = { RequestMethod.GET })
	public String sysAccList(SysAcc sysAcc, HttpServletRequest req) {
		return "/sys/sysAcclist";
	}

	@RequestMapping(value = "/getsysAcclists")
	@ResponseBody
	public Map<String, Object> sysAccLists(SysAcc sysAcc, HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
//		String startTime = req.getParameter("StartTime");
//		String endTime = req.getParameter("EndTime");
		String sysAccName = req.getParameter("sysAccName");
		String sysAccState = req.getParameter("sysAccState");

		try {
			log.info("<=====执行sysmenulist====>");

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
//			parm.put("startTime", startTime);
//			parm.put("endTime", endTime);
			parm.put("sysAccName", sysAccName);
			parm.put("sysAccState", sysAccState);

			int count = sysAccServiceImpl.count(parm);
			List<SysAcc> sysAccs = sysAccServiceImpl.page(parm);
			return RespUtil.pageResult(count, sysAccs);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;

	}

	@RequestMapping(value = "/deleteSysAcc")
	@ResponseBody
	@com.sme.core.spring.Log(type = "账号管理", desc = "删除账号")
	public StringJSON sysAccDelete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					SysAcc sysAcc = new SysAcc();
					sysAcc.setSysAccId(Integer.valueOf(id));
					sysAccServiceImpl.delete(sysAcc);
				}

				return getSuccess(true, "删除成功！", null);
			} else {
				return getSuccess(false, "删除内容为空！", null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return getSuccess(false, "系统异常！", null);
		}
	}

	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	@com.sme.core.spring.Log(type = "账号管理", desc = "重置密码")
	public StringJSON resetPwd(SysAcc sysAcc) {
		try {
				SysAcc sysAcc2 = new SysAcc();
				sysAcc2 = sysAccServiceImpl.getById(sysAcc);
				sysAcc2.setSysAccPassword(MD5.encryByMD5("123456"));
				sysAccServiceImpl.update(sysAcc2);
				return getSuccess(true, "成功,密码为：123456！", null);
		} catch (Exception e) {
			// TODO: handle exception
			return getSuccess(false, "系统异常！", null);
		}
	}

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	@ResponseBody
	@com.sme.core.spring.Log(type = "账号管理", desc = "修改账号")
	public StringJSON sysAccSave(SysAcc sysAcc, Model model, HttpServletRequest request,
								 HttpServletResponse response) {
		try {
			if (sysAcc.getSysAccId() != null) {

				SysAcc sysAcc2 = new SysAcc();

				sysAcc2 = sysAccServiceImpl.getById(sysAcc);
				sysAcc2.setSysAccDesc(sysAcc.getSysAccDesc());
				sysAcc2.setSysAccName(sysAcc.getSysAccName());
				sysAcc2.setSysAccOrgid(sysAcc.getSysAccOrgid());
				// sysAcc2.setSysAccPassword(MD5.encryByMD5(sysAcc.getSysAccPassword()));
				sysAcc2.setSysAccMobile(sysAcc.getSysAccMobile());
				sysAcc2.setSysAccRealname(sysAcc.getSysAccRealname());
				sysAcc2.setSysAccRoleid(sysAcc.getSysAccRoleid());
				sysAcc2.setSysAccState(sysAcc.getSysAccState());
				sysAcc2.setSysAccType(sysAcc.getSysAccType());
				sysAcc2.setSysAccUdate(new Date());
				sysAcc2.setSysAccUueser("admin2");

				sysAccServiceImpl.update(sysAcc2);

				return getSuccess(true, "修改成功", null);
			} else {

				SysAcc sysAccnames=new SysAcc();
				sysAccnames.setSysAccName(sysAcc.getSysAccName());
				if(chkAccountIsExists(sysAccnames)){
					return getSuccess(false, "该用户名已存在,请重新输入用户名！！");
				}

				if(!sysAcc.getSysAccType().equals("1")){
					SysAcc sysAccphone = new SysAcc();
					sysAccphone.setSysAccMobile(sysAcc.getSysAccMobile());
					if(chkAccountIsExists(sysAccphone)){
						return getSuccess(false, "该手机号码已存在,请输入未注册手机号码！！");
					}
				}

				String password = MD5.encryByMD5("1");
				sysAcc.setSysAccPassword(password);
				sysAcc.setSysAccCdate(new Date());
				sysAcc.setSysAccCuser("admin");
				sysAcc.setSysAccMoney(0);

				sysAccServiceImpl.insert(sysAcc);

				return getSuccess(true, "添加成功", null);
			}

		} catch (Exception e) {
			log.error(e.getCause().getMessage());
			System.out.println(e.getCause().getMessage());
			return getSuccess(false, "发生系统异常");
		}
	}


	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	@ResponseBody
	@com.sme.core.spring.Log(type = "首页", desc = "运营商注册")
	public StringJSON sysAccRegistere(SysAcc sysAcc, Model model, HttpServletRequest request,
								 HttpServletResponse response) {
		try {

				SysAcc sysAccnames=new SysAcc();
				sysAccnames.setSysAccName(sysAcc.getSysAccName());
				if(chkAccountIsExists(sysAccnames)){
					return getSuccess(false, "该用户名已存在,请重新输入用户名！！");
				}
				SysAcc sysAccphone = new SysAcc();
				sysAccphone.setSysAccMobile(sysAcc.getSysAccMobile());
				if(chkAccountIsExists(sysAccphone)){
					return getSuccess(false, "该手机号码已存在,请输入未注册手机号码！！");
				}

				String password = MD5.encryByMD5(sysAcc.getSysAccPassword());
				sysAcc.setSysAccPassword(password);
			    sysAcc.setSysAccState("2");
				sysAcc.setSysAccType("2");
				sysAcc.setSysAccCdate(new Date());
				sysAcc.setSysAccCuser("运营商"+sysAcc.getSysAccName());

				sysAccServiceImpl.insert(sysAcc);

				return getSuccess(true, "注册成功，帐号审核通过后客服将会联系你！");

		} catch (Exception e) {
			log.error(e.getCause().getMessage());
			System.out.println(e.getCause().getMessage());
			return getSuccess(false, "系统异常，注册失败！");
		}
	}

	private boolean chkAccountIsExists(SysAcc sysAcc) {
		Boolean flag = sysAccServiceImpl.getSysAcc(sysAcc);
		return flag;
	}

	public String sysAccAdd(Model model, SysAcc sysAcc) {
		List<SysRole> sysRoles = new ArrayList<SysRole>();
		sysRoles = sysRoleServiceImpl.select(null);
		model.addAttribute("uproles", sysRoles);
		model.addAttribute("sysAcc", sysAcc);
		return "/sys/sysAccform";
	}

	@RequestMapping("/add")
	public String sysAccAdd(Model model) {
		List<SysRole> sysRoles = new ArrayList<SysRole>();
		sysRoles = sysRoleServiceImpl.select(null);
		model.addAttribute("sysAcc", new SysAcc());
		model.addAttribute("uproles", sysRoles);
		return "/sys/sysAccform";
	}

	@RequestMapping(value = "/{id}/update")
	public String sysAccUpdate(@PathVariable Integer id, Model model) {
		try {
			SysAcc sysAcc = new SysAcc();
			sysAcc.setSysAccId(id);
			sysAcc = sysAccServiceImpl.getById(sysAcc);
			sysAcc.setSysAccPassword("");
			return sysAccAdd(model, sysAcc);
		} catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:/sysAcc/sysAcclist.do";
		}

	}

	@RequestMapping("/{id}/info")
	public ModelAndView sysAccInfo(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("/sys/sysAccview");
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccId(id);
		sysAcc = sysAccServiceImpl.getById(sysAcc);
		mav.addObject("sysAcc", sysAcc);
		return mav;
	}

	public SysAccServiceImpl getSysAccServiceImpl() {
		return sysAccServiceImpl;
	}

	public void setSysAccServiceImpl(SysAccServiceImpl sysAccServiceImpl) {
		this.sysAccServiceImpl = sysAccServiceImpl;
	}

	public SysRoleServiceImpl getSysRoleServiceImpl() {
		return sysRoleServiceImpl;
	}

	public void setSysRoleServiceImpl(SysRoleServiceImpl sysRoleServiceImpl) {
		this.sysRoleServiceImpl = sysRoleServiceImpl;
	}

	@Override
	public InterfaceBaseService getService() {
		// TODO Auto-generated method stub
		return sysAccServiceImpl;
	}

	// ================== begin ======================

	// ================== end ======================
}
