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
import com.sme.entity.SchoolSchool;
import com.sme.entity.SysAcc;
import com.sme.service.impl.SchoolSchoolServiceImpl;
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

import com.sme.util.RespUtil;

@Controller
@RequestMapping("/SchoolSchool")
public class SchoolSchoolController extends BaseController<SchoolSchool> {
	@Autowired
	private SchoolSchoolServiceImpl schoolSchoolServiceImpl;
	
	private Log log = LogFactory.getLog(SchoolSchoolController.class);
	
	@RequestMapping(value="/schoolSchoollist", method={RequestMethod.GET})
	public String schoolSchoolList(SchoolSchool schoolSchool, HttpServletRequest req) {
		return "/schoolSchool/schoolSchoollist";
	}
	
	@RequestMapping(value="/getschoolSchoollists", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> schoolSchoolLists(SchoolSchool schoolSchool, HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
		String schoolName = req.getParameter("schoolName");

		try {
			log.info("<=====执行sysmenulist====>");

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("schoolName",schoolName);

			int count = schoolSchoolServiceImpl.count(parm);
			List<SchoolSchool> schoolSchools = schoolSchoolServiceImpl.page(parm);
			return RespUtil.pageResult(count, schoolSchools);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String schoolSchoolDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 SchoolSchool schoolSchool = new SchoolSchool();
//			 schoolSchool.setSchoolSchoolId(id);
			schoolSchoolServiceImpl.delete(schoolSchool);
			return "redirect:/schoolSchool/schoolSchoollist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/schoolSchool/schoolSchoollist.do";
		}
	 }

	@RequestMapping(value = "/deleteSchools")
	@ResponseBody
	@com.sme.core.spring.Log(type = "学校管理", desc = "删除学校")
	public StringJSON deleteSchools(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					SchoolSchool schoolSchool = new SchoolSchool();
					schoolSchool.setSchoolUnid(Integer.valueOf(id));
					schoolSchoolServiceImpl.delete(schoolSchool);
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
	
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	@ResponseBody
	@com.sme.core.spring.Log(type = "学校管理", desc = "新增/修改学校")
	public StringJSON schoolSchoolSave(SchoolSchool schoolSchool, Model model, HttpServletRequest request, HttpServletResponse response){
		SysAcc session = (SysAcc) request.getSession().getAttribute("loginUser");
		try {
			if (schoolSchool.getSchoolUnid() != null) {

				SchoolSchool schoolSchool1 = new SchoolSchool();
				schoolSchool1.setSchoolUnid(schoolSchool.getSchoolUnid());
				schoolSchool1.setSchoolDesc(schoolSchool.getSchoolDesc());
				schoolSchool1.setSchoolName(schoolSchool.getSchoolName());
				schoolSchool1.setSchoolRule(schoolSchool.getSchoolRule());
				schoolSchool1.setSchoolUdate(new Date());
				schoolSchool1.setSchoolUuser(session.getSysAccId());

				schoolSchoolServiceImpl.update(schoolSchool1);


				return getSuccess(true, "修改成功");
			} else {

				schoolSchool.setSchoolState("1");
				schoolSchool.setSchoolCdate(new Date());
				schoolSchool.setSchoolCuser(session.getSysAccId());
				schoolSchoolServiceImpl.insert(schoolSchool);
				return getSuccess(true, "添加成功");
			}

		} catch (Exception e) {
			log.error(e.getCause().getMessage());
			return getSuccess(false, "发生系统异常");
		}
	}
	
	public String schoolSchoolAdd(Model model,SchoolSchool schoolSchool){
			model.addAttribute("schoolSchool", schoolSchool);
			return "/schoolSchool/schoolSchoolform";
	}
	
	@RequestMapping("/add")
	public ModelAndView schoolSchoolAdd(){
		ModelAndView mav = new ModelAndView("/schoolSchool/schoolSchoolform","schoolSchool", new SchoolSchool());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String schoolSchoolUpdate(@PathVariable Integer id, Model model){
		try
		{
			SchoolSchool schoolSchool = new SchoolSchool();
//			schoolSchool.setSchoolSchoolId(id);
			schoolSchool = schoolSchoolServiceImpl.getById(schoolSchool);
			return schoolSchoolAdd(model,schoolSchool);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/schoolSchool/schoolSchoollist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView schoolSchoolInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/schoolSchool/schoolSchoolview");
		SchoolSchool schoolSchool = new SchoolSchool();
//		schoolSchool.setSchoolSchoolId(id);
		schoolSchool = schoolSchoolServiceImpl.getById(schoolSchool);
		mav.addObject("schoolSchool", schoolSchool);
		return mav;
	}
	
	public SchoolSchoolServiceImpl getSchoolSchoolServiceImpl() {
		return schoolSchoolServiceImpl;
	}

	public void setSchoolSchoolServiceImpl(SchoolSchoolServiceImpl schoolSchoolServiceImpl) {
		this.schoolSchoolServiceImpl = schoolSchoolServiceImpl;
	}

	@Override
	public InterfaceBaseService<SchoolSchool> getService() {
		return schoolSchoolServiceImpl;
	}

	//================== begin ======================
 
	//================== end ======================
}
