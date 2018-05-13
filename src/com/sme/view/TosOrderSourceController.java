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

import com.sme.service.impl.TosOrderSourceServiceImpl;
import com.sme.entity.TosOrderSource;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TosOrderSource")
public class TosOrderSourceController {
	@Autowired
	private TosOrderSourceServiceImpl tosOrderSourceServiceImpl;
	
	private Log log = LogFactory.getLog(TosOrderSourceController.class);
	
	@RequestMapping(value="/tosOrderSourcelist", method={RequestMethod.GET})
	public String tosOrderSourceList(TosOrderSource tosOrderSource, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TosOrderSource> tosOrderSources = tosOrderSourceServiceImpl.select(tosOrderSource);
//			RespUtil.setResp(tosOrderSources, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/tosOrderSource/tosOrderSourcelist";
	}
	
	@RequestMapping(value="/gettosOrderSourcelists", method={RequestMethod.GET})
	@ResponseBody
	public List<TosOrderSource> tosOrderSourceLists(TosOrderSource tosOrderSource, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TosOrderSource> tosOrderSources = tosOrderSourceServiceImpl.select(tosOrderSource);
			return tosOrderSources;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tosOrderSourceDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TosOrderSource tosOrderSource = new TosOrderSource();
//			 tosOrderSource.setTosOrderSourceId(id);
			tosOrderSourceServiceImpl.delete(tosOrderSource);
			return "redirect:/tosOrderSource/tosOrderSourcelist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tosOrderSource/tosOrderSourcelist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String tosOrderSourceSave(TosOrderSource tosOrderSource,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tosOrderSource.getTosOrderSourceId()!=null){
//
//				return "redirect:/tosOrderSource/tosOrderSourcelist.do";
//			}else{
				
				return "redirect:/tosOrderSource/tosOrderSourcelist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tosOrderSourceAdd(model,tosOrderSource);
		}
	}
	
	public String tosOrderSourceAdd(Model model,TosOrderSource tosOrderSource){
			model.addAttribute("tosOrderSource", tosOrderSource);
			return "/tosOrderSource/tosOrderSourceform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tosOrderSourceAdd(){
		ModelAndView mav = new ModelAndView("/tosOrderSource/tosOrderSourceform","tosOrderSource", new TosOrderSource());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tosOrderSourceUpdate(@PathVariable Integer id, Model model){
		try
		{
			TosOrderSource tosOrderSource = new TosOrderSource();
//			tosOrderSource.setTosOrderSourceId(id);
			tosOrderSource = tosOrderSourceServiceImpl.getById(tosOrderSource);
			return tosOrderSourceAdd(model,tosOrderSource);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tosOrderSource/tosOrderSourcelist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tosOrderSourceInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tosOrderSource/tosOrderSourceview");
		TosOrderSource tosOrderSource = new TosOrderSource();
//		tosOrderSource.setTosOrderSourceId(id);
		tosOrderSource = tosOrderSourceServiceImpl.getById(tosOrderSource);
		mav.addObject("tosOrderSource", tosOrderSource);
		return mav;
	}
	
	public TosOrderSourceServiceImpl getTosOrderSourceServiceImpl() {
		return tosOrderSourceServiceImpl;
	}

	public void setTosOrderSourceServiceImpl(TosOrderSourceServiceImpl tosOrderSourceServiceImpl) {
		this.tosOrderSourceServiceImpl = tosOrderSourceServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
