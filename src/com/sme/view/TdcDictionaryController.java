package com.sme.view;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sme.core.model.StringJSON;
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

	@RequestMapping(value = "/deleteDictionary")
	@ResponseBody
	@com.sme.core.spring.Log(type = "字典管理", desc = "删除字典")
	public StringJSON tdcDictionaryDelete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					TdcDictionary tdcDictionary = new TdcDictionary();
					tdcDictionary.setTdcDictionaryUnid(Integer.valueOf(id));
					tdcDictionaryServiceImpl.delete(tdcDictionary);
				}

				return getSuccess(true, "删除成功！");
			} else {
				return getSuccess(false, "删除内容为空！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return getSuccess(false, "系统异常！");
		}
	}

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	@ResponseBody
	@com.sme.core.spring.Log(type = "字典管理", desc = "修改字典")
	public StringJSON tdcDictionarySave(TdcDictionary tdcDictionary, Model model, HttpServletRequest request, HttpServletResponse response){
		try {
			if (tdcDictionary.getTdcDictionaryUnid() != null) {


	//			TbcInfo tbcInfo1 = new TbcInfo();
	//			tbcInfo1 = tbcInfoServiceImpl.getById(tbcInfo);
	//			tbcInfo1.setTioName(tbcInfo.getTioName());
	//			tbcInfo1.setTioContactname(tbcInfo.getTioContactname());
	//			tbcInfo1.setTioContactphone(tbcInfo.getTioContactphone());
	//			tbcInfo1.setTioDesc(tbcInfo.getTioDesc());
	//			tbcInfo1.setTioState(tbcInfo.getTioState());
	//			tbcInfo1.setTioType(tbcInfo.getTioType());
	//			tbcInfo1.setTioUdate(new Date());
	//			tbcInfo1.setTioUuser("admin2");
	//
	//			tbcInfoServiceImpl.update(tbcInfo1);
				TdcDictionary models = new TdcDictionary();
				models.setTdcDictionaryName(tdcDictionary.getTdcDictionaryName());
				models.setTdcDictionaryUnid(tdcDictionary.getTdcDictionaryUnid());
				if(checkIsExists(models)){
					return getSuccess(false, "该分类名称已存在,请输入未使用名称！！");
				}

				TdcDictionary amodels = new TdcDictionary();
				amodels = tdcDictionaryServiceImpl.getById(tdcDictionary);
				amodels.setTdcDictionaryName(tdcDictionary.getTdcDictionaryName());
				amodels.setTdcDictionaryDesc(tdcDictionary.getTdcDictionaryDesc());
				amodels.setTdcDictionaryState(tdcDictionary.getTdcDictionaryState());
				amodels.setTdcDictionaryType(tdcDictionary.getTdcDictionaryType());
				amodels.setTdcDictionaryParentid(tdcDictionary.getTdcDictionaryParentid());
				amodels.setTdcDictionaryUdate(new Date());
				amodels.setTdcDictionaryUuser(1);

				tdcDictionaryServiceImpl.update(amodels);
				return getSuccess(true, "修改成功");
			} else {

				TdcDictionary models = new TdcDictionary();
				models.setTdcDictionaryName(tdcDictionary.getTdcDictionaryName());
				if(checkIsExists(models)){
					return getSuccess(false, "该分类名称已存在,请输入未使用名称！！");
				}

				tdcDictionary.setTdcDictionaryState("1");
				tdcDictionary.setTdcDictionaryCdate(new Date());
				tdcDictionary.setTdcDictionaryCuser(1);

				tdcDictionaryServiceImpl.insert(tdcDictionary);
				return getSuccess(true, "添加成功");
			}

		} catch (Exception e) {
			log.error(e.getCause().getMessage());
			return getSuccess(false, "发生系统异常");
		}
	}

	private boolean checkIsExists(TdcDictionary tdcDictionary) {
		Boolean flag = tdcDictionaryServiceImpl.getDictionary(tdcDictionary);
		return flag;
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
