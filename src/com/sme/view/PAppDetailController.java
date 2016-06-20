package com.sme.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.PAppDetail;
import com.sme.service.PAppDetailService;
import com.sme.service.detailState.context.StateContext;
import com.sme.util.JSONObject;
import com.sme.util.RespMessage;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/appDetail")
public class PAppDetailController extends BaseController<PAppDetail> {
	@Autowired
	private PAppDetailService pAppDetailService;

	private Log log = LogFactory.getLog(PAppDetailController.class);

	@RequestMapping(value = "/appDetailView", method = { RequestMethod.GET })
	public String pAppDetailList() {
		if (log.isDebugEnabled())
			log.debug("打开插件审核页面..");
		return "/pAppDetail/pAppDetaillist";
	}

	@RequestMapping(value = "/getpAppDetaillists")
	@ResponseBody
	public Object pAppDetailLists(HttpServletRequest req) {
		// 分页属性
		rows = Integer.parseInt(req.getParameter("rows"));
		page = Integer.parseInt(req.getParameter("page"));
		try {
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("pAppdetailName", req.getParameter("pAppdetailName"));
			parm.put("pluginType", req.getParameter("pluginType"));
			int count = pAppDetailService.countForGetCheckAppList(parm);
			List<PAppDetail> pAppDetails = pAppDetailService.pageForGetCheckAppList(parm);

			parm.clear();
			parm.put("total", count);
			parm.put("rows", pAppDetails);

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(parm);

			return json;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/delete", method = { RequestMethod.GET })
	public String pAppDetailDelete(@PathVariable Integer id) {
		try {
			log.info("<=====执行delete口====>" + id);
			PAppDetail pAppDetail = new PAppDetail();
			pAppDetail.setpAppdetailId(id);
			pAppDetailService.delete(pAppDetail);
			return "redirect:/pAppDetail/pAppDetaillist.do";
		} catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:/pAppDetail/pAppDetaillist.do";
		}
	}

	/**
	 * 新增或者更新
	 * 
	 * @param pAppDetail
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String pAppDetailSave(PAppDetail pAppDetail, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			if (pAppDetail.getpAppdetailId() != null) {

				return "redirect:/pAppDetail/pAppDetaillist.do";
			} else {

				return "redirect:/pAppDetail/pAppDetaillist.do";
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return pAppDetailAdd(model, pAppDetail);
		}
	}

	/**
	 * 跳转
	 * 
	 * @param model
	 * @param pAppDetail
	 * @return
	 */
	public String pAppDetailAdd(Model model, PAppDetail pAppDetail) {
		model.addAttribute("pAppDetail", pAppDetail);
		return "/pAppDetail/pAppDetailform";
	}

	/**
	 * 跳转新增页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView pAppDetailAdd() {
		ModelAndView mav = new ModelAndView("/pAppDetail/pAppDetailform", "pAppDetail", new PAppDetail());
		return mav;
	}

	/**
	 * 跳转更新页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/update")
	public String pAppDetailUpdate(@PathVariable Integer id, Model model) {
		try {
			PAppDetail pAppDetail = new PAppDetail();
			pAppDetail.setpAppdetailId(id);
			pAppDetail = pAppDetailService.getById(pAppDetail);
			return pAppDetailAdd(model, pAppDetail);
		} catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:/pAppDetail/pAppDetaillist.do";
		}

	}

	/**
	 * 详情
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequestMapping("/{id}/info")
	@ResponseBody
	public Object pAppDetailInfo(@PathVariable Integer id) throws IOException {
		PAppDetail pAppDetail = new PAppDetail();
		pAppDetail.setpAppdetailId(id);
		pAppDetail = pAppDetailService.getById(pAppDetail);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(pAppDetail);

		return json;
	}

	// ================== begin ======================

	/**
	 * 审核操作
	 * @param t
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/appAudit")
	@ResponseBody
	@com.sme.core.spring.Log(type = "插件管理", desc = "插件审核")
	public Object appAudit(PAppDetail t) throws IOException {
		RespMessage msg = (RespMessage) pAppDetailService.auditApp(t);

		String json = "{\"code\":\"500\",\"message\":\"SUCCESS\"}";
		if (msg != null) {
			json = "{\"code\":\"" + msg.getCode() + "\",\"message\":\"" + msg.getMessage() + "\"}";
		}

		ObjectMapper mapper = new ObjectMapper();
		json = mapper.writeValueAsString(json);

		return json;
	}

	/**
	 * 查看插件详情页面
	 * @return
	 */
	@RequestMapping(value = "/viewDetail")
	public String detailView() {
		return "/pAppDetail/detailForView";
	}

	/**
	 * 审核插件详情页面
	 * @return
	 */
	@RequestMapping(value = "/checkDetail")
	public String detailCheck() {
		return "/pAppDetail/checkDetail";
	}

	@RequestMapping(value = "/upload")
	public String upload() {
		return "/pAppDetail/upload";
	}

	@RequestMapping(value = "/uploadfile")
	public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletResponse resp) {
		final String basePath = "/Users/haoy/Downloads/test";
		try {
			byte[] bytes = file.getBytes();

			File dirPath = new File(basePath);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}

			String fileName = file.getOriginalFilename();

			File uploadedFile = new File(basePath + File.separator + fileName);
			FileCopyUtils.copy(bytes, uploadedFile);
			resp.getWriter().write("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/uploadFile")
	public void upload(HttpServletRequest req, HttpServletResponse res) {
		final String basePath = "/Users/haoy/Downloads/test";
		String fileName = null;
		try {
			File dirPath = new File(basePath);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}

			MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) req;

			MultipartFile multiFile = multiReq.getFile("file");
			fileName = multiFile.getOriginalFilename();
			
			String path = basePath + File.separator + fileName;
			OutputStream os = new BufferedOutputStream(new FileOutputStream(path));
			
			FileCopyUtils.copy(multiFile.getInputStream(), os);
			
			res.getWriter().write("true");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.info("文件" + fileName + "上传失败，未找到指定文件。");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//================== begin ======================
	
	@RequestMapping(value="/pageParams", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> pageParams(HttpServletRequest req) {
		//分页属性
		if(req.getParameter("rows")!=null && req.getParameter("page")!=null){
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		}else{
			rows = 15;
			page = 1;
		}
		try {
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("pAppdetailPlugintype", req.getParameter("ptype"));
			String pid = req.getParameter("pid");
			parm.put("pid", pid);
			int count = pAppDetailService.count(parm);
			List<PAppDetail> sysAccs = pAppDetailService.page(parm);
			return RespUtil.pageResult(count, sysAccs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="/changeAppState", method={RequestMethod.POST})
	@ResponseBody
	public JSONObject changeAppState(PAppDetail pAppDetail){
		
		PAppDetail  appDetail = pAppDetailService.getById(pAppDetail);
		
		JSONObject jsonObject = new JSONObject();
		
		if(null == appDetail)
		{
			jsonObject.setCode(501);
			jsonObject.setInfo("该插件已经被其他用户删除！");
			return jsonObject;
		}
		
//		if(!pAppDetail.getpAppdetailAdminid().equals(super.getAdminId()))
//		{
//			jsonObject.setCode(503);
//		    jsonObject.setInfo("请勿非法操作！");
//		    return jsonObject;
//		}
		
		StateContext context = StateContext.build(pAppDetail.getpAppdetailAuditstate());
		
		appDetail.setpAppdetailAuditstate(pAppDetail.getpAppdetailAuditstate());
		
		if("3".equals(pAppDetail.getpAppdetailAuditstate()))
		{
			return context.start(pAppDetail);
		}
		else if("4".equals(pAppDetail.getpAppdetailAuditstate()))
		{
			return context.stop(pAppDetail);
		}
		
		return jsonObject;
		
	}
	
	
	@RequestMapping(value="/deleteHtml", method={RequestMethod.POST})
	@ResponseBody
	public JSONObject deleteHtml(PAppDetail pAppDetail){
		
		pAppDetail = pAppDetailService.getById(pAppDetail);
		
		JSONObject jsonObject = new JSONObject();
		
		if(null ==pAppDetail)
		{
			jsonObject.setCode(501);
			jsonObject.setInfo("该插件已经被其他用户删除！");
			return jsonObject;
		}
		
		//后期增加session用户验证
//		if(!pAppDetail.getpAppdetailAdminid().equals(super.getAdminId()))
//		{
//			jsonObject.setCode(503);
//			jsonObject.setInfo("请勿非法操作！");
//			return jsonObject;
//		}

		StateContext context = StateContext.build(pAppDetail.getpAppdetailAuditstate());
		
		return context.del(pAppDetail);
	}
	
	/**
	 * 返回小类详情页面
	 * @return
	 */
	@RequestMapping(value="/viewHtml", method={RequestMethod.GET})
	public String  getViewHtml(){
		return "/pAppDetail/viewHtml";
	}
	
	/**
	 * 返回html小类类表页面
	 * @return
	 */
	@RequestMapping(value="/smallHtml", method={RequestMethod.GET})
	public String getSmallHtmlPage(){
		return "/pAppDetail/smallHtml";
	}
	/**
	 * 返回apk小类类表页面
	 * @return
	 */
	@RequestMapping(value="/smallApk", method={RequestMethod.GET})
	public String getSmallApkPage(String pid, Map<String, Object> map){
		map.put("pid", pid);
		return "/pAppDetail/smallApk";
	}
	
	/**
	 * 返回html添加页面
	 * @return
	 */
	@RequestMapping(value="/htmlversion", method={RequestMethod.GET})
	public String getVersionPage(){
		return "/pAppDetail/htmlversion";
	}

	/**
	 * 增加html记录
	 * @param pAppDetail
	 * @return
	 */
	@RequestMapping(value="/addHtmlDetail", method={RequestMethod.POST})
	@ResponseBody
	public JSONObject addHtmlDetail(PAppDetail pAppDetail){
		return pAppDetailService.addHtmlDetail(pAppDetail);
	}
	
	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return pAppDetailService;
	}


	public PAppDetailService getpAppDetailService()
	{
		return pAppDetailService;
	}

	public void setpAppDetailService(PAppDetailService pAppDetailService)
	{
		this.pAppDetailService = pAppDetailService;
	}
	//================== end ======================
}
