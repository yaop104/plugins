package com.sme.view;

import com.sme.core.model.StringJSON;
import com.sme.entity.*;
import com.sme.service.*;
import com.sme.service.impl.TdcDictionaryServiceImpl;
import com.sme.util.*;
import com.sme.util.mail.MailSenderInfo;
import com.sme.util.mail.SimpleMailSender;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by yao on 2016/7/11.
 */
@Controller
@RequestMapping("/outInterface")
public class OutInterfaceController {

    @Autowired
    private TdcDictionaryServiceImpl tdcDictionaryServiceImpl;
    @Autowired
    private PApplicationService pApplicationService;
    @Autowired
    private PAppDetailService pAppDetailService;
    @Autowired
    private TagTagService tagTagServiceImpl;
    @Autowired
    private SysAccService sysAccService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private TapDownloadService tapDownloadService;
    @Autowired
    public FeedbackService feedbackService;
    @Autowired
    private TsmSendMessageService tsmSendMessageService;
    @Autowired
    private TodOrderService todOrderService;

    private Log log = LogFactory.getLog(TdcDictionaryController.class);

    /**
     * 字符串转换成JSON
     *
     * @param isSuccess
     * @param message
     * @return
     */
    protected StringJSON getSuccess(boolean isSuccess, String message, Object data) {
        StringJSON json = new StringJSON();
        json.setSuccess(isSuccess);
        json.setMessage(message);
        json.setData(data);
        return json;
    }

    protected StringJSON getSuccess(boolean isSuccess, String message) {
        StringJSON json = new StringJSON();
        json.setSuccess(isSuccess);
        json.setMessage(message);
        return json;
    }


