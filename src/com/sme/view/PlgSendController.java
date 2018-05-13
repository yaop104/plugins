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

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.PlgSend;
import com.sme.entity.SysAcc;
import com.sme.service.PlgSendService;

@Controller
@RequestMapping("/PlgSend")
public class PlgSendController extends BaseController<PlgSend>{
	@Autowired
	private PlgSendService plgSendServiceImpl;
	
	private Log log = LogFactory.getLog(PlgSendController.class);
	
	
	@RequestMapping(value="/getplgSendlists", method={RequestMethod.GET})
	@ResponseBody
	public List<PlgSend> plgSendLists(PlgSend plgSend, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<PlgSend> plgSends = plgSendServiceImpl.select(plgSend);
			return plgSends;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String plgSendDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 PlgSend plgSend = new PlgSend();
//			 plgSend.setPlgSendId(id);
			plgSendServiceImpl.delete(plgSend);
			return "redirect:/plgSend/plgSendlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/plgSend/plgSendlist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	@ResponseBody
	public StringJSON plgSendSave(PlgSend plgSend, HttpServletRequest request){
		SysAcc session = (SysAcc) request.getSession().getAttribute("loginUser");
		try
		{
			if(plgSend.getPsdUnid()!=null){
				
				return getSuccess(true, "修改成功");
			}else{
				
				//增加session信息
				plgSend.setPsdCuser(session.getSysAccName());
				plgSend.setPsdCdate(new Date());
				
				//根据类型插入发送任务
				Boolean flag = plgSendServiceImpl.insertForType(plgSend);
				
				if(flag){
				
					return getSuccess(true, "添加成功");
				}else{
					
					return getSuccess(false, "添加失败");
				}
				
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return getSuccess(false, "系统异常");
		}
	}
	
	public String plgSendAdd(Model model,PlgSend plgSend){
			model.addAttribute("plgSend", plgSend);
			return "/plgSend/plgSendform";
	}
	
	@RequestMapping("/add")
	public ModelAndView plgSendAdd(){
		ModelAndView mav = new ModelAndView("/plgSend/plgSendform","plgSend", new PlgSend());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String plgSendUpdate(@PathVariable Integer id, Model model){
		try
		{
			PlgSend plgSend = new PlgSend();
//			plgSend.setPlgSendId(id);
			plgSend = plgSendServiceImpl.getById(plgSend);
			return plgSendAdd(model,plgSend);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/plgSend/plgSendlist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView plgSendInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/plgSend/plgSendview");
		PlgSend plgSend = new PlgSend();
//		plgSend.setPlgSendId(id);
		plgSend = plgSendServiceImpl.getById(plgSend);
		mav.addObject("plgSend", plgSend);
		return mav;
	}
	
	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return plgSendServiceImpl;
	}
	
	public PlgSendService getPlgSendServiceImpl() {
		return plgSendServiceImpl;
	}

	public void setPlgSendServiceImpl(PlgSendService plgSendServiceImpl) {
		this.plgSendServiceImpl = plgSendServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
