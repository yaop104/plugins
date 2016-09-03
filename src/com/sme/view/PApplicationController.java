package com.sme.view;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.PAppDetail;
import com.sme.entity.PApplication;
import com.sme.entity.SysAcc;
import com.sme.service.PApplicationService;
import com.sme.util.Config;
import com.sme.util.Pass;
import com.sme.util.RespMessage;
import com.sme.util.RespUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/application")
public class PApplicationController extends BaseController<PApplication> {
	@Autowired
	private PApplicationService pApplicationService;

	private Log log = LogFactory.getLog(PApplicationController.class);

	@RequestMapping(value = "/pApplicationlist", method = {RequestMethod.GET})
	public String pApplicationList(PApplication pApplication, HttpServletRequest req) {
		return "/pApplication/pApplicationlist";
	}

	@RequestMapping(value = "/insertForT", method = { RequestMethod.POST })
	@ResponseBody
	public StringJSON insertForT(PApplication pApplication,HttpServletRequest req) {
		SysAcc sysAcc = (SysAcc)getLoginUser(req);
		try {
			pApplication.setpAppOpen(0);
			pApplication.setpAppPraise(0);
			pApplication.setpAppCdate(new Date());
			pApplication.setpAppCuser(sysAcc.getSysAccId());
			pApplicationService.insert(pApplication);
			return getSuccess(true, "新增成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "系统异常！");
		}
	}

	@RequestMapping(value = "/getpApplicationlists")
	@ResponseBody
	public Object pApplicationLists(HttpServletRequest req) {
		// 分页属性
		rows = Integer.parseInt(req.getParameter("rows"));
		page = Integer.parseInt(req.getParameter("page"));
		try {
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("pAppdetailName", req.getParameter("pAppdetailName"));
			parm.put("pluginType", req.getParameter("pluginType"));
			int count = pApplicationService.countForList(parm);
			List<PApplication> pAppDetails = pApplicationService.pageForList(parm);

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

	@RequestMapping(value = "/offLine")
	@ResponseBody
	@com.sme.core.spring.Log(type = "插件管理", desc = "插件运营 | 下线插件")
	public RespMessage offLine(String appId) {
		RespMessage msg = new RespMessage();
		try{
			if (StringUtils.isNotBlank(appId)) {
				pApplicationService.offLine(Integer.parseInt(appId));
				msg.setCode("500");
				msg.setMessage("下线成功");
			}
		}catch (Exception e){
			msg.setCode("1");
			msg.setMessage("下线失败");
		}
		return msg;

	}

	@RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET})
	public String pApplicationDelete(@PathVariable Integer id) {
		try {
			log.info("<=====执行delete口====>" + id);
			PApplication pApplication = new PApplication();
			pApplication.setpAppId(id);
			pApplicationService.delete(pApplication);
			return "redirect:/pApplication/pApplicationlist.do";
		} catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:/pApplication/pApplicationlist.do";
		}
	}

//	 public StringJSON pAppSave(PApplication pApplication){
//		 try
//		{
//			
//		}
//		catch (Exception e)
//		{
//			// TODO: handle exception
//		}
//	 }

	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public String pApplicationSave(PApplication pApplication, Model model, HttpServletRequest request, HttpServletResponse response) {

		try {
			if (pApplication.getpAppId() != null) {

				return "redirect:/pApplication/pApplicationlist.do";
			} else {

				return "redirect:/pApplication/pApplicationlist.do";
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return pApplicationAdd(model, pApplication);
		}
	}

	public String pApplicationAdd(Model model, PApplication pApplication) {
		model.addAttribute("pApplication", pApplication);
		return "/pApplication/pApplicationform";
	}

	@RequestMapping("/add")
	public ModelAndView pApplicationAdd() {
		ModelAndView mav = new ModelAndView("/pApplication/pApplicationform", "pApplication", new PApplication());
		return mav;
	}

	@RequestMapping(value = "/{id}/update")
	public String pApplicationUpdate(@PathVariable Integer id, Model model) {
		try {
			PApplication pApplication = new PApplication();
			pApplication.setpAppId(id);
			pApplication = pApplicationService.getById(pApplication);
			return pApplicationAdd(model, pApplication);
		} catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:/pApplication/pApplicationlist.do";
		}

	}