    @RequestMapping(value = "/insertData", method = {RequestMethod.POST})
    @ResponseBody
    public StringJSON insertData(@RequestParam(value = "pic", required = false) MultipartFile[] files,
                                 long userId,String text,HttpServletRequest request,HttpServletResponse response){
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setText(text);
        feedback.setCreatTime(new Date());
        String savePath = request.getServletContext().getRealPath("upload");
        try {
            StringBuilder sb = new StringBuilder();
            //判断file数组不能为空并且长度大于0
            if(files!=null&&files.length>0){
                for(int i = 0;i<files.length;i++){
                    MultipartFile file = files[i];
                    IFileSaver fileUtil = new ImageCheckProxy(new FileSaver(savePath));
                    if(!file.isEmpty()){
                        //保存文件
                        fileUtil.save(file, file.getOriginalFilename());
                        sb.append(file.getOriginalFilename()).append(",");
                    }
                }
                sb.deleteCharAt(sb.lastIndexOf(","));
                feedback.setPic(sb.toString());
            }
            feedbackService.insert(feedback);
            return getSuccess(true, "反馈成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getSuccess(false, "反馈异常");
        }
    }

    @RequestMapping(value="/getHomePage", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON getHomePage(HttpServletRequest req) {
        try {
            log.info("<=====执行getHomePage====>");
            List<TdcDictionary> tdcDictionarys = tdcDictionaryServiceImpl.select(new TdcDictionary());
            PAppDetail pAppDetail = new PAppDetail();
            List<PAppDetail> pAppDetails = pAppDetailService.selectDetail(pAppDetail);
            List<TagTag> tagTags =tagTagServiceImpl.select(new TagTag());
            Map<String, Object> map = new HashMap<>();
            map.put("type",tdcDictionarys);
            map.put("tag",tagTags.size()>0?tagTags.get(0):new TagTag());
            map.put("apps",pAppDetails);

            return getSuccess(true,"",map);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"分类获取失败,系统异常！！");
        }

    }

    @RequestMapping(value="/getUserInfo", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON getUserInfo(Integer id, HttpServletRequest req) {
        try {
            log.info("<=====执行getUserInfo====>");
            SysAcc sysAcc = new SysAcc();
            sysAcc.setSysAccId(id);
            sysAcc = sysAccService.getById(sysAcc);
            sysAcc.setSysAccPassword("");
            Map<String, Object> map = new HashMap<>();
            map.put("sysAcc",sysAcc);
            return getSuccess(true,"成功",map);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"分类获取失败,系统异常！！");
        }

    }

    /**
     * 上传logo
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/updateLogo", method={RequestMethod.POST})
    @ResponseBody
    public StringJSON updateLogo(Integer id,@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        System.out.println("file="+file.getOriginalFilename());// 得到上传文件的文件名
        String savePath = Config.HEAD_IMG_PATH;
        IFileSaver fileUtil = new ImageCheckProxy(new FileSaver(savePath));
        fileUtil.save(file, file.getOriginalFilename());
        JSONObject json = fileUtil.getResult();
        if(500==json.getCode()){
            SysAcc sysAcc = new SysAcc();
            sysAcc.setSysAccId(id);
            sysAcc.setSysAccHead(json.getInfo());
            sysAccService.update(sysAcc);
            String img = json.getInfo();
            img = Config.HEAD_IMG_REALPATH + "download/pic/" + img;
            json.setInfo(img);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("logo",json);
        if(500==json.getCode()){
            return getSuccess(true,"",map);
        }else{
            return getSuccess(false,"",map);
        }

    }

    @RequestMapping(value="/login", method={RequestMethod.POST})
    @ResponseBody
    public StringJSON login(String account, String password, HttpSession session, Model model, HttpServletRequest req){

        RespMessage respMessage = new RespMessage();
        try
        {

            SysAcc sysAcc = new SysAcc();
            //是否增加ip限制
            if(!"".equals(password) && !"".equals(account.trim()) ){
                sysAcc = sysAccService.getSysAccForLogin(account, password);//验证用户名、密码登录（用于普通登录）
                if(sysAcc != null){
                    sysAcc.setSysAccPassword("");
                    model.addAttribute("loginUser", sysAcc);
                    respMessage.setCode("0");
                    String path = req.getContextPath();
                    respMessage.setMessage("成功");
                    respMessage.setBody(sysAcc);

                    //登陆成功 记录登陆日志
                    LoginLog log = new LoginLog();
                    log.setAccId(sysAcc.getSysAccId());
                    log.setLoginIp(sysAcc.getSysAccMobile());

                    loginLogService.insert(log);
                    Map<String, Object> map = new HashMap<>();
                    map.put("login",sysAcc);
                    return getSuccess(true,"",map);

                }else{
                    respMessage.setCode("1");
                    respMessage.setMessage("用户名或密码错误！");
                    return getSuccess(false,"用户名或密码错误！");
                }

            }else{
                respMessage.setCode("1");
                respMessage.setMessage("用户名或密码错误！");
                return getSuccess(false,"用户名或密码错误！");
            }
        }
        catch (Exception e)
        {

            e.getStackTrace();
            respMessage.setCode("1");
            respMessage.setMessage("失败，系统异常，请刷新页面，尝试重新登入！");
            return getSuccess(false,"失败，系统异常，请刷新页面，尝试重新登入！");
        }

    }


    @RequestMapping(value="/gettdcDictionarylists", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON tdcDictionaryLists(TdcDictionary tdcDictionary, HttpServletRequest req) {
        try {
            log.info("<=====执行gettdcDictionarylists====>");
            List<TdcDictionary> tdcDictionarys = tdcDictionaryServiceImpl.select(tdcDictionary);
            return getSuccess(true,"",tdcDictionarys);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"分类获取失败,系统异常！！");
        }

    }

    @RequestMapping(value="/getpApplicationlists", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON getpApplicationlists(PApplication pApplication, HttpServletRequest req) {
        try {
            log.info("<=====执行getpApplicationlists====>");
            Map<String, Object> parm = new HashMap<String, Object>();
            parm.put("pAppdetailName", req.getParameter("pAppdetailName"));
            parm.put("pluginType", req.getParameter("pluginType"));
            List<PApplication> pAppDetails = pApplicationService.pageForList(parm);
            return getSuccess(true,"",pAppDetails);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"app列表获取失败,系统异常！！");
        }

    }

    @RequestMapping(value="/getpApplicationDetail", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON getpApplicationDetail(PAppDetail pAppDetail, HttpServletRequest req) {
        try {
            log.info("<=====执行getpApplicationDetail====>");
            List<PAppDetail> pAppDetails = pAppDetailService.selectDetail(pAppDetail);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pAppDetails", pAppDetails);
            return getSuccess(true,"",map);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"app获取失败,系统异常！！");
        }

    }

    @RequestMapping(value="/getpApplicationPackages", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON getpApplicationPackages(PAppDetail pAppDetail, HttpServletRequest req) {
        try {
            log.info("<=====执行getpApplicationPackages====>");
            List<PAppDetail> pAppDetails = pAppDetailService.selectDetail(pAppDetail);
            String applicationPackages = "";
            for(PAppDetail p : pAppDetails){
                applicationPackages = applicationPackages + p.getpAppdetailPackagename() + ",";
            }
            applicationPackages = applicationPackages.substring(0,applicationPackages.length()-1);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("applicationPackages", applicationPackages);
            return getSuccess(true,"",map);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"app获取失败,系统异常！！");
        }

    }


    @RequestMapping(value="/insertDownload", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON insertDownload(TapDownload tapDownload, HttpServletRequest req) {
        try {
            log.info("<=====执行insertDownload====>");
            tapDownload.setTapState("1");
            tapDownload.setTapUdate(new Date());
            tapDownloadService.insert(tapDownload);


            int apkName = tapDownload.getTapAppid();
            PAppDetail pAppDetail = new PAppDetail();
            pAppDetail.setpAppdetailId(apkName);
           PAppDetail pAppDetails = pAppDetailService.getById(pAppDetail);

            if(pAppDetails ==null ){
                return null;
            }
            PApplication pApplication = new PApplication();
            pApplication.setpAppId(pAppDetails.getpAppdetailApkactionid());
            pApplication = pApplicationService.getById(pApplication);
            pApplication.setpAppOpen(pApplication.getpAppOpen()==null?1:pApplication.getpAppOpen()+1);
            pApplicationService.update(pApplication);

            return getSuccess(true,"");
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"系统异常！！");
        }

    }

    @RequestMapping(value="/getTapDownload", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON getTapDownload(TapDownload tapDownload, HttpServletRequest req) {
        try {
            log.info("<=====执行getTapDownload====>");
            List<TapDownload> tapDownloads = tapDownloadService.select(tapDownload);
            return getSuccess(true,"",tapDownloads);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"列表获取失败,系统异常！！");
        }

    }

    @RequestMapping(value="/getImgList", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public StringJSON getImgList() {
        try {
            log.info("<=====执行getImgList====>");
            List<TodOrder> todOrders = new ArrayList<>();
            todOrders = todOrderService.getImgList();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("todOrders", todOrders);
            return getSuccess(true,"",map);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"列表获取失败,系统异常！！");
        }

    }

    @RequestMapping(value = "/register", method = { RequestMethod.POST })
    @ResponseBody
    @com.sme.core.spring.Log(type = "首页", desc = "用户注册")
    public StringJSON sysAccRegistere(SysAcc sysAcc, Model model, HttpServletRequest request,
                                      HttpServletResponse response) {
        try {

            SysAcc sysAccnames=new SysAcc();
            sysAccnames.setSysAccName(sysAcc.getSysAccName());
            if(chkAccountIsExists(sysAccnames)){
                return getSuccess(false, "该用户名已存在,请重新输入用户名！！");
            }
            if(!StringUtil.isEmpty(sysAcc.getSysAccMobile())){
                SysAcc sysAccphone = new SysAcc();
                sysAccphone.setSysAccMobile(sysAcc.getSysAccMobile());
                if(chkAccountIsExists(sysAccphone)){
                    return getSuccess(false, "该手机号码已存在,请输入未注册手机号码！！");
                }
            }
            if(!RegexValidateUtil.checkEmail(sysAcc.getSysAccEmail())){
                return getSuccess(false, "该邮箱格式不正确,请输入新的邮箱！！");
            }
            SysAcc sysAccmail = new SysAcc();
            sysAccmail.setSysAccEmail(sysAcc.getSysAccEmail());
            if(chkAccountIsExists(sysAccmail)){
                return getSuccess(false, "该邮箱已存在,请输入未注册邮箱！！");
            }
            String pwd = StringUtil.getRandomChar(6);
            String password = MD5.encryByMD5(pwd);
            sysAcc.setSysAccPassword(password);
            sysAcc.setSysAccState("1");
            sysAcc.setSysAccType("1");
            sysAcc.setSysAccMoney(0);
            sysAcc.setSysAccCdate(new Date());
            sysAcc.setSysAccCuser("用户"+sysAcc.getSysAccName());



            boolean flag =  sendMail(sysAcc.getSysAccEmail(), pwd);

            if(flag){
                sysAccService.insert(sysAcc);
                return getSuccess(true, "注册成功，稍后帐号密码将发送至邮件！");
            }else{
                return getSuccess(false, "激活邮件发送失败，注册失败，稍后再试！");
            }

        } catch (Exception e) {
            log.error(e.getCause().getMessage());
            System.out.println(e.getCause().getMessage());
            return getSuccess(false, "系统异常，注册失败！");
        }
    }


    	@RequestMapping(value="/gettsmSendMessageCode")
	@ResponseBody
	public StringJSON gettsmSendMessageCode(TsmSendMessage tsmSendMessage, HttpServletRequest req) {
		try {
			log.info("<=====执行gettsmSendMessageCode====>");
			//检查数据完整性
            if(!RegexValidateUtil.checkEmail(tsmSendMessage.getTsmSendMobile())){
                    return getSuccess(false, "该邮箱格式不正确,请输入新的邮箱！！");
			}

			SysAcc sysAccphone = new SysAcc();
			sysAccphone.setSysAccEmail(tsmSendMessage.getTsmSendMobile());
			List<SysAcc> list = sysAccService.select(sysAccphone);
			if(list.size() > 0 ){
				if(!list.get(0).getSysAccType().equals("1")){
                    return getSuccess(false, "您的账号并非为移动端用户！");
				}
			}else{
                return getSuccess(false, "您的邮箱未注册！");
			}

			String code = StringUtil.getRandomNumber(4);
			tsmSendMessage.setTsmSendCode(code);
			tsmSendMessage.setTsmSendType("1");
			String flag = tsmSendMessageService.sendMessage(tsmSendMessage);

			if("1".endsWith(flag)){
                return  getSuccess(true, "发送成功，稍后验证码将发送至邮件！");
			}else{
                return getSuccess(false, "发送失败，稍后再试！");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return getSuccess(false, "失败，系统异常，稍后再试！");
		}

	}

    //获取账户流水明细信息
    @RequestMapping(value = "/getCode")
    @ResponseBody
    public StringJSON getCode(TsmSendMessage tsmSendMessage, HttpServletRequest request) {
        try {
            //检查数据完整性
            if(!RegexValidateUtil.checkEmail(tsmSendMessage.getTsmSendMobile())){
                return getSuccess(false, "该邮箱格式不正确,请输入新的邮箱！！");
            }
            //检查数据完整性
            if(tsmSendMessage.getTsmSendCode()==""||tsmSendMessage.getTsmSendCode()==null||!StringUtil.isNumber(tsmSendMessage.getTsmSendCode())){
                return getSuccess(false, "请正确输入验证码！！");
            }
            tsmSendMessage.setTsmSendType("1");
            String flag = tsmSendMessageService.selectLastCode(tsmSendMessage);

            if("1".equals(flag)){
                return  getSuccess(true, "成功");
            }else{
                return getSuccess(false, "无效验证码，请重新获取！");
            }



        } catch (Exception e) {
            log.error(e.getStackTrace());
            return getSuccess(false, "失败，系统异常，稍后再试！");
        }

    }

    // 根据code修改密码
    @RequestMapping(value = "/repwd")
    @ResponseBody
    public StringJSON repwd(String mobile, String oldpwd, String newpwd) {
        try {

            //检查数据完整性
            if(!RegexValidateUtil.checkEmail(mobile)){
                return getSuccess(false, "该邮箱格式不正确,请输入新的邮箱！！");
            }

            if (StringUtils.isBlank(newpwd)) {
                return getSuccess(false, "密码为空");
            }

            SysAcc sysAcc1 = new SysAcc();
            sysAcc1 = sysAccService.getSysAccForLoginByRepwd(mobile, oldpwd);// 验证用户名、密码登录（用于普通登录）
            if (sysAcc1 != null) {
                if("1".equals(sysAcc1.getSysAccType())){
                    String password = MD5.encryByMD5(newpwd);
                    sysAcc1.setSysAccPassword(password);
                    sysAccService.update(sysAcc1);
                    sysAcc1.setSysAccPassword("");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("repwd", sysAcc1);
                    return getSuccess(true, "修改密码成功",map);
                }else{
                    return getSuccess(false, "非移动端用户,请稍后再试");
                }

            } else {
                return getSuccess(false, "原密码错误");
            }
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return getSuccess(false, "系统异常！");
        }

    }

    private boolean chkAccountIsExists(SysAcc sysAcc) {
        Boolean flag = sysAccService.getSysAcc(sysAcc);
        return flag;
    }

    @RequestMapping(value = "/repassword", method = { RequestMethod.POST })
    @ResponseBody
    @com.sme.core.spring.Log(type = "首页", desc = "修改密码")
    public StringJSON sysAccRepassword(String mobile, String oldpwd, String newpwd) {
        try {

            //检查数据完整性
            if(!RegexValidateUtil.checkEmail(mobile)){
                return getSuccess(false, "该邮箱格式不正确,请输入新的邮箱！！");
            }

            if (StringUtils.isBlank(newpwd)) {
                return getSuccess(false, "密码为空");
            }

            SysAcc sysAcc1 = new SysAcc();
            sysAcc1 = sysAccService.getSysAccForLoginRepwd(mobile, oldpwd);// 验证用户名、密码登录（用于普通登录）
            if (sysAcc1 != null) {
                if("1".equals(sysAcc1.getSysAccType())){
                    String password = MD5.encryByMD5(newpwd);
                    sysAcc1.setSysAccPassword(password);
                    sysAccService.update(sysAcc1);
                    sysAcc1.setSysAccPassword("");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("repwd", sysAcc1);
                    return getSuccess(true, "修改密码成功",map);
                }else{
                    return getSuccess(false, "非移动端用户,请稍后再试");
                }

            } else {
                return getSuccess(false, "原密码错误");
            }

        } catch (Exception e) {
            log.error(e.getCause().getMessage());
            return getSuccess(false, "系统异常！");
        }
    }

    public static boolean sendMail(String mail, String pwd){

        //这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        //服务器端口
        mailInfo.setMailServerHost(Config.MailServerHost);
        //或者是通过qq邮箱发送
//        mailInfo.setMailServerHost("smtp.qq.com");
        mailInfo.setMailServerPort(Config.MailServerPort);
        mailInfo.setValidate(true);
        //您的邮箱用户名
        mailInfo.setUserName(Config.UserName);
        //您的邮箱密码
        mailInfo.setPassword(Config.Password);
        //发送邮件源地址
        mailInfo.setFromAddress(Config.FromAddress);
        //发送邮件目的地址
        mailInfo.setToAddress(mail);
        //主题
        mailInfo.setSubject(Config.Subject);
        //内容
        mailInfo.setContent(Config.Content1 + pwd + Config.Content2);
        //这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        Boolean flag = sms.sendTextMail(mailInfo);//发送文体格式
        return  flag;
    }



    @RequestMapping(value = "/download")
    @ResponseBody
    @Pass
    public Object downloadApk(HttpServletRequest request, HttpServletResponse response, String apkName) {

        if(StringUtils.isBlank(apkName)){
            return null;
        }

        PAppDetail pAppDetail = new PAppDetail();pAppDetail.setpAppdetailApk(apkName);
        List<PAppDetail> pAppDetails = pAppDetailService.select(pAppDetail);

        if(pAppDetails.size()!=1){
            return null;
        }
        PApplication pApplication = new PApplication();
        pApplication.setpAppId(pAppDetails.get(0).getpAppdetailApkactionid());
        pApplication = pApplicationService.getById(pApplication);
        pApplication.setpAppOpen(pApplication.getpAppOpen()==null?1:pApplication.getpAppOpen()+1);
        pApplicationService.update(pApplication);

        String path =  Config.DEFAULT_APK_PATH + File.separator + apkName;

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
}
