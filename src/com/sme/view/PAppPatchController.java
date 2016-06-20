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

import com.sme.service.impl.PAppPatchServiceImpl;
import com.sme.entity.PAppPatch;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/PAppPatch")
public class PAppPatchController {
	@Autowired
	private PAppPatchServiceImpl pAppPatchServiceImpl;
	
	private Log log = LogFactory.getLog(PAppPatchController.class);
	
	@RequestMapping(value="/pAppPatchlist", method={RequestMethod.GET})
	public String pAppPatchList(PAppPatch pAppPatch, HttpServletRequest req) {
		//分页属性
		String pageSize = req.getParameter("pageSize");
		String pageNo = req.getParameter("pageNo");
		try {
			log.info("<=====执行sysmenulist====>");
			
			Map<String, Object> parm = new HashMap<String, Object>();
			parm = RespUtil.changePage(pageSize, pageNo, parm);
			int count = pAppPatchServiceImpl.count(parm);
			List<PAppPatch> pAppPatchs = pAppPatchServiceImpl.page(parm);
			RespUtil.setResp(pAppPatchs, count, pageSize, pageNo, req);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/pAppPatch/pAppPatchlist";
	}
	
	@RequestMapping(value="/getpAppPatchlists", method={RequestMethod.GET})
	@ResponseBody
	public List<PAppPatch> pAppPatchLists(PAppPatch pAppPatch, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<PAppPatch> pAppPatchs = pAppPatchServiceImpl.select(pAppPatch);
			return pAppPatchs;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String pAppPatchDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 PAppPatch pAppPatch = new PAppPatch();
			 pAppPatch.setpPatchId(id);
			pAppPatchServiceImpl.delete(pAppPatch);
			return "redirect:/pAppPatch/pAppPatchlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/pAppPatch/pAppPatchlist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String pAppPatchSave(PAppPatch pAppPatch,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
			if(pAppPatch.getpPatchId()!=null){
				
				return "redirect:/pAppPatch/pAppPatchlist.do";
			}else{
				
				return "redirect:/pAppPatch/pAppPatchlist.do";
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return pAppPatchAdd(model,pAppPatch);
		}
	}
	
	public String pAppPatchAdd(Model model,PAppPatch pAppPatch){
			model.addAttribute("pAppPatch", pAppPatch);
			return "/pAppPatch/pAppPatchform";
	}
	
	@RequestMapping("/add")
	public ModelAndView pAppPatchAdd(){
		ModelAndView mav = new ModelAndView("/pAppPatch/pAppPatchform","pAppPatch", new PAppPatch());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String pAppPatchUpdate(@PathVariable Integer id, Model model){
		try
		{
			PAppPatch pAppPatch = new PAppPatch();
			pAppPatch.setpPatchId(id);
			pAppPatch = pAppPatchServiceImpl.getById(pAppPatch);
			return pAppPatchAdd(model,pAppPatch);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/pAppPatch/pAppPatchlist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView pAppPatchInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/pAppPatch/pAppPatchview");
		PAppPatch pAppPatch = new PAppPatch();
		pAppPatch.setpPatchId(id);
		pAppPatch = pAppPatchServiceImpl.getById(pAppPatch);
		mav.addObject("pAppPatch", pAppPatch);
		return mav;
	}
	
	public PAppPatchServiceImpl getPAppPatchServiceImpl() {
		return pAppPatchServiceImpl;
	}

	public void setPAppPatchServiceImpl(PAppPatchServiceImpl pAppPatchServiceImpl) {
		this.pAppPatchServiceImpl = pAppPatchServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
