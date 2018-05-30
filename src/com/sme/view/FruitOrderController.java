package com.sme.view;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.FruitItem;
import com.sme.entity.FruitOrder;
import com.sme.service.FruitOrderService;
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
@RequestMapping("/fruitOrder")
public class FruitOrderController extends BaseController<FruitOrder> {
	@Autowired
	private FruitOrderService fruitOrderServiceImpl;
	
	private Log log = LogFactory.getLog(FruitOrderController.class);
	
	@RequestMapping(value="/fruitOrderlist", method={RequestMethod.GET})
	public String fruitOrderList(FruitOrder fruitOrder, HttpServletRequest req) {
		return "/fruitOrder/fruitOrderlist";
	}
	
	@RequestMapping(value="/getfruitOrderlists", method={RequestMethod.GET})
	@ResponseBody
	public List<FruitOrder> fruitOrderLists(FruitOrder fruitOrder, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<FruitOrder> fruitOrders = fruitOrderServiceImpl.select(fruitOrder);
			return fruitOrders;
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
		String receiverMobile = req.getParameter("receiverMobile");
		String beginTime = req.getParameter("beginTime");
		String endTime = req.getParameter("endTime");
		String orderStatus = req.getParameter("orderStatus");
		String id = req.getParameter("id");
		try {
			log.info("<=====pageForSearch====>");

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("beginTime", beginTime);
			parm.put("endTime", endTime);
			parm.put("orderStatus", orderStatus);
			parm.put("receiverMobilelike", receiverMobile);
			parm.put("idlike", id);

			int count = getService().count(parm);
			List<FruitItem> fruitItems = getService().page(parm);
			return RespUtil.pageResult(count, fruitItems);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String fruitOrderDeleteOne(@PathVariable String id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 FruitOrder fruitOrder = new FruitOrder();
			 fruitOrder.setId(id);
			fruitOrderServiceImpl.delete(fruitOrder);
			return "redirect:/fruitOrder/fruitOrderlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/fruitOrder/fruitOrderlist.do";
		}
	 }


	@RequestMapping(value="/onItem", method={RequestMethod.POST})
	@ResponseBody
	public StringJSON fruitOrderOnItem(FruitOrder fruitOrderReq){
		try
		{
			log.info("<=====执行发货口====>" + id);
			FruitOrder fruitOrder = new FruitOrder();
			fruitOrder.setId(fruitOrderReq.getId());
			fruitOrder.setOrderStatus(4);
			fruitOrderServiceImpl.update(fruitOrder);
			return getSuccess(true, "成功！", null);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			return getSuccess(false, "系统异常！", null);
		}
	}


	@RequestMapping(value="/fruitOrderDelete", method={RequestMethod.POST})
	@ResponseBody
	public StringJSON fruitOrderDelete(String ids){
		try
		{
			if(ids != null && ids.length() > 0){
				String[] idStrings = ids.split(",");

				for (String id : idStrings)
				{
					FruitOrder fruitOrder = new FruitOrder();
					fruitOrder.setId(id);
					fruitOrder.setHidden(2);
					fruitOrderServiceImpl.update(fruitOrder);
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
	public StringJSON fruitItemSave(FruitOrder fruitOrder, Model model, HttpServletRequest request, HttpServletResponse response){

		try {
			if (fruitOrder.getId() != null) {

				FruitOrder fruitOrder1 = new FruitOrder();

				fruitOrder1 = fruitOrderServiceImpl.getById(fruitOrder);
				fruitOrder1.setUpdateTime(new Date());
				fruitOrder1.setOrderStatus(fruitOrder.getOrderStatus());
				fruitOrder1.setReceiverAddress(fruitOrder.getReceiverAddress());
				fruitOrder1.setReceiverMobile(fruitOrder.getReceiverMobile());
				fruitOrder1.setReceiverName(fruitOrder.getReceiverName());
				fruitOrder1.setHidden(fruitOrder.getHidden());

				fruitOrderServiceImpl.update(fruitOrder1);

				return getSuccess(true, "修改成功", null);
			} else {

//				fruitItem.setUpdateTime(new Date());
//				fruitItem.setCreateTime(new Date());
//				fruitItem.setAdminId(1);
//				fruitItem.setIsDelete(1);
//				fruitItem.setItemType(1);
//				fruitItem.setItemStatus(1);
//
//				fruitItemServiceImpl.insert(fruitItem);

				return getSuccess(true, "添加成功", null);
			}

		} catch (Exception e) {
			log.error(e.getCause().getMessage());
			System.out.println(e.getCause().getMessage());
			return getSuccess(false, "发生系统异常");
		}
	}
	
	public String fruitOrderAdd(Model model, FruitOrder fruitOrder){
			model.addAttribute("fruitOrder", fruitOrder);
			return "/fruitOrder/fruitOrderform";
	}
	
	@RequestMapping("/add")
	public ModelAndView fruitOrderAdd(){
		ModelAndView mav = new ModelAndView("/fruitOrder/fruitOrderform","fruitOrder", new FruitOrder());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String fruitOrderUpdate(@PathVariable String id, Model model){
		try
		{
			FruitOrder fruitOrder = new FruitOrder();
			fruitOrder.setId(id);
			fruitOrder = fruitOrderServiceImpl.getById(fruitOrder);
			return fruitOrderAdd(model,fruitOrder);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/fruitOrder/fruitOrderlist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView fruitOrderInfo(@PathVariable String id){
		ModelAndView mav = new ModelAndView("/fruitOrder/fruitOrderview");
		FruitOrder fruitOrder = new FruitOrder();
		fruitOrder.setId(id);
		fruitOrder = fruitOrderServiceImpl.getById(fruitOrder);
		mav.addObject("fruitOrder", fruitOrder);
		return mav;
	}
	
	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return fruitOrderServiceImpl;
	}
	
	public FruitOrderService getFruitOrderServiceImpl() {
		return fruitOrderServiceImpl;
	}

	public void setFruitOrderServiceImpl(FruitOrderService fruitOrderServiceImpl) {
		this.fruitOrderServiceImpl = fruitOrderServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
