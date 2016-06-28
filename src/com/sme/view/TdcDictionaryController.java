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

import com.sme.service.impl.TdcDictionaryServiceImpl;
import com.sme.entity.TdcDictionary;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TdcDictionary")
public class TdcDictionaryController extends BaseController<TdcDictionary> {
	@Autowired
	private TdcDictionaryServiceImpl tdcDictionaryServiceImpl;
	
	private Log log = LogFactory.getLog(TdcDictionaryController.class);
	
	@RequestMapping(value="/tdcDictionarylist", method={RequestMethod.GET})
	public String tdcDictionaryList() {
		return "/sys/dictionarylist";
	}
	
	@RequestMapping(value="/gettdcDictionarylists", method={RequestMethod.GET})
	@ResponseBody
	public List<TdcDictionary> tdcDictionaryLists(TdcDictionary tdcDictionary, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TdcDictionary> tdcDictionarys = tdcDictionaryServiceImpl.select(tdcDictionary);
			return tdcDictionarys;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tdcDictionaryDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TdcDictionary tdcDictionary = new TdcDictionary();
//			 tdcDictionary.setTdcDictionaryId(id);
			tdcDictionaryServiceImpl.delete(tdcDictionary);
			return "redirect:/tdcDictionary/tdcDictionarylist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tdcDictionary/tdcDictionarylist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String tdcDictionarySave(TdcDictionary tdcDictionary,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tdcDictionary.getTdcDictionaryId()!=null){
//
//				return "redirect:/tdcDictionary/tdcDictionarylist.do";
//			}else{
				
				return "redirect:/tdcDictionary/tdcDictionarylist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tdcDictionaryAdd(model,tdcDictionary);
		}
	}
	
	public String tdcDictionaryAdd(Model model,TdcDictionary tdcDictionary){
			model.addAttribute("tdcDictionary", tdcDictionary);
			return "/tdcDictionary/tdcDictionaryform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tdcDictionaryAdd(){
		ModelAndView mav = new ModelAndView("/tdcDictionary/tdcDictionaryform","tdcDictionary", new TdcDictionary());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tdcDictionaryUpdate(@PathVariable Integer id, Model model){
		try
		{
			TdcDictionary tdcDictionary = new TdcDictionary();
//			tdcDictionary.setTdcDictionaryId(id);
			tdcDictionary = tdcDictionaryServiceImpl.getById(tdcDictionary);
			return tdcDictionaryAdd(model,tdcDictionary);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tdcDictionary/tdcDictionarylist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tdcDictionaryInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tdcDictionary/tdcDictionaryview");
		TdcDictionary tdcDictionary = new TdcDictionary();
//		tdcDictionary.setTdcDictionaryId(id);
		tdcDictionary = tdcDictionaryServiceImpl.getById(tdcDictionary);
		mav.addObject("tdcDictionary", tdcDictionary);
		return mav;
	}
	
	public TdcDictionaryServiceImpl getTdcDictionaryServiceImpl() {
		return tdcDictionaryServiceImpl;
	}

	public void setTdcDictionaryServiceImpl(TdcDictionaryServiceImpl tdcDictionaryServiceImpl) {
		this.tdcDictionaryServiceImpl = tdcDictionaryServiceImpl;
	}

	@Override
	public InterfaceBaseService<TdcDictionary> getService() {
		return tdcDictionaryServiceImpl;
	}

	//================== begin ======================
 
	//================== end ======================
}
