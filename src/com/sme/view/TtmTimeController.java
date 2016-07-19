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

import com.sme.service.impl.TtmTimeServiceImpl;
import com.sme.entity.TtmTime;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TtmTime")
public class TtmTimeController {
	@Autowired
	private TtmTimeServiceImpl ttmTimeServiceImpl;
	
	private Log log = LogFactory.getLog(TtmTimeController.class);
	
	@RequestMapping(value="/ttmTimelist", method={RequestMethod.GET})
	public String ttmTimeList(TtmTime ttmTime, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TtmTime> ttmTimes = ttmTimeServiceImpl.select(ttmTime);
//			RespUtil.setResp(ttmTimes, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/ttmTime/ttmTimelist";
	}
	
	@RequestMapping(value="/getttmTimelists", method={RequestMethod.GET})
	@ResponseBody
	public List<TtmTime> ttmTimeLists(TtmTime ttmTime, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TtmTime> ttmTimes = ttmTimeServiceImpl.select(ttmTime);
			return ttmTimes;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String ttmTimeDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TtmTime ttmTime = new TtmTime();
//			 ttmTime.setTtmTimeId(id);
			ttmTimeServiceImpl.delete(ttmTime);
			return "redirect:/ttmTime/ttmTimelist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/ttmTime/ttmTimelist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String ttmTimeSave(TtmTime ttmTime,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(ttmTime.getTtmTimeId()!=null){
//
//				return "redirect:/ttmTime/ttmTimelist.do";
//			}else{
				
				return "redirect:/ttmTime/ttmTimelist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return ttmTimeAdd(model,ttmTime);
		}
	}
	
	public String ttmTimeAdd(Model model,TtmTime ttmTime){
			model.addAttribute("ttmTime", ttmTime);
			return "/ttmTime/ttmTimeform";
	}
	
	@RequestMapping("/add")
	public ModelAndView ttmTimeAdd(){
		ModelAndView mav = new ModelAndView("/ttmTime/ttmTimeform","ttmTime", new TtmTime());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String ttmTimeUpdate(@PathVariable Integer id, Model model){
		try
		{
			TtmTime ttmTime = new TtmTime();
//			ttmTime.setTtmTimeId(id);
			ttmTime = ttmTimeServiceImpl.getById(ttmTime);
			return ttmTimeAdd(model,ttmTime);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/ttmTime/ttmTimelist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView ttmTimeInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/ttmTime/ttmTimeview");
		TtmTime ttmTime = new TtmTime();
//		ttmTime.setTtmTimeId(id);
		ttmTime = ttmTimeServiceImpl.getById(ttmTime);
		mav.addObject("ttmTime", ttmTime);
		return mav;
	}
	
	public TtmTimeServiceImpl getTtmTimeServiceImpl() {
		return ttmTimeServiceImpl;
	}

	public void setTtmTimeServiceImpl(TtmTimeServiceImpl ttmTimeServiceImpl) {
		this.ttmTimeServiceImpl = ttmTimeServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
