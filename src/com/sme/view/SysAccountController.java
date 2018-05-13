package com.sme.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sme.core.model.BaseObject;
import com.sme.core.model.StringJSON;
import com.sme.core.service.BaseService;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.SysAcc;
import com.sme.entity.SysRole;
import com.sme.service.SysAccService;
import com.sme.service.SysRoleService;
import com.sme.util.MD5;
import com.sme.util.RespMessage;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/sysAccount")
public class SysAccountController extends BaseController<SysAcc>{
	@Resource(name="sysAccServiceImpl")
	private SysAccService sysAccServiceImpl;
	@Resource(name="sysRoleServiceImpl")
	private SysRoleService sysRoleServiceImpl;
	
	private Log log = LogFactory.getLog(SysAccController.class);
	
	@RequestMapping(value="/sysAcclist", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> sysAccListPost(HttpServletRequest req) {
		//分页属性
		if(req.getParameter("rows")!=null && req.getParameter("page")!=null){
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		}else{
			rows = 15;
			page = 1;
		}
		String startTime = req.getParameter("StartTime");
		String endTime = req.getParameter("EndTime");
		String sysAccName = req.getParameter("sysAccName");
		String sysAccState = req.getParameter("sysAccState");
		try {
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("startTime", startTime);
			parm.put("endTime", endTime);
			parm.put("sysAccName", sysAccName);
			parm.put("sysAccState", sysAccState);
			int count = sysAccServiceImpl.count(parm);
			List<SysAcc> sysAccs = sysAccServiceImpl.page(parm);
			return RespUtil.pageResult(count, sysAccs);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping(value="/sysAccountxxx", method={RequestMethod.GET})
	public String sysAccountxxx() {
		return "/sys/sysAccxxx";
	}
	
	@RequestMapping(value="/sysAcclist", method={RequestMethod.GET})
	public String sysAccList() {
		return "/sys/sysAcc";
	}
	
	@RequestMapping(value="/getsysAcclists", method={RequestMethod.GET})
	@ResponseBody
	public List<SysAcc> sysAccLists(SysAcc sysAcc, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<SysAcc> sysAccs = sysAccServiceImpl.select(sysAcc);
			return sysAccs;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String sysAccDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 SysAcc sysAcc = new SysAcc();
			 sysAcc.setSysAccId(id);
			sysAccServiceImpl.delete(sysAcc);
			return "redirect:/sysAcc/sysAcclist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/sysAcc/sysAcclist.do";
		}
	 }
	
	 @RequestMapping(value="/sysAccountDelete", method={RequestMethod.POST})
	 @ResponseBody
	 public StringJSON sysAccountDelete(String ids){
		 try
		{
			 if(ids != null && ids.length() > 0){
				 String[] idStrings = ids.split(",");
				 
				 for (String id : idStrings)
				{
					 SysAcc sysAcc = new SysAcc();
					 sysAcc.setSysAccId(Integer.valueOf(id));
					sysAccServiceImpl.delete(sysAcc);
				}
				 
				 return getSuccess(true, "删除成功！", null);
			 }else{
				 return getSuccess(false, "删除内容为空！", null);
			 }
		}
		catch (Exception e)
		{
			// TODO: handle exception
			return getSuccess(false, "系统异常！", null);
		}
	 }
	 
	 @RequestMapping(value="/sysAccountSave", method={RequestMethod.POST})
	 @ResponseBody
	 public StringJSON sysAccountSave(SysAcc sysAcc){
		 try
		{
			String password = MD5.encryByMD5("1");
			sysAcc.setSysAccPassword(password);
			sysAcc.setSysAccCdate(new Date());
			sysAcc.setSysAccCuser("admin");
			
			sysAccServiceImpl.insert(sysAcc);
			return getSuccess(true, "添加成功！", null);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			return getSuccess(false, "系统异常！", null);
		}
		 
	 }
	 
	 @RequestMapping(value="/sysAccountUpdate", method={RequestMethod.POST})
	 @ResponseBody
	 public StringJSON sysAccountUpdate(SysAcc sysAcc){
		 try
		{
			 	SysAcc sysAcc2 = new SysAcc();
				
				sysAcc2 = sysAccServiceImpl.getById(sysAcc);
				sysAcc2.setSysAccDesc(sysAcc.getSysAccDesc());
				sysAcc2.setSysAccName(sysAcc.getSysAccName());
				sysAcc2.setSysAccOrgid(sysAcc.getSysAccOrgid());
//				sysAcc2.setSysAccPassword(MD5.encryByMD5(sysAcc.getSysAccPassword()));
				sysAcc2.setSysAccRealname(sysAcc.getSysAccRealname());
				sysAcc2.setSysAccRoleid(sysAcc.getSysAccRoleid());
				sysAcc2.setSysAccState(sysAcc.getSysAccState());
				sysAcc2.setSysAccUdate(new Date());
				sysAcc2.setSysAccUueser("admin2");
				
				sysAccServiceImpl.update(sysAcc2);
			 return getSuccess(true, "修改成功！", null);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			return getSuccess(false, "系统异常！", null);
		}
	 }
	 
	@RequestMapping(value="/save", method={RequestMethod.POST})
	@ResponseBody
	public RespMessage sysAccSave(SysAcc sysAcc,Model model, HttpServletRequest request, HttpServletResponse response){
		RespMessage respMessage = new RespMessage();
		try
		{
			if(sysAcc.getSysAccId()!=null){
				
				SysAcc sysAcc2 = new SysAcc();
				
				sysAcc2 = sysAccServiceImpl.getById(sysAcc);
				sysAcc2.setSysAccDesc(sysAcc.getSysAccDesc());
				sysAcc2.setSysAccName(sysAcc.getSysAccName());
				sysAcc2.setSysAccOrgid(sysAcc.getSysAccOrgid());
//				sysAcc2.setSysAccPassword(MD5.encryByMD5(sysAcc.getSysAccPassword()));
				sysAcc2.setSysAccRealname(sysAcc.getSysAccRealname());
				sysAcc2.setSysAccRoleid(sysAcc.getSysAccRoleid());
				sysAcc2.setSysAccState(sysAcc.getSysAccState());
				sysAcc2.setSysAccUdate(new Date());
				sysAcc2.setSysAccUueser("admin2");
				
				sysAccServiceImpl.update(sysAcc2);
				
				respMessage.setCode("0");
				respMessage.setMessage("/sysAcc/sysAcclist.do");
				
				return respMessage;
			}else{
				String password = MD5.encryByMD5("1");
				sysAcc.setSysAccPassword(password);
				sysAcc.setSysAccCdate(new Date());
				sysAcc.setSysAccCuser("admin");
				
				sysAccServiceImpl.insert(sysAcc);
				
				respMessage.setCode("0");
				respMessage.setMessage("/sysAcc/sysAcclist.do");
				
				return respMessage;
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			respMessage.setCode("1");
			
			return respMessage;
		}
	}
	
	public String sysAccAdd(Model model,SysAcc sysAcc){
			List<SysRole> sysRoles = new ArrayList<SysRole>();
			sysRoles = sysRoleServiceImpl.select(null);
			model.addAttribute("uproles", sysRoles);
			model.addAttribute("sysAcc", sysAcc);
			return "/sys/sysAccform";
	}
	
	@RequestMapping("/add")
	public String sysAccAdd(Model model){
		List<SysRole> sysRoles = new ArrayList<SysRole>();
		sysRoles = sysRoleServiceImpl.select(null);
		model.addAttribute("sysAcc", new SysAcc());
		model.addAttribute("uproles", sysRoles);
		return "/sys/sysAccform";
	}
	
	@RequestMapping(value="/{id}/update")
	public String sysAccUpdate(@PathVariable Integer id, Model model){
		try
		{
			SysAcc sysAcc = new SysAcc();
			sysAcc.setSysAccId(id);
			sysAcc = sysAccServiceImpl.getById(sysAcc);
			sysAcc.setSysAccPassword("");
			return sysAccAdd(model,sysAcc);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/sysAcc/sysAcclist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView sysAccInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/sys/sysAccview");
		SysAcc sysAcc = new SysAcc();
		sysAcc.setSysAccId(id);
		sysAcc = sysAccServiceImpl.getById(sysAcc);
		mav.addObject("sysAcc", sysAcc);
		return mav;
	}

	public SysAccService getSysAccServiceImpl()
	{
		return sysAccServiceImpl;
	}

	public void setSysAccServiceImpl(SysAccService sysAccServiceImpl)
	{
		this.sysAccServiceImpl = sysAccServiceImpl;
	}

	public SysRoleService getSysRoleServiceImpl()
	{
		return sysRoleServiceImpl;
	}

	public void setSysRoleServiceImpl(SysRoleService sysRoleServiceImpl)
	{
		this.sysRoleServiceImpl = sysRoleServiceImpl;
	}

	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return sysAccServiceImpl;
	}
	//================== begin ======================
 
	//================== end ======================
}
