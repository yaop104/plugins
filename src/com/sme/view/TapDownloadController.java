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

import com.sme.service.impl.TapDownloadServiceImpl;
import com.sme.entity.TapDownload;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TapDownload")
public class TapDownloadController extends BaseController<TapDownload>{
	@Autowired
	private TapDownloadServiceImpl tapDownloadServiceImpl;
	
	private Log log = LogFactory.getLog(TapDownloadController.class);
	
	@RequestMapping(value="/tapDownloadlist", method={RequestMethod.GET})
	public String tapDownloadList(TapDownload tapDownload, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TapDownload> tapDownloads = tapDownloadServiceImpl.select(tapDownload);
//			RespUtil.setResp(tapDownloads, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/tapDownload/tapDownloadlist";
	}
	
	@RequestMapping(value="/gettapDownloadlists", method={RequestMethod.GET})
	@ResponseBody
	public List<TapDownload> tapDownloadLists(TapDownload tapDownload, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TapDownload> tapDownloads = tapDownloadServiceImpl.select(tapDownload);
			return tapDownloads;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tapDownloadDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TapDownload tapDownload = new TapDownload();
//			 tapDownload.setTapDownloadId(id);
			tapDownloadServiceImpl.delete(tapDownload);
			return "redirect:/tapDownload/tapDownloadlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tapDownload/tapDownloadlist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String tapDownloadSave(TapDownload tapDownload,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tapDownload.getTapDownloadId()!=null){
				
//				return "redirect:/tapDownload/tapDownloadlist.do";
//			}else{
				
				return "redirect:/tapDownload/tapDownloadlist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tapDownloadAdd(model,tapDownload);
		}
	}
	
	public String tapDownloadAdd(Model model,TapDownload tapDownload){
			model.addAttribute("tapDownload", tapDownload);
			return "/tapDownload/tapDownloadform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tapDownloadAdd(){
		ModelAndView mav = new ModelAndView("/tapDownload/tapDownloadform","tapDownload", new TapDownload());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tapDownloadUpdate(@PathVariable Integer id, Model model){
		try
		{
			TapDownload tapDownload = new TapDownload();
//			tapDownload.setTapDownloadId(id);
			tapDownload = tapDownloadServiceImpl.getById(tapDownload);
			return tapDownloadAdd(model,tapDownload);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tapDownload/tapDownloadlist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tapDownloadInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tapDownload/tapDownloadview");
		TapDownload tapDownload = new TapDownload();
//		tapDownload.setTapDownloadId(id);
		tapDownload = tapDownloadServiceImpl.getById(tapDownload);
		mav.addObject("tapDownload", tapDownload);
		return mav;
	}
	
	public TapDownloadServiceImpl getTapDownloadServiceImpl() {
		return tapDownloadServiceImpl;
	}

	public void setTapDownloadServiceImpl(TapDownloadServiceImpl tapDownloadServiceImpl) {
		this.tapDownloadServiceImpl = tapDownloadServiceImpl;
	}

	@Override
	public InterfaceBaseService<TapDownload> getService() {
		return tapDownloadServiceImpl;
	}

	//================== begin ======================
 
	//================== end ======================
}
