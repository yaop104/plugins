package com.sme.view;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.LoginLog;
import com.sme.entity.SysAcc;
import com.sme.entity.SysMenu;
import com.sme.entity.SysRoleMenu;
import com.sme.service.LoginLogService;
import com.sme.service.SysAccService;
import com.sme.service.SysMenuService;
import com.sme.service.SysRoleMenuService;
import com.sme.util.MD5;
import com.sme.util.RespMessage;

@Controller
@SessionAttributes("loginUser")
public class SysIndexController extends BaseController<SysAcc>{
	@Autowired
	private SysMenuService sysMenuService; 
	@Autowired
	private SysAccService sysAccService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Autowired
	private LoginLogService loginLogService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/repwd")
	public String repwd(){
		return "/sys/repwd";
	}
	
	@RequestMapping(value="/repwd",method={RequestMethod.POST})
	@ResponseBody
	@com.sme.core.spring.Log(type = "修改密码", desc = "修改密码")
	public RespMessage repwd(String oldpwd, String newpwd, HttpSession session){
		RespMessage respMessage = new RespMessage();
		try {
			SysAcc sysAcc =  (SysAcc) session.getAttribute("loginUser");
			if(sysAcc!=null){
				SysAcc sysAcc1 = new SysAcc();
				sysAcc1 = sysAccService.getSysAccForLogin(sysAcc.getSysAccName(), oldpwd);//验证用户名、密码登录（用于普通登录）
				if(sysAcc1!=null){
					String password = MD5.encryByMD5(newpwd);
					sysAcc1.setSysAccPassword(password);
					sysAccService.update(sysAcc1);
					respMessage.setCode("0");
				}else{
					respMessage.setCode("1");
				}
			}else{
				respMessage.setCode("2");
			}
		} catch (Exception e) {
			respMessage.setCode("3");
		}
		return respMessage;
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req,Model model){
		req.getSession().invalidate(); //清除session
		 model.addAttribute("loginUser", new SysAcc());
		 return "redirect:/login.do";
	 }
	
	@RequestMapping("/selectTree")
	public String selectTree() {
		return "/sys/selectTree";
	}
	
	@RequestMapping("/menu")
	public ModelAndView menu(HttpSession session) {
		SysAcc sysAcc =  (SysAcc) session.getAttribute("loginUser");
		List<SysMenu> list = new ArrayList<SysMenu>();
		if(sysAcc!= null){
			List<SysMenu> sysMenu = new ArrayList<SysMenu>();
			List<String> strings = new ArrayList<String>();
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setSysRoleId(Integer.valueOf(sysAcc.getSysAccRoleid()));
			strings = sysRoleMenuService.selectMenuIds(sysRoleMenu);
			
			sysMenu = sysMenuService.select(null);
			
			for (int i = 0; i < sysMenu.size(); i++)
			{
				if(strings.contains(String.valueOf(sysMenu.get(i).getSysMenuId()))){
					list.add(sysMenu.get(i));
				}
				
			}
		}
		else{
			//方便开发时加入
			list = sysMenuService.select(null);
		}
		
		
		ModelAndView mav = new ModelAndView("/sys/left_menu", "menus", list);
		return mav;
	}
	
	@RequestMapping(value="/login", method={RequestMethod.POST})
	@ResponseBody
	public RespMessage login(String account, String password, HttpSession session, Model model, HttpServletRequest req){
			
		RespMessage respMessage = new RespMessage();
		try
		{
				
				SysAcc sysAcc = new SysAcc();
				//是否增加ip限制
				if(!"".equals(password) && !"".equals(account.trim()) ){
					sysAcc = sysAccService.getSysAccForLogin(account, password);//验证用户名、密码登录（用于普通登录）
					if(sysAcc != null){
						sysAcc.setSysAccPassword("");
						model.addAttribute("loginUser", sysAcc);
						respMessage.setCode("0");
						String path = req.getContextPath();
						respMessage.setMessage(path + "/index.do");
						
						//登陆成功 记录登陆日志
						LoginLog log = new LoginLog();
						log.setAccId(sysAcc.getSysAccId());
						log.setLoginIp(getIp(req));
						
						loginLogService.insert(log);
						
						return respMessage;
						
					}else{
						respMessage.setCode("1");
						respMessage.setMessage("用户名或密码错误！");
						return respMessage;
					}
					
				}else{
					respMessage.setCode("1");
					respMessage.setMessage("用户名或密码错误！");
					return respMessage;
				}
		}
		catch (Exception e)
		{
			
			e.getStackTrace();
			respMessage.setCode("1");
			respMessage.setMessage("失败，系统异常，请刷新页面，尝试重新登入！");
			return respMessage;
		}
		
	}

	@Override
	public InterfaceBaseService getService() {
		return null;
	}
	
	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getRemoteAddr();
	       }
	       return filterIp(ip);
	}
	
	private String filterIp(String ip) {
	    if (ip != null) {
	        String[] data = ip.split(",");
	        for (int i=0; i<data.length; i++) {
	            if (!"unknown".equalsIgnoreCase(data[i].replaceAll("\\s*", ""))) {
	                ip = data[i].replaceAll("\\s*", "");   //去除首尾空格
	                break;
	            }
	        }
	    }

	    return ip;
	}
}
