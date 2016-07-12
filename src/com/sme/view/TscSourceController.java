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

import com.sme.service.impl.TscSourceServiceImpl;
import com.sme.entity.TscSource;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TscSource")
public class TscSourceController extends BaseController<TscSource>{
	@Autowired
	private TscSourceServiceImpl tscSourceServiceImpl;
	
	private Log log = LogFactory.getLog(TscSourceController.class);
	
	@RequestMapping(value="/tscSourcelist", method={RequestMethod.GET})
	public String tscSourceList(TscSource tscSource, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TscSource> tscSources = tscSourceServiceImpl.select(tscSource);
//			RespUtil.setResp(tscSources, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/tscSource/tscSourcelist";
	}
	
	@RequestMapping(value="/gettscSourcelists", method={RequestMethod.GET})
	@ResponseBody
	public List<TscSource> tscSourceLists(TscSource tscSource, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TscSource> tscSources = tscSourceServiceImpl.select(tscSource);
			return tscSources;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tscSourceDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TscSource tscSource = new TscSource();
//			 tscSource.setTscSourceId(id);
			tscSourceServiceImpl.delete(tscSource);
			return "redirect:/tscSource/tscSourcelist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tscSource/tscSourcelist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String tscSourceSave(TscSource tscSource,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tscSource.getTscSourceId()!=null){
//
//				return "redirect:/tscSource/tscSourcelist.do";
//			}else{
				
				return "redirect:/tscSource/tscSourcelist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tscSourceAdd(model,tscSource);
		}
	}
	
	public String tscSourceAdd(Model model,TscSource tscSource){
			model.addAttribute("tscSource", tscSource);
			return "/tscSource/tscSourceform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tscSourceAdd(){
		ModelAndView mav = new ModelAndView("/tscSource/tscSourceform","tscSource", new TscSource());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tscSourceUpdate(@PathVariable Integer id, Model model){
		try
		{
			TscSource tscSource = new TscSource();
//			tscSource.setTscSourceId(id);
			tscSource = tscSourceServiceImpl.getById(tscSource);
			return tscSourceAdd(model,tscSource);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tscSource/tscSourcelist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tscSourceInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tscSource/tscSourceview");
		TscSource tscSource = new TscSource();
//		tscSource.setTscSourceId(id);
		tscSource = tscSourceServiceImpl.getById(tscSource);
		mav.addObject("tscSource", tscSource);
		return mav;
	}
	
	public TscSourceServiceImpl getTscSourceServiceImpl() {
		return tscSourceServiceImpl;
	}

	public void setTscSourceServiceImpl(TscSourceServiceImpl tscSourceServiceImpl) {
		this.tscSourceServiceImpl = tscSourceServiceImpl;
	}

	@Override
	public InterfaceBaseService<TscSource> getService() {
		return tscSourceServiceImpl;
	}

	//================== begin ======================
 
	//================== end ======================
}
