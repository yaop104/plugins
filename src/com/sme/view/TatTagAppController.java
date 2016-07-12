package com.sme.view;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
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

import com.sme.service.impl.TatTagAppServiceImpl;
import com.sme.entity.TatTagApp;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TatTagApp")
public class TatTagAppController extends BaseController<TatTagApp>{
	@Autowired
	private TatTagAppServiceImpl tatTagAppServiceImpl;
	
	private Log log = LogFactory.getLog(TatTagAppController.class);
	
	@RequestMapping(value="/tatTagApplist", method={RequestMethod.GET})
	public String tatTagAppList(TatTagApp tatTagApp, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TatTagApp> tatTagApps = tatTagAppServiceImpl.select(tatTagApp);
//			RespUtil.setResp(tatTagApps, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/tatTagApp/tatTagApplist";
	}
	
	@RequestMapping(value="/gettatTagApplists", method={RequestMethod.GET})
	@ResponseBody
	public List<TatTagApp> tatTagAppLists(TatTagApp tatTagApp, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TatTagApp> tatTagApps = tatTagAppServiceImpl.select(tatTagApp);
			return tatTagApps;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tatTagAppDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TatTagApp tatTagApp = new TatTagApp();
//			 tatTagApp.setTatTagAppId(id);
			tatTagAppServiceImpl.delete(tatTagApp);
			return "redirect:/tatTagApp/tatTagApplist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tatTagApp/tatTagApplist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String tatTagAppSave(TatTagApp tatTagApp,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tatTagApp.getTatTagAppId()!=null){
//
//				return "redirect:/tatTagApp/tatTagApplist.do";
//			}else{
				
				return "redirect:/tatTagApp/tatTagApplist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tatTagAppAdd(model,tatTagApp);
		}
	}
	
	public String tatTagAppAdd(Model model,TatTagApp tatTagApp){
			model.addAttribute("tatTagApp", tatTagApp);
			return "/tatTagApp/tatTagAppform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tatTagAppAdd(){
		ModelAndView mav = new ModelAndView("/tatTagApp/tatTagAppform","tatTagApp", new TatTagApp());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tatTagAppUpdate(@PathVariable Integer id, Model model){
		try
		{
			TatTagApp tatTagApp = new TatTagApp();
//			tatTagApp.setTatTagAppId(id);
			tatTagApp = tatTagAppServiceImpl.getById(tatTagApp);
			return tatTagAppAdd(model,tatTagApp);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tatTagApp/tatTagApplist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tatTagAppInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tatTagApp/tatTagAppview");
		TatTagApp tatTagApp = new TatTagApp();
//		tatTagApp.setTatTagAppId(id);
		tatTagApp = tatTagAppServiceImpl.getById(tatTagApp);
		mav.addObject("tatTagApp", tatTagApp);
		return mav;
	}
	
	public TatTagAppServiceImpl getTatTagAppServiceImpl() {
		return tatTagAppServiceImpl;
	}

	public void setTatTagAppServiceImpl(TatTagAppServiceImpl tatTagAppServiceImpl) {
		this.tatTagAppServiceImpl = tatTagAppServiceImpl;
	}

	@Override
	public InterfaceBaseService<TatTagApp> getService() {
		return tatTagAppServiceImpl;
	}

	//================== begin ======================
 
	//================== end ======================
}
