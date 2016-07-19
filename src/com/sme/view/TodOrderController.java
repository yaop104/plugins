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

import com.sme.service.impl.TodOrderServiceImpl;
import com.sme.entity.TodOrder;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TodOrder")
public class TodOrderController {
	@Autowired
	private TodOrderServiceImpl todOrderServiceImpl;
	
	private Log log = LogFactory.getLog(TodOrderController.class);
	
	@RequestMapping(value="/todOrderlist", method={RequestMethod.GET})
	public String todOrderList(TodOrder todOrder, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TodOrder> todOrders = todOrderServiceImpl.select(todOrder);
//			RespUtil.setResp(todOrders, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/todOrder/todOrderlist";
	}
	
	@RequestMapping(value="/gettodOrderlists", method={RequestMethod.GET})
	@ResponseBody
	public List<TodOrder> todOrderLists(TodOrder todOrder, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TodOrder> todOrders = todOrderServiceImpl.select(todOrder);
			return todOrders;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String todOrderDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TodOrder todOrder = new TodOrder();
//			 todOrder.setTodOrderId(id);
			todOrderServiceImpl.delete(todOrder);
			return "redirect:/todOrder/todOrderlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/todOrder/todOrderlist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String todOrderSave(TodOrder todOrder,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(todOrder.getTodOrderId()!=null){
//
//				return "redirect:/todOrder/todOrderlist.do";
//			}else{
				
				return "redirect:/todOrder/todOrderlist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return todOrderAdd(model,todOrder);
		}
	}
	
	public String todOrderAdd(Model model,TodOrder todOrder){
			model.addAttribute("todOrder", todOrder);
			return "/todOrder/todOrderform";
	}
	
	@RequestMapping("/add")
	public ModelAndView todOrderAdd(){
		ModelAndView mav = new ModelAndView("/todOrder/todOrderform","todOrder", new TodOrder());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String todOrderUpdate(@PathVariable Integer id, Model model){
		try
		{
			TodOrder todOrder = new TodOrder();
//			todOrder.setTodOrderId(id);
			todOrder = todOrderServiceImpl.getById(todOrder);
			return todOrderAdd(model,todOrder);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/todOrder/todOrderlist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView todOrderInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/todOrder/todOrderview");
		TodOrder todOrder = new TodOrder();
//		todOrder.setTodOrderId(id);
		todOrder = todOrderServiceImpl.getById(todOrder);
		mav.addObject("todOrder", todOrder);
		return mav;
	}
	
	public TodOrderServiceImpl getTodOrderServiceImpl() {
		return todOrderServiceImpl;
	}

	public void setTodOrderServiceImpl(TodOrderServiceImpl todOrderServiceImpl) {
		this.todOrderServiceImpl = todOrderServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
