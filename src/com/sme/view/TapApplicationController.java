package com.sme.view;

import java.util.Date;
import java.util.List;

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
		try {
			log.info("<=====执行sysmenulist====>");
			List<TapApplication> tapApplications = tapApplicationServiceImpl.select(tapApplication);
//			RespUtil.setResp(tapApplications, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/tapApplication/tapApplicationlist";
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
	public String tapApplicationSave(TapApplication tapApplication,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tapApplication.getTapApplicationId()!=null){
//
//				return "redirect:/tapApplication/tapApplicationlist.do";
//			}else{
				
				return "redirect:/tapApplication/tapApplicationlist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tapApplicationAdd(model,tapApplication);
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
