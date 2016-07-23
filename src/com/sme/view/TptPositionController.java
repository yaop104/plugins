package com.sme.view;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.TbcInfo;
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
public class TptPositionController extends BaseController<TptPosition> {
	@Autowired
	private TptPositionServiceImpl tptPositionServiceImpl;
	
	private Log log = LogFactory.getLog(TptPositionController.class);
	
	@RequestMapping(value="/tptPositionlist", method={RequestMethod.GET})
	public String tptPositionList(TptPosition tptPosition, HttpServletRequest req) {
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

	@RequestMapping(value = "/deleteTptPositions")
	@ResponseBody
	@com.sme.core.spring.Log(type = "运营商管理", desc = "删除运营商")
	public StringJSON tptPositionDelete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					TptPosition tptPosition = new TptPosition();
					 tptPosition.setTptUnid(Integer.valueOf(id));
					tptPositionServiceImpl.delete(tptPosition);
				}

				return getSuccess(true, "删除成功！", null);
			} else {
				return getSuccess(false, "删除内容为空！", null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return getSuccess(false, "系统异常！", null);
		}
	}


	@RequestMapping(value="/save", method={RequestMethod.POST})
	@ResponseBody
	@com.sme.core.spring.Log(type = "运营商管理", desc = "修改运营商")
	public StringJSON tptPositionSave(TptPosition tptPosition, Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
			if(tptPosition.getTptUnid()!=null){
				TptPosition tptPosition1 = new TptPosition();
				tptPosition1.setTptUnid(tptPosition.getTptUnid());
				tptPosition1 = tptPositionServiceImpl.getById(tptPosition1);
				tptPosition1.setTptDemourl(tptPosition.getTptDemourl());
				tptPosition1.setTptDesc(tptPosition.getTptDesc());
				tptPosition1.setTptName(tptPosition.getTptName());
				tptPosition1.setTptPrice(tptPosition.getTptPrice());
				tptPosition1.setTptState(tptPosition.getTptState());
				return getSuccess(true, "修改成功");
			} else {

				tptPosition.setTptCdate(new Date());
				tptPosition.setTptCuser("admin");
				tptPositionServiceImpl.insert(tptPosition);

				return getSuccess(true, "添加成功");
			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return getSuccess(false, "发生系统异常");
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

	@Override
	public InterfaceBaseService<TptPosition> getService() {
		return tptPositionServiceImpl;
	}

	//================== begin ======================
 
	//================== end ======================
}
