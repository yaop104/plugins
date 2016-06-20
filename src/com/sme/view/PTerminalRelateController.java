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

import com.sme.service.impl.PTerminalRelateServiceImpl;
import com.sme.entity.PTerminalRelate;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/PTerminalRelate")
public class PTerminalRelateController {
	@Autowired
	private PTerminalRelateServiceImpl pTerminalRelateServiceImpl;
	
	private Log log = LogFactory.getLog(PTerminalRelateController.class);
	
	@RequestMapping(value="/pTerminalRelatelist", method={RequestMethod.GET})
	public String pTerminalRelateList(PTerminalRelate pTerminalRelate, HttpServletRequest req) {
		//分页属性
		String pageSize = req.getParameter("pageSize");
		String pageNo = req.getParameter("pageNo");
		try {
			log.info("<=====执行sysmenulist====>");
			
			Map<String, Object> parm = new HashMap<String, Object>();
			parm = RespUtil.changePage(pageSize, pageNo, parm);
			int count = pTerminalRelateServiceImpl.count(parm);
			List<PTerminalRelate> pTerminalRelates = pTerminalRelateServiceImpl.page(parm);
			RespUtil.setResp(pTerminalRelates, count, pageSize, pageNo, req);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/pTerminalRelate/pTerminalRelatelist";
	}
	
	@RequestMapping(value="/getpTerminalRelatelists", method={RequestMethod.GET})
	@ResponseBody
	public List<PTerminalRelate> pTerminalRelateLists(PTerminalRelate pTerminalRelate, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<PTerminalRelate> pTerminalRelates = pTerminalRelateServiceImpl.select(pTerminalRelate);
			return pTerminalRelates;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String pTerminalRelateDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 PTerminalRelate pTerminalRelate = new PTerminalRelate();
			 pTerminalRelate.setpTermrelateId(id);
			pTerminalRelateServiceImpl.delete(pTerminalRelate);
			return "redirect:/pTerminalRelate/pTerminalRelatelist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/pTerminalRelate/pTerminalRelatelist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String pTerminalRelateSave(PTerminalRelate pTerminalRelate,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
			if(pTerminalRelate.getpTermrelateId()!=null){
				
				return "redirect:/pTerminalRelate/pTerminalRelatelist.do";
			}else{
				
				return "redirect:/pTerminalRelate/pTerminalRelatelist.do";
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return pTerminalRelateAdd(model,pTerminalRelate);
		}
	}
	
	public String pTerminalRelateAdd(Model model,PTerminalRelate pTerminalRelate){
			model.addAttribute("pTerminalRelate", pTerminalRelate);
			return "/pTerminalRelate/pTerminalRelateform";
	}
	
	@RequestMapping("/add")
	public ModelAndView pTerminalRelateAdd(){
		ModelAndView mav = new ModelAndView("/pTerminalRelate/pTerminalRelateform","pTerminalRelate", new PTerminalRelate());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String pTerminalRelateUpdate(@PathVariable Integer id, Model model){
		try
		{
			PTerminalRelate pTerminalRelate = new PTerminalRelate();
			pTerminalRelate.setpTermrelateId(id);
			pTerminalRelate = pTerminalRelateServiceImpl.getById(pTerminalRelate);
			return pTerminalRelateAdd(model,pTerminalRelate);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/pTerminalRelate/pTerminalRelatelist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView pTerminalRelateInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/pTerminalRelate/pTerminalRelateview");
		PTerminalRelate pTerminalRelate = new PTerminalRelate();
		pTerminalRelate.setpTermrelateId(id);
		pTerminalRelate = pTerminalRelateServiceImpl.getById(pTerminalRelate);
		mav.addObject("pTerminalRelate", pTerminalRelate);
		return mav;
	}
	
	public PTerminalRelateServiceImpl getPTerminalRelateServiceImpl() {
		return pTerminalRelateServiceImpl;
	}

	public void setPTerminalRelateServiceImpl(PTerminalRelateServiceImpl pTerminalRelateServiceImpl) {
		this.pTerminalRelateServiceImpl = pTerminalRelateServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
