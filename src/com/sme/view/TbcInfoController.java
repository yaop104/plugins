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
import com.sme.entity.SysAcc;
import com.sme.util.MD5;
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

import com.sme.service.impl.TbcInfoServiceImpl;
import com.sme.entity.TbcInfo;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TbcInfo")
public class TbcInfoController extends BaseController<TbcInfo> {
	@Autowired
	private TbcInfoServiceImpl tbcInfoServiceImpl;
	
	private Log log = LogFactory.getLog(TbcInfoController.class);

	@RequestMapping(value="/tbcInfolist", method={RequestMethod.GET})
	public String tbcInfoList(TbcInfo tbcInfo, HttpServletRequest req) {
		return "/tbcInfo/tbcInfolist";
	}
	
	@RequestMapping(value="/gettbcInfolists", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> tbcInfolists(TbcInfo tbcInfo, HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 10;
			page = 1;
		}
		String tioName = req.getParameter("tioName");
		String tioState = req.getParameter("tioState");

		try {
			log.info("<=====执行sysmenulist====>");

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("tioName",tioName);
			parm.put("tioState",tioState);

			int count = tbcInfoServiceImpl.count(parm);
			List<TbcInfo> tbcInfos = tbcInfoServiceImpl.page(parm);
			return RespUtil.pageResult(count, tbcInfos);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;

	}
	

	@RequestMapping(value = "/deleteTbcInfos")
	@ResponseBody
	@com.sme.core.spring.Log(type = "运营商管理", desc = "删除运营商")
	public StringJSON tbcInfoDelete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					TbcInfo tbcInfo = new TbcInfo();
					tbcInfo.setTioUnid(Integer.valueOf(id));
					tbcInfoServiceImpl.delete(tbcInfo);
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
	@com.sme.core.spring.Log(type = "运营商管理", desc = "修改运营商")
	public StringJSON tbcInfoSave(TbcInfo tbcInfo, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (tbcInfo.getTioUnid() != null) {


				TbcInfo tbcInfo1 = new TbcInfo();
				tbcInfo1 = tbcInfoServiceImpl.getById(tbcInfo);
				tbcInfo1.setTioName(tbcInfo.getTioName());
				tbcInfo1.setTioContactname(tbcInfo.getTioContactname());
				tbcInfo1.setTioContactphone(tbcInfo.getTioContactphone());
				tbcInfo1.setTioDesc(tbcInfo.getTioDesc());
				tbcInfo1.setTioState(tbcInfo.getTioState());
				tbcInfo1.setTioType(tbcInfo.getTioType());
				tbcInfo1.setTioUdate(new Date());
				tbcInfo1.setTioUuser("admin2");

				tbcInfoServiceImpl.update(tbcInfo1);

				return getSuccess(true, "修改成功");
			} else {

				tbcInfo.setTioCdate(new Date());
				tbcInfo.setTioCuser("admin");

				tbcInfoServiceImpl.insert(tbcInfo);

				return getSuccess(true, "添加成功");
			}

		} catch (Exception e) {
			log.error(e.getCause().getMessage());
			return getSuccess(false, "发生系统异常");
		}
	}
	
	public String tbcInfoAdd(Model model,TbcInfo tbcInfo){
			model.addAttribute("tbcInfo", tbcInfo);
			return "/tbcInfo/tbcInfoform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tbcInfoAdd(){
		ModelAndView mav = new ModelAndView("/tbcInfo/tbcInfoform","tbcInfo", new TbcInfo());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tbcInfoUpdate(@PathVariable Integer id, Model model){
		try
		{
			TbcInfo tbcInfo = new TbcInfo();
//			tbcInfo.setTbcInfoId(id);
			tbcInfo = tbcInfoServiceImpl.getById(tbcInfo);
			return tbcInfoAdd(model,tbcInfo);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tbcInfo/tbcInfolist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tbcInfoInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tbcInfo/tbcInfoview");
		TbcInfo tbcInfo = new TbcInfo();
//		tbcInfo.setTbcInfoId(id);
		tbcInfo = tbcInfoServiceImpl.getById(tbcInfo);
		mav.addObject("tbcInfo", tbcInfo);
		return mav;
	}
	
	public TbcInfoServiceImpl getTbcInfoServiceImpl() {
		return tbcInfoServiceImpl;
	}

	public void setTbcInfoServiceImpl(TbcInfoServiceImpl tbcInfoServiceImpl) {
		this.tbcInfoServiceImpl = tbcInfoServiceImpl;
	}

	@Override
	public InterfaceBaseService<TbcInfo> getService() {
		return tbcInfoServiceImpl;
	}

	//================== begin ======================
 
	//================== end ======================
}
