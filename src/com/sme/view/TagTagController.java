package com.sme.view;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.TdcDictionary;
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

import com.sme.service.impl.TagTagServiceImpl;
import com.sme.entity.TagTag;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TagTag")
public class TagTagController extends BaseController<TagTag>{
	@Autowired
	private TagTagServiceImpl tagTagServiceImpl;
	
	private Log log = LogFactory.getLog(TagTagController.class);

	@RequestMapping(value="/tagTaglist", method={RequestMethod.GET})
	public String tagTagList() {
		return "/sys/taglist";
	}

	@RequestMapping(value="/gettagTaglists", method={RequestMethod.GET})
	@ResponseBody
	public List<TagTag> tagTagLists(TagTag tagTag, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TagTag> tagTags = tagTagServiceImpl.select(tagTag);
			return tagTags;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}

	@RequestMapping(value = "/page")
	@ResponseBody
	public Map<String, Object> page(HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
//		String startTime = req.getParameter("StartTime");
//		String endTime = req.getParameter("EndTime");
		String tagTagName = req.getParameter("tagTagName");
		String tagTagState = req.getParameter("tagTagState");

		try {
			log.info("<=====执行sysmenulist====>");

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
//			parm.put("startTime", startTime);
//			parm.put("endTime", endTime);
			parm.put("tagTagName", tagTagName);
			parm.put("tagTagState", tagTagState);

			int count = tagTagServiceImpl.count(parm);
			List<TagTag> sysAccs = tagTagServiceImpl.page(parm);
			return RespUtil.pageResult(count, sysAccs);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;

	}

	@RequestMapping(value = "/deleteTag")
	@ResponseBody
	@com.sme.core.spring.Log(type = "标签管理", desc = "删除标签")
	public StringJSON tagTagDelete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					TagTag models = new TagTag();
					models.setTagTagUnid(Integer.valueOf(id));
					tagTagServiceImpl.delete(models);
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
	@com.sme.core.spring.Log(type = "标签管理", desc = "修改标签")
	public StringJSON tagTagSave(TagTag tagTag, Model model, HttpServletRequest request, HttpServletResponse response){
		try {
			if (tagTag.getTagTagUnid() != null) {

				TagTag models = new TagTag();
				models.setTagTagName(tagTag.getTagTagName());
				models.setTagTagUnid(tagTag.getTagTagUnid());
				if(checkIsExists(models)){
					return getSuccess(false, "该名称已存在,请输入未使用名称！！");
				}

				TagTag amodels = new TagTag();
				amodels = tagTagServiceImpl.getById(tagTag);
				amodels.setTagTagState(tagTag.getTagTagState());
				amodels.setTagTagDesc(tagTag.getTagTagDesc());
				amodels.setTagTagName(tagTag.getTagTagName());
				amodels.setTagTagUdate(new Date());
				amodels.setTagTagUuser(1);
				tagTagServiceImpl.update(amodels);

				return getSuccess(true, "修改成功");
			} else {

				TagTag models = new TagTag();
				models.setTagTagName(tagTag.getTagTagName());
				if(checkIsExists(models)){
					return getSuccess(false, "该名称已存在,请输入未使用名称！！");
				}

				models.setTagTagCdate(new Date());
				models.setTagTagCuser(1);
				models.setTagTagType("1");

				tagTagServiceImpl.insert(tagTag);
				return getSuccess(true, "添加成功");
			}

		} catch (Exception e) {
			log.error(e.getCause().getMessage());
			return getSuccess(false, "发生系统异常");
		}
	}

	private boolean checkIsExists(TagTag tagTag) {
		Boolean flag = tagTagServiceImpl.getTagTag(tagTag);
		return flag;
	}

	public String tagTagAdd(Model model,TagTag tagTag){
			model.addAttribute("tagTag", tagTag);
			return "/tagTag/tagTagform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tagTagAdd(){
		ModelAndView mav = new ModelAndView("/tagTag/tagTagform","tagTag", new TagTag());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tagTagUpdate(@PathVariable Integer id, Model model){
		try
		{
			TagTag tagTag = new TagTag();
//			tagTag.setTagTagId(id);
			tagTag = tagTagServiceImpl.getById(tagTag);
			return tagTagAdd(model,tagTag);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tagTag/tagTaglist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tagTagInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tagTag/tagTagview");
		TagTag tagTag = new TagTag();
//		tagTag.setTagTagId(id);
		tagTag = tagTagServiceImpl.getById(tagTag);
		mav.addObject("tagTag", tagTag);
		return mav;
	}
	
	public TagTagServiceImpl getTagTagServiceImpl() {
		return tagTagServiceImpl;
	}

	public void setTagTagServiceImpl(TagTagServiceImpl tagTagServiceImpl) {
		this.tagTagServiceImpl = tagTagServiceImpl;
	}

	@Override
	public InterfaceBaseService<TagTag> getService() {
		return tagTagServiceImpl;
	}

	//================== begin ======================
 
	//================== end ======================
}