	@RequestMapping("/{id}/info")
	public ModelAndView pApplicationInfo(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("/pApplication/pApplicationview");
		PApplication pApplication = new PApplication();
		pApplication.setpAppId(id);
		pApplication = pApplicationService.getById(pApplication);
		mav.addObject("pApplication", pApplication);
		return mav;
	}

	//================== begin ======================
	@RequestMapping(value = "/apkversion", method = {RequestMethod.GET})
	public String getApkPage() {
		return "/pApplication/apkversion";
	}


	@RequestMapping(value = "/htmlversion", method = {RequestMethod.GET})
	public String getVersionPage() {
		return "/pApplication/htmlversion";
	}

	@RequestMapping(value = "/getApplicationlists")
	@ResponseBody
	public Map<String, Object> applicationLists(HttpServletRequest req) {
		SysAcc sysAcc = (SysAcc)getLoginUser(req);
		//分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 15;
			page = 1;
		}
		String pAppPlugintype = req.getParameter("pAppPlugintype") == null ? "" : req.getParameter("pAppPlugintype");
		String pAppdetailName = req.getParameter("pAppdetailName") == null ? "" : req.getParameter("pAppdetailName");
		try {
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			parm.put("pAppPlugintype", pAppPlugintype);
			parm.put("pAppPluginname", pAppdetailName);
			parm.put("pAppCuser", sysAcc.getSysAccId());
			if(sysAcc.getSysAccType().equals("2")){
				parm.put("pAppCuserType", "1");
			}
			int count = pApplicationService.count(parm);
			List<PApplication> pAppDetails = pApplicationService.page(parm);

			return RespUtil.pageResult(count, pAppDetails);

		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/applicationDelete", method = {RequestMethod.POST})
	@ResponseBody
	public StringJSON applicationDelete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					PApplication pApplication = new PApplication();
					pApplication.setpAppId(Integer.valueOf(id));
					pApplicationService.delete(pApplication);
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

	@RequestMapping(value = "/orderApp")
	public String orderApp() {
		return "/pApplication/orderApk";
	}

	@RequestMapping(value = "/getOrderApp")
	@ResponseBody
	public Object getOrderApp() {
		try {
			Map<String, Object> parm = new HashMap<String, Object>();

			List<PApplication> lists = pApplicationService.getOrderApp();

			parm.put("total", lists.size());
			parm.put("rows", lists);

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(parm);

			return json;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/saveOrder")
	@ResponseBody
	public Object saveOrder(String ids) {
		try {
			pApplicationService.saveOrder(ids);
			return "{\"code\":\"500\"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//================== end ======================

	@Override
	public InterfaceBaseService getService() {
		// TODO Auto-generated method stub
		return pApplicationService;
	}

	public PApplicationService getpApplicationService() {
		return pApplicationService;
	}

	public void setpApplicationService(PApplicationService pApplicationService) {
		this.pApplicationService = pApplicationService;
	}

	@RequestMapping(value = "/getLatestVersion", method = {RequestMethod.POST})
	@ResponseBody
	@Pass
	public Object checkApk(String userId, HttpServletRequest request) {
		Map<String, Object> json = new HashMap<>();
		try {
			PAppDetail detail = pApplicationService.hasNewVersion();
			if (detail != null) {
				NewVersionInfo newVersionInfo = new NewVersionInfo();
				newVersionInfo.setVersionName(detail.getpAppdetailVersion() + "");
				newVersionInfo.setVersionNo(detail.getpAppdetailVersion() + "");
				newVersionInfo.setReleaseNote(detail.getpAppdetailChangelog());
				newVersionInfo.setSize(detail.getpAppdetailSize() + "");
				String downloadUrl = request.getScheme() + "://" + request.getServerName() + ":" +
						request.getServerPort() + request.getContextPath() + "/application/" + "download.do?apkName=" +
						detail.getpAppdetailApk();
				newVersionInfo.setUrl(downloadUrl);
                json.put("data", newVersionInfo);
                json.put("code", "000000");
                json.put("message", "请求成功");
            } else {
                json.put("code", "000001");
                json.put("message", "当前已是最新版本");
				json.put("data", null);
            }
		} catch (Exception e) {
			json.put("code", "000002");
			json.put("message", "服务器异常");
		}
		return json;
	}

	@RequestMapping(value = "/download")
	@ResponseBody
	@Pass
	public Object downloadApk(HttpServletRequest request, HttpServletResponse response, String apkName) {
		String path = request.getServletContext().getRealPath("apk") + File.separator + apkName;

		File origin = new File(path);
		long fileLength = origin.length();    //原文件大小

		String userAgent = request.getHeader("USER-AGENT");

		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			String name = fixFileNameCharset(origin.getName(), userAgent);
			setRespHeader(response, name, userAgent);

			long pastLength = 0;            //记录已下载文件大小
			int rangeSwitch = 0;            //从头开始的全文下载从某字节开始的下载 bytes=27000-  从某字节开始到某字节结束的下载bytes=27000-39000
			long toLength = 0;            	//记录客户端需要下载的字节段的最后一个字节偏移量 比如 bytes = 27000 - 39000 则这个值是为39000

			String rangeBytes = "";         //记录客户端传来的形如“bytes=27000-”或者“bytes = 27000 - 39000”的内容
			if (request.getHeader("Range") != null) {//客户端请求的下载的文件块的开始字节
				rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
				if (rangeBytes.contains("-")) {
					if (rangeBytes.indexOf('-') == rangeBytes.length() - 1) {//bytes=969998336-
						rangeSwitch = 1;
						rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
						pastLength = Long.parseLong(rangeBytes.trim());
						if (pastLength > 0 && pastLength < fileLength) {
							response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
						}
					} else {//bytes=1275856879-1275877358
						rangeSwitch = 2;
						String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
						String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
						pastLength = Long.parseLong(temp0.trim());  //bytes=1275856879-1275877358从第 1275856879 个字节开始下载
						toLength = Long.parseLong(temp2);   //bytes=1275856879-1275877358到第 1275877358 个字节结束
						if ((pastLength == 0 && toLength < fileLength) || (pastLength > 0 && toLength > pastLength)) {
							response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
						}
					}
				} else if (Long.parseLong(rangeBytes.trim()) > 0 && Long.parseLong(rangeBytes.trim()) < fileLength) {//bytes=969998336
					response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
				}
			}

			/**
			 * 如果设置了Content-Length 则客户端会自动进行多线程下载。如果不希望 支持多线程则不要设置这个参数。
			 * 响应的格式是:
			 * Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
			 */
			response.setHeader("Content-Length", String.valueOf(fileLength - pastLength));

			if (pastLength != 0) {
                /*
                 *不是从最开始下载,
                 *响应的格式是:
                 *Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
                 */
				switch (rangeSwitch) {
					case 1: {//针对 bytes=27000- 的请求
						String contentRange = new StringBuffer("bytes ")
								.append(new Long(pastLength).toString())
								.append("-").append(new Long(fileLength - 1).toString())
								.append("/").append(new Long(fileLength).toString())
								.toString();
						response.setHeader("Content-Range", contentRange);
						break;
					}
					case 2: {//针对 bytes=27000-39000 的请求
						String contentRange = rangeBytes + "/" + new Long(fileLength).toString();
						response.setHeader("Content-Range", contentRange);
						break;
					}
					default: {
						break;
					}
				}
			} else {
				//是从开始进行下载
			}

			bos = new BufferedOutputStream(response.getOutputStream());
			bis = new BufferedInputStream(new FileInputStream(path));
			try {
				byte[] buffer = new byte[8192];     	//暂存容器
				switch (rangeSwitch) {
					case 0: {                           //普通下载 或者从头开始的下载
						//同1
					}
					case 1: {                           //针对 bytes=27000- 的请求
						int n;
						bis.skip(pastLength);
						while ((n = bis.read(buffer)) != -1) {
							bos.write(buffer, 0, n);
						}
						break;
					}
					case 2: {//针对 bytes=27000-39000 的请求
						int n;
						bis.skip(pastLength);
						while ((n = bis.read(buffer)) < toLength) {
							try {
								bos.write(buffer, 0, n);
							} catch (Exception e1) {
								break;
							}
						}
						break;
					}
					default: {
						break;
					}
				}
				bos.flush();
			} catch (Exception e) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	private void setRespHeader(HttpServletResponse resp, String wholeFileName,String userAgent) throws UnsupportedEncodingException {
		resp.setCharacterEncoding("UTF-8");
		if (!StringUtils.isEmpty(userAgent) && userAgent.toUpperCase().indexOf("MSIE") != -1) {
			if(wholeFileName.indexOf(".")>0) {
				String suffix = wholeFileName.substring(wholeFileName.indexOf(".") + 1);
				String newsuffix = URLDecoder.decode(suffix, "UTF-8");
				if (isHanzi(newsuffix)) {
					resp.addHeader("Content-Disposition", "attachment;filename=\"" + wholeFileName.replace(".", "%2E") + "\"");
				} else {
					resp.addHeader("Content-Disposition", "attachment;filename=\"" + wholeFileName + "\"");
				}
			}else{
				resp.addHeader("Content-Disposition", "attachment;filename=\"" + wholeFileName + "\"");
			}
		}else{
			resp.addHeader("Content-Disposition", "attachment;filename=\"" + wholeFileName + "\"");
		}
		resp.addHeader("Keep-Alive", "timeout=30, max=180");
		resp.addHeader("Connection", "Keep-Alive");
		resp.setContentType("application/octet-stream;charset=utf-8");
		//告诉客户端允许断点续传多线程连接下载,响应的格式 是:  Accept - Ranges:bytes
		resp.setHeader("Accept-Ranges", "bytes");
		//如果是第一次下,还没有断点续传,状态是默认的 200, 无需显式设置; 响应的格式是: HTTP / 1.1 200 OK
		resp.setStatus(HttpServletResponse.SC_OK);
	}
	/**
	 * 判断是否有汉字
	 */
	private boolean isHanzi(String str){
		if(str.getBytes().length == str.length()){
			return false;
		}else{
			return true;
		}

	}

	/**
	 * 文件下载时的文件名中文编码修正
	 *
	 * @param wholeFileName
	 * @param userAgent
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String fixFileNameCharset(Object wholeFileName, String userAgent) throws UnsupportedEncodingException {
		String wholeFileNameStr = "文件名缺失";
		if (null != wholeFileName) {
			wholeFileNameStr = wholeFileName.toString();
			if (!StringUtils.isEmpty(userAgent) && userAgent.toUpperCase().indexOf("MSIE") != -1) {//IE浏览器，采用URLEncoder编码
				wholeFileNameStr = URLEncoder.encode(wholeFileNameStr, "ISO8859-1");
			} else {//userAgent无法获取、以及非IE浏览器，统一转为ISO8859-1编码
                wholeFileNameStr = new String(wholeFileNameStr.getBytes("UTF-8"), "ISO8859-1");
			}
		}
		return wholeFileNameStr;
	}

	class NewVersionInfo {
		private String versionName;
		private String versionNo;
		private String releaseNote;
		private String size;
		private String url;
		private String ext;

		public NewVersionInfo() {
		}

		public String getVersionName() {
			return versionName;
		}

		public void setVersionName(String versionName) {
			this.versionName = versionName;
		}

		public String getVersionNo() {
			return versionNo;
		}

		public void setVersionNo(String versionNo) {
			this.versionNo = versionNo;
		}

		public String getReleaseNote() {
			return releaseNote;
		}

		public void setReleaseNote(String releaseNote) {
			this.releaseNote = releaseNote;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getExt() {
			return ext;
		}

		public void setExt(String ext) {
			this.ext = ext;
		}
	}
}
