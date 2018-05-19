package com.sme.view;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.FruitItem;
import com.sme.service.FruitItemService;
import com.sme.util.RespUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fruitItem")
public class FruitItemController extends BaseController<FruitItem> {
	@Autowired
	private FruitItemService fruitItemServiceImpl;
	
	private Log log = LogFactory.getLog(FruitItemController.class);
	
	@RequestMapping(value="/fruitItemlist", method={RequestMethod.GET})
	public String fruitItemList(FruitItem fruitItem, HttpServletRequest req) {
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

	@RequestMapping(value = "/pageForSearch", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> pageForSearch(HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
		String title = req.getParameter("title");

		try {
			log.info("<=====pageForSearch====>");

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("title", title);

			int count = getService().count(parm);
			List<FruitItem> fruitItems = getService().page(parm);
			return RespUtil.pageResult(count, fruitItems);
		} catch (Exception e) {
			e.printStackTrace();
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

	@RequestMapping(value="/fruitItemDelete", method={RequestMethod.POST})
	@ResponseBody
	public StringJSON fruitItemDelete(String ids){
		try
		{
			if(ids != null && ids.length() > 0){
				String[] idStrings = ids.split(",");

				for (String id : idStrings)
				{
					FruitItem fruitItem= new FruitItem();
					fruitItem.setId(Integer.valueOf(id));
					fruitItemServiceImpl.delete(fruitItem);
				}

				return getSuccess(true, "删除成功！", null);
			}else{
				return getSuccess(false, "删除内容为空！", null);
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			return getSuccess(false, "系统异常！", null);
		}
	}
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	@ResponseBody
	public StringJSON fruitItemSave(FruitItem fruitItem, Model model, HttpServletRequest request, HttpServletResponse response){

		try {
			if (fruitItem.getId() != null) {

				FruitItem fruitItem1 = new FruitItem();

				fruitItem1 = fruitItemServiceImpl.getById(fruitItem);
				fruitItem1.setId(fruitItem.getId());
				fruitItem1.setUpdateTime(new Date());
				fruitItem1.setTitle(fruitItem.getTitle());
				fruitItem1.setItemStock(fruitItem.getItemStock());
				fruitItem1.setItemSaleInfo(fruitItem.getItemSaleInfo());
				fruitItem1.setItemPrice(fruitItem.getItemPrice());
				fruitItem1.setItemSaleType(fruitItem.getItemSaleType());
				fruitItem1.setItemPic(fruitItem.getItemPic());

				fruitItemServiceImpl.update(fruitItem1);

				return getSuccess(true, "修改成功", null);
			} else {

				fruitItem.setUpdateTime(new Date());
				fruitItem.setCreateTime(new Date());
				fruitItem.setAdminId(1);
				fruitItem.setIsDelete(1);
				fruitItem.setItemType(1);
				fruitItem.setItemStatus(1);

				fruitItemServiceImpl.insert(fruitItem);

				return getSuccess(true, "添加成功", null);
			}

		} catch (Exception e) {
			log.error(e.getCause().getMessage());
			System.out.println(e.getCause().getMessage());
			return getSuccess(false, "发生系统异常");
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
