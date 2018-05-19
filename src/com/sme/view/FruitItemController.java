package com.sme.view;

import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.FruitItem;
import com.sme.service.FruitItemService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/FruitItem")
public class FruitItemController extends BaseController<FruitItem> {
	@Autowired
	private FruitItemService fruitItemServiceImpl;
	
	private Log log = LogFactory.getLog(FruitItemController.class);
	
	@RequestMapping(value="/fruitItemlist", method={RequestMethod.GET})
	public String fruitItemList(FruitItem fruitItem, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<FruitItem> fruitItems = fruitItemServiceImpl.select(fruitItem);
//			RespUtil.setResp(fruitItems, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/fruitItem/fruitItemlist";
	}
	
	@RequestMapping(value="/getfruitItemlists", method={RequestMethod.GET})
	@ResponseBody
	public List<FruitItem> fruitItemLists(FruitItem fruitItem, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<FruitItem> fruitItems = fruitItemServiceImpl.select(fruitItem);
			return fruitItems;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String fruitItemDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 FruitItem fruitItem = new FruitItem();
			 fruitItem.setId(id);
			fruitItemServiceImpl.delete(fruitItem);
			return "redirect:/fruitItem/fruitItemlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/fruitItem/fruitItemlist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String fruitItemSave(FruitItem fruitItem, Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
			if(fruitItem.getId()!=null){
				
				return "redirect:/fruitItem/fruitItemlist.do";
			}else{
				
				return "redirect:/fruitItem/fruitItemlist.do";
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return fruitItemAdd(model,fruitItem);
		}
	}
	
	public String fruitItemAdd(Model model, FruitItem fruitItem){
			model.addAttribute("fruitItem", fruitItem);
			return "/fruitItem/fruitItemform";
	}
	
	@RequestMapping("/add")
	public ModelAndView fruitItemAdd(){
		ModelAndView mav = new ModelAndView("/fruitItem/fruitItemform","fruitItem", new FruitItem());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String fruitItemUpdate(@PathVariable Integer id, Model model){
		try
		{
			FruitItem fruitItem = new FruitItem();
			fruitItem.setId(id);
			fruitItem = fruitItemServiceImpl.getById(fruitItem);
			return fruitItemAdd(model,fruitItem);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/fruitItem/fruitItemlist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView fruitItemInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/fruitItem/fruitItemview");
		FruitItem fruitItem = new FruitItem();
		fruitItem.setId(id);
		fruitItem = fruitItemServiceImpl.getById(fruitItem);
		mav.addObject("fruitItem", fruitItem);
		return mav;
	}
	
	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return fruitItemServiceImpl;
	}
	
	public FruitItemService getFruitItemServiceImpl() {
		return fruitItemServiceImpl;
	}

	public void setFruitItemServiceImpl(FruitItemService fruitItemServiceImpl) {
		this.fruitItemServiceImpl = fruitItemServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
