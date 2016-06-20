package com.sme.view;

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

import com.sme.service.impl.PTerminalInfoServiceImpl;
import com.sme.entity.PTerminalInfo;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/PTerminalInfo")
public class PTerminalInfoController {
	@Autowired
	private PTerminalInfoServiceImpl pTerminalInfoServiceImpl;
	
	private Log log = LogFactory.getLog(PTerminalInfoController.class);
	
	@RequestMapping(value="/pTerminalInfolist", method={RequestMethod.GET})
	public String pTerminalInfoList(PTerminalInfo pTerminalInfo, HttpServletRequest req) {
		//分页属性
		String pageSize = req.getParameter("pageSize");
		String pageNo = req.getParameter("pageNo");
		try {
			log.info("<=====执行sysmenulist====>");
			
			Map<String, Object> parm = new HashMap<String, Object>();
			parm = RespUtil.changePage(pageSize, pageNo, parm);
			int count = pTerminalInfoServiceImpl.count(parm);
			List<PTerminalInfo> pTerminalInfos = pTerminalInfoServiceImpl.page(parm);
			RespUtil.setResp(pTerminalInfos, count, pageSize, pageNo, req);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/pTerminalInfo/pTerminalInfolist";
	}
	
	@RequestMapping(value="/getpTerminalInfolists", method={RequestMethod.GET})
	@ResponseBody
	public List<PTerminalInfo> pTerminalInfoLists(PTerminalInfo pTerminalInfo, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<PTerminalInfo> pTerminalInfos = pTerminalInfoServiceImpl.select(pTerminalInfo);
			return pTerminalInfos;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String pTerminalInfoDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 PTerminalInfo pTerminalInfo = new PTerminalInfo();
			 pTerminalInfo.setpTerminfoId(id);
			pTerminalInfoServiceImpl.delete(pTerminalInfo);
			return "redirect:/pTerminalInfo/pTerminalInfolist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/pTerminalInfo/pTerminalInfolist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String pTerminalInfoSave(PTerminalInfo pTerminalInfo,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
			if(pTerminalInfo.getpTerminfoId()!=null){
				
				return "redirect:/pTerminalInfo/pTerminalInfolist.do";
			}else{
				
				return "redirect:/pTerminalInfo/pTerminalInfolist.do";
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return pTerminalInfoAdd(model,pTerminalInfo);
		}
	}
	
	public String pTerminalInfoAdd(Model model,PTerminalInfo pTerminalInfo){
			model.addAttribute("pTerminalInfo", pTerminalInfo);
			return "/pTerminalInfo/pTerminalInfoform";
	}
	
	@RequestMapping("/add")
	public ModelAndView pTerminalInfoAdd(){
		ModelAndView mav = new ModelAndView("/pTerminalInfo/pTerminalInfoform","pTerminalInfo", new PTerminalInfo());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String pTerminalInfoUpdate(@PathVariable Integer id, Model model){
		try
		{
			PTerminalInfo pTerminalInfo = new PTerminalInfo();
			pTerminalInfo.setpTerminfoId(id);
			pTerminalInfo = pTerminalInfoServiceImpl.getById(pTerminalInfo);
			return pTerminalInfoAdd(model,pTerminalInfo);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/pTerminalInfo/pTerminalInfolist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView pTerminalInfoInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/pTerminalInfo/pTerminalInfoview");
		PTerminalInfo pTerminalInfo = new PTerminalInfo();
		pTerminalInfo.setpTerminfoId(id);
		pTerminalInfo = pTerminalInfoServiceImpl.getById(pTerminalInfo);
		mav.addObject("pTerminalInfo", pTerminalInfo);
		return mav;
	}
	
	public PTerminalInfoServiceImpl getPTerminalInfoServiceImpl() {
		return pTerminalInfoServiceImpl;
	}

	public void setPTerminalInfoServiceImpl(PTerminalInfoServiceImpl pTerminalInfoServiceImpl) {
		this.pTerminalInfoServiceImpl = pTerminalInfoServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
