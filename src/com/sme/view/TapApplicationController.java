package com.sme.view;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sme.core.model.StringJSON;
import com.sme.entity.SysAcc;
import com.sme.entity.TptPosition;
import com.sme.entity.TstStatement;
import com.sme.service.SysAccService;
import com.sme.service.TstStatementService;
import com.sme.service.impl.SysAccServiceImpl;
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

import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.TapApplication;
import com.sme.service.TapApplicationService;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TapApplication")
public class TapApplicationController extends BaseController<TapApplication>{
	@Autowired
	private TapApplicationService tapApplicationServiceImpl;

	private Log log = LogFactory.getLog(TapApplicationController.class);
	
	@RequestMapping(value="/tapApplicationlist", method={RequestMethod.GET})
	public String tapApplicationList(TapApplication tapApplication, HttpServletRequest req) {
		return "/tapApplication/tapApplicationlist";
	}

	@RequestMapping(value = "/insertForT", method = { RequestMethod.POST })
	@ResponseBody
	public StringJSON insertForT(TapApplication t, HttpServletRequest request) {
		SysAcc session = (SysAcc) getLoginUser(request);
		try {
			t.setTapApplicationCuser(session.getSysAccId());
			t.setTapApplicationCdate(new Date());
			tapApplicationServiceImpl.insert(t);
			return getSuccess(true, "新增成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "系统异常！");
		}
	}


	@RequestMapping(value = "/page", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> page(HttpServletRequest req) {
		SysAcc sysAcc = (SysAcc)getLoginUser(req);
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 15;
			page = 1;
		}
		try {
			String tptName = req.getParameter("tptName");
			String tptState = req.getParameter("tptState");

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("tapApplicationMoneyid", tptName);
			parm.put("tapApplicationCheckstate", tptState);
			parm.put("tapApplicationCuser", sysAcc.getSysAccId());
			if(sysAcc.getSysAccType().equals("2")){
				parm.put("tapApplicationCuserType", "1");
			}
			int count = tapApplicationServiceImpl.count(parm);
			List<TapApplication> sysAccs = tapApplicationServiceImpl.page(parm);
			return RespUtil.pageResult(count, sysAccs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value="/tapApplicationChecklist", method={RequestMethod.GET})
	public String tapApplicationChecklist(TapApplication tapApplication, HttpServletRequest req) {
		return "/tapApplication/tapApplicationChecklist";
	}

	@RequestMapping(value="/gettapApplicationlists", method={RequestMethod.GET})
	@ResponseBody
	public List<TapApplication> tapApplicationLists(TapApplication tapApplication, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TapApplication> tapApplications = tapApplicationServiceImpl.select(tapApplication);
			return tapApplications;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tapApplicationDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TapApplication tapApplication = new TapApplication();
//			 tapApplication.setTapApplicationId(id);
			tapApplicationServiceImpl.delete(tapApplication);
			return "redirect:/tapApplication/tapApplicationlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tapApplication/tapApplicationlist.do";
		}
	 }
	

	@RequestMapping(value="/save", method={RequestMethod.POST})
	@ResponseBody
	@com.sme.core.spring.Log(type = "运营商管理", desc = "修改运营商")
	public StringJSON tapApplicationSave(TapApplication tapApplication, Model model, HttpServletRequest request, HttpServletResponse response){
		SysAcc session = (SysAcc) getLoginUser(request);
		try
		{
			if(tapApplication.getTapApplicationUnid()!=null){
//				TptPosition tptPosition1 = new TptPosition();
//				tptPosition1.setTptUnid(tptPosition.getTptUnid());
//				tptPosition1 = tptPositionServiceImpl.getById(tptPosition1);
//				tptPosition1.setTptDemourl(tptPosition.getTptDemourl());
//				tptPosition1.setTptDesc(tptPosition.getTptDesc());
//				tptPosition1.setTptName(tptPosition.getTptName());
//				tptPosition1.setTptPrice(tptPosition.getTptPrice());
//				tptPosition1.setTptState(tptPosition.getTptState());
				return getSuccess(true, "修改成功");
			} else {

				tapApplication.setTapApplicationCdate(new Date());
				tapApplication.setTapApplicationState("1");
				tapApplication.setTapApplicationCuser(session.getSysAccId());
				tapApplication.setTapApplicationCheckstate("1");
				tapApplicationServiceImpl.insert(tapApplication);

				return getSuccess(true, "添加成功");
			}

		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return getSuccess(false, "发生系统异常");
		}
	}
	
	public String tapApplicationAdd(Model model,TapApplication tapApplication){
			model.addAttribute("tapApplication", tapApplication);
			return "/tapApplication/tapApplicationform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tapApplicationAdd(){
		ModelAndView mav = new ModelAndView("/tapApplication/tapApplicationform","tapApplication", new TapApplication());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tapApplicationUpdate(@PathVariable Integer id, Model model){
		try
		{
			TapApplication tapApplication = new TapApplication();
//			tapApplication.setTapApplicationId(id);
			tapApplication = tapApplicationServiceImpl.getById(tapApplication);
			return tapApplicationAdd(model,tapApplication);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tapApplication/tapApplicationlist.do";
		}
		
	}

	@RequestMapping(value = "/updateforCheck", method = { RequestMethod.POST })
	@ResponseBody
	public StringJSON updateforCheck(TapApplication t, HttpServletRequest request) {
		SysAcc session = (SysAcc) request.getSession().getAttribute("loginUser");
		try {
			t.setTapApplicationChecktime(new Date());
			t.setTapApplicationUuser(session.getSysAccId());
			t.setTapApplicationCheckname(session.getSysAccName());
			String flag =  tapApplicationServiceImpl.updateForCheck(t);
			if("1".endsWith(flag)){
				return getSuccess(true, "更新成功！");
			}else{
				return getSuccess(false, "系统异常！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "系统异常！");
		}
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tapApplicationInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tapApplication/tapApplicationview");
		TapApplication tapApplication = new TapApplication();
//		tapApplication.setTapApplicationId(id);
		tapApplication = tapApplicationServiceImpl.getById(tapApplication);
		mav.addObject("tapApplication", tapApplication);
		return mav;
	}
	
	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return tapApplicationServiceImpl;
	}
	
	public TapApplicationService getTapApplicationServiceImpl() {
		return tapApplicationServiceImpl;
	}

	public void setTapApplicationServiceImpl(TapApplicationService tapApplicationServiceImpl) {
		this.tapApplicationServiceImpl = tapApplicationServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
