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

import com.sme.service.impl.TptPositionServiceImpl;
import com.sme.entity.TptPosition;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TptPosition")
public class TptPositionController {
	@Autowired
	private TptPositionServiceImpl tptPositionServiceImpl;
	
	private Log log = LogFactory.getLog(TptPositionController.class);
	
	@RequestMapping(value="/tptPositionlist", method={RequestMethod.GET})
	public String tptPositionList(TptPosition tptPosition, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TptPosition> tptPositions = tptPositionServiceImpl.select(tptPosition);
//			RespUtil.setResp(tptPositions, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/tptPosition/tptPositionlist";
	}
	
	@RequestMapping(value="/gettptPositionlists", method={RequestMethod.GET})
	@ResponseBody
	public List<TptPosition> tptPositionLists(TptPosition tptPosition, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TptPosition> tptPositions = tptPositionServiceImpl.select(tptPosition);
			return tptPositions;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tptPositionDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TptPosition tptPosition = new TptPosition();
//			 tptPosition.setTptPositionId(id);
			tptPositionServiceImpl.delete(tptPosition);
			return "redirect:/tptPosition/tptPositionlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tptPosition/tptPositionlist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String tptPositionSave(TptPosition tptPosition,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tptPosition.getTptPositionId()!=null){
//
//				return "redirect:/tptPosition/tptPositionlist.do";
//			}else{
				
				return "redirect:/tptPosition/tptPositionlist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tptPositionAdd(model,tptPosition);
		}
	}
	
	public String tptPositionAdd(Model model,TptPosition tptPosition){
			model.addAttribute("tptPosition", tptPosition);
			return "/tptPosition/tptPositionform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tptPositionAdd(){
		ModelAndView mav = new ModelAndView("/tptPosition/tptPositionform","tptPosition", new TptPosition());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tptPositionUpdate(@PathVariable Integer id, Model model){
		try
		{
			TptPosition tptPosition = new TptPosition();
//			tptPosition.setTptPositionId(id);
			tptPosition = tptPositionServiceImpl.getById(tptPosition);
			return tptPositionAdd(model,tptPosition);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tptPosition/tptPositionlist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tptPositionInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tptPosition/tptPositionview");
		TptPosition tptPosition = new TptPosition();
//		tptPosition.setTptPositionId(id);
		tptPosition = tptPositionServiceImpl.getById(tptPosition);
		mav.addObject("tptPosition", tptPosition);
		return mav;
	}
	
	public TptPositionServiceImpl getTptPositionServiceImpl() {
		return tptPositionServiceImpl;
	}

	public void setTptPositionServiceImpl(TptPositionServiceImpl tptPositionServiceImpl) {
		this.tptPositionServiceImpl = tptPositionServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
