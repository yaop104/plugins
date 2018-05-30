package com.sme.view;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alipay.api.AlipayApiException;
import com.sme.core.model.StringJSON;
import com.sme.entity.*;
import com.sme.entity.alipay.AppPayResponse;
import com.sme.entity.item.SignWithApp;
import com.sme.service.*;
import com.sme.service.impl.TdcDictionaryServiceImpl;
import com.sme.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.sme.util.Config.*;
import static com.sme.util.StringUtil.timeStampString;

/**
 * Created by yao on 2016/7/11.
 */
@Controller
@RequestMapping("/outInterface")
public class OutInterfaceController {

    @Autowired
    private TdcDictionaryServiceImpl tdcDictionaryServiceImpl;
    @Autowired
    private SysAccService sysAccService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    public FeedbackService feedbackService;
    @Autowired
    private TsmSendMessageService tsmSendMessageService;
    @Autowired
    private FruitItemService fruitItemServiceImpl;
    @Autowired
    private FruitOrderService fruitOrderServiceImpl;
    @Autowired
    private FruitOrderLogService fruitOrderLogServiceImpl;

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

    /**
     * 字符串转换成JSON
     *
     * @param code
     * @param message
     * @return
     */
    protected RespMessage respMessage(String code, String message, Object data) {
        RespMessage json = new RespMessage();
        json.setCode(code);
        json.setMessage(message);
        json.setData(data);
        return json;
    }

    /**
     * 字符串转换成JSON
     *
     * @param code
     * @param message
     * @return
     */
    protected RespMessage respMessage(String code, String message) {
        RespMessage json = new RespMessage();
        json.setCode(code);
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

            Map<String, Object> map = new HashMap<>();

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
            img = HEAD_IMG_REALPATH + "download/pic/" + img;
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
    public RespMessage login(@RequestBody LoginDTO loginDTO){
        try
        {
            SysAcc sysAcc = new SysAcc();
            //是否增加ip限制
            if(StringUtils.isNotBlank(loginDTO.getAccount()) && StringUtils.isNotBlank(loginDTO.getPassword())){
                sysAcc = sysAccService.getSysAccForLogin(loginDTO.getAccount(), loginDTO.getPassword());//验证用户名、密码登录（用于普通登录）
                if(sysAcc != null){

                    //登陆成功 记录登陆日志
                    LoginLog log = new LoginLog();
                    log.setAccId(sysAcc.getSysAccId());
                    log.setLoginIp(sysAcc.getSysAccMobile());
                    loginLogService.insert(log);
                    //生成token
                    String token = StringUtil.generateUUID(false);
                    sysAcc.setSysAccUdate(new Date());
                    sysAcc.setSysAccToken(token);
                    sysAccService.update(sysAcc);

                    Map<String, Object> map = new HashMap<>();
                    map.put("token",token);
                    return respMessage("1","",map);

                }else{
                    return respMessage("-1","用户名或密码错误！");
                }

            }else{
                return respMessage("-1","用户名或密码错误！");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return respMessage("-1","失败，系统异常，尝试重新登入！");
        }

    }

    @RequestMapping(value="/loginOut", method={RequestMethod.POST})
    @ResponseBody
    public RespMessage loginOut(@RequestBody LoginDTO loginDTO){
        try
        {
            SysAcc sysAcc = new SysAcc();
            //是否增加ip限制
            if(StringUtils.isNotBlank(loginDTO.getAccToken())){
                sysAcc.setSysAccToken(loginDTO.getAccToken());
                sysAcc = sysAccService.getSysAccByToken(sysAcc);//验证用户名、密码登录（用于普通登录）
                if(sysAcc != null){

                    //清除token
                    sysAcc.setSysAccUdate(new Date());
                    sysAcc.setSysAccToken("");
                    sysAccService.update(sysAcc);

                    Map<String, Object> map = new HashMap<>();
                    return respMessage("1","登出成功",  map);

                }else{
                    return respMessage("-1","登出失败！");
                }

            }else{
                return respMessage("-1","登出失败！");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return respMessage("-1","失败，系统异常，重新尝试！");
        }

    }

    @RequestMapping(value="/getIFruittems", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public  RespMessage getIFruittems(@RequestBody FruitItem fruitItem) {
        try {
            log.info("<=====getIFruittems====>");
            // 分页属性
            if (fruitItem.getRows() < 1) {
                 fruitItem.setRows(10);
            }
            if(fruitItem.getPage() < 1){
                fruitItem.setPage(1);
            }


            Map<String, Object> parm = new HashMap<String, Object>();
            parm.put("page", fruitItem.getBegin());
            parm.put("pageCount",fruitItem.getEnd());


            int count = fruitItemServiceImpl.count(parm);
            List<FruitItem> fruitItems = fruitItemServiceImpl.page(parm);
            if(CollectionUtils.isNotEmpty(fruitItems)){

                for (FruitItem fruitItem1 : fruitItems) {
                    if(StringUtils.isNotBlank(fruitItem1.getItemPic())){
                        fruitItem1.setItemPic(HEAD_IMG_REALPATH+fruitItem1.getItemPic());
                    }
                    fruitItem1.setItemSaleInfo(fruitItem1.getItemSaleInfo() + (fruitItem1.getItemSaleType().equals(2)?"/个":"/份"));
                }

            }
            return respMessage("1","获取成功",  RespUtil.pageResult(count, fruitItems));
        } catch (Exception e) {
            log.error(e.getMessage());
            return respMessage("1","获取成功",  RespUtil.pageResult(0, new ArrayList()));
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


    @RequestMapping(value = "/register", method = { RequestMethod.POST })
    @ResponseBody
    @com.sme.core.spring.Log(type = "首页", desc = "用户注册")
    public RespMessage sysAccRegister(@RequestBody SysAcc sysAcc) {
        try {

            SysAcc sysAccnames=new SysAcc();
            sysAccnames.setSysAccName(sysAcc.getSysAccName());
            if(chkAccountIsExists(sysAccnames)){
                return respMessage("-1", "该用户名已存在,请重新输入用户名！！");
            }

            if(!RegexValidateUtil.checkMobileNumber(sysAcc.getSysAccMobile())){
                return respMessage("-1", "手机格式不正确,请重新输入");
            }
//            if(!StringUtil.isEmpty(sysAcc.getSysAccMobile())){
//                SysAcc sysAccphone = new SysAcc();
//                sysAccphone.setSysAccMobile(sysAcc.getSysAccMobile());
//                if(chkAccountIsExists(sysAccphone)){
//                    return getSuccess(false, "该手机号码已存在,请输入未注册手机号码！！");
//                }
//            }

            //检查数据完整性
            if(sysAcc.getTsmSendCode()==""||sysAcc.getTsmSendCode()==null||!StringUtil.isNumber(sysAcc.getTsmSendCode())){
                return respMessage("-1", "请正确输入验证码！！");
            }

            if (StringUtils.isBlank(sysAcc.getFirstPassword())) {
                return respMessage("-1", "密码为空");
            }

            if (!sysAcc.getFirstPassword().equals(sysAcc.getSecondPassword())) {
                return respMessage("-1", "两次密码不一致");
            }

            TsmSendMessage tsmSendMessage = new TsmSendMessage();
            tsmSendMessage.setTsmSendType("1");
            tsmSendMessage.setTsmSendMobile(sysAcc.getSysAccMobile());
            tsmSendMessage.setTsmSendCode(sysAcc.getTsmSendCode());
            String flag = tsmSendMessageService.selectLastCode(tsmSendMessage);

            if(!"1".equals(flag)){
                return respMessage("-1", "无效验证码，请重新输入！");
            }



            String password = MD5.encryByMD5(sysAcc.getFirstPassword());
            sysAcc.setSysAccPassword(password);
            sysAcc.setSysAccState("1");
            sysAcc.setSysAccType("1");
            sysAcc.setSysAccUserType("2");
            sysAcc.setSysAccMoney(0);
            sysAcc.setSysAccCdate(new Date());
            sysAcc.setSysAccDesc(sysAcc.getFirstPassword());
            sysAcc.setSysAccCuser("用户:"+sysAcc.getSysAccName());

            sysAccService.insert(sysAcc);
            return respMessage("1", "注册成功！");

        } catch (Exception e) {
            log.error(e.getCause().getMessage());
            System.out.println(e.getCause().getMessage());
            return respMessage("-1", "系统异常，注册失败！");
        }
    }


    @RequestMapping(value="/getCode")
    @ResponseBody
    public RespMessage getCode(@RequestBody TsmSendMessage tsmSendMessage) {
        try {
            log.info("<=====getCode====>");
            //检查数据完整性
            if(!RegexValidateUtil.checkMobileNumber(tsmSendMessage.getTsmSendMobile())){
                return respMessage("-1", "手机格式不正确,请重新输入");
            }

            String code = StringUtil.getRandomNumber(4);
            tsmSendMessage.setTsmSendCode(code);
            tsmSendMessage.setTsmSendType("1");

            tsmSendMessageService.sendMessage(tsmSendMessage);

            return  respMessage("1", "发送成功，稍后验证码将发送至手机！");

        } catch (Exception e) {
            log.error(e.getMessage());
            return respMessage("-1", "失败，系统异常，稍后再试！");
        }

    }

    //验证验证码
    @RequestMapping(value = "/checkCode")
    @ResponseBody
    public RespMessage checkCode(@RequestBody TsmSendMessage tsmSendMessage) {
        try {
            //检查数据完整性
            if(!RegexValidateUtil.checkMobileNumber(tsmSendMessage.getTsmSendMobile())){
                return respMessage("-1", "该邮箱格式不正确,请输入新的邮箱！！");
            }
            //检查数据完整性
            if(tsmSendMessage.getTsmSendCode()==""||tsmSendMessage.getTsmSendCode()==null||!StringUtil.isNumber(tsmSendMessage.getTsmSendCode())){
                return respMessage("-1", "请正确输入验证码！！");
            }
            tsmSendMessage.setTsmSendType("1");
            String flag = tsmSendMessageService.selectLastCode(tsmSendMessage);

            if("1".equals(flag)){
                return  respMessage("1", "成功");
            }else{
                return respMessage("-1", "无效验证码，请重新输入！");
            }



        } catch (Exception e) {
            log.error(e.getStackTrace());
            return respMessage("-1", "失败，系统异常，稍后再试！");
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


    /**
     *订单列表
     * @param order
     * @return
     */
    @RequestMapping(value="/getOrderList")
    @ResponseBody
    public RespMessage getOrderList(@RequestBody FruitOrder order) {
        try {
            log.info("<=====getOrderList====>");
            SysAcc sysAcc = getUserByToken(order.getToken());
            if(sysAcc == null){
                return  respMessage("2", "");
            }
            // 分页属性
            if (order.getRows() < 1) {
                order.setRows(10);
            }
            if(order.getPage() < 1){
                order.setPage(1);
            }


            Map<String, Object> parm = new HashMap<String, Object>();
            parm.put("page", order.getBegin());
            parm.put("pageCount",order.getEnd());
            parm.put("userId", sysAcc.getSysAccId());


            int count = fruitOrderServiceImpl.count(parm);
            List<FruitOrder> fruitOrders = fruitOrderServiceImpl.page(parm);

            return respMessage("1","获取成功",  RespUtil.pageResult(count, fruitOrders));
        } catch (Exception e) {
            log.error(e.getMessage());
            return respMessage("1","获取成功",  RespUtil.pageResult(0, new ArrayList()));
        }

    }

    /**
     * 下订单
     * @param orderItem
     * @return
     */
    @RequestMapping(value="/addOrder")
    @ResponseBody
    @Transactional
    public RespMessage addOrder(@RequestBody OrderItem orderItem) {
        try {
            log.info("<=====addOrder====>");
            SysAcc sysAcc = getUserByToken(orderItem.getToken());
            if(sysAcc == null){
                return  respMessage("2", "");
            }
            //检查数据完整性

            String ooid = StringUtil.generateOrderId(sysAcc.getSysAccId());

            //生成签名
            OrderDTO orderDTO = getOrderDTO(orderItem, sysAcc, ooid);

            FruitOrder fruitOrder = buildOrder(orderItem, sysAcc, ooid);

            fruitOrderServiceImpl.insert(fruitOrder);

            return  respMessage("1", "成功", orderDTO);

        } catch (Exception e) {
            log.error(e.getMessage());
            return respMessage("-1", "失败，系统异常，稍后再试！");
        }

    }

    private FruitOrder buildOrder(OrderItem orderItem, SysAcc sysAcc, String ooid) {
        FruitOrder fruitOrder = new FruitOrder();
        fruitOrder.setId(ooid);
        fruitOrder.setHidden(1);
        fruitOrder.setOrderStatus(1);
        fruitOrder.setReceiverName(orderItem.getReceiverName());
        fruitOrder.setReceiverMobile(orderItem.getReceiverMobile());
        fruitOrder.setReceiverAddress(orderItem.getReceiverAddress());
        fruitOrder.setNotes(orderItem.getNotes());
        fruitOrder.setCreateTime(new Date());
        fruitOrder.setOrderPrice(orderItem.getOrderPrice());
        fruitOrder.setRealpay(orderItem.getOrderPrice());
        fruitOrder.setQuantity(orderItem.getQuantity());
        fruitOrder.setSellerId(1);
        fruitOrder.setUserId(sysAcc.getSysAccId());
        fruitOrder.setSnapshot(JSON.toJSONString(orderItem));
        fruitOrder.setDescription(orderItem.getDescription());
        return fruitOrder;

    }

    private OrderDTO getOrderDTO(OrderItem orderItem, SysAcc sysAcc, String ooid) {
        OrderContent content = new OrderContent();
        content.setBody(ooid);
        content.setOut_trade_no(ooid);
        content.setSubject(ooid);
        content.setProduct_code(QUICK_MSECURITY_PAY);
        content.setTotal_amount(orderItem.getOrderPrice());
        String contentString = JSON.toJSONString(content);
        OrderDTO orderBase = new OrderDTO();
        orderBase.setApp_id(alipayAppId);
        orderBase.setMethod(alipayMethod);
        orderBase.setFormat(alipayFormat);
        orderBase.setCharset(alipayCharset);
        orderBase.setSign_type(alipayAignType);
        orderBase.setTimestamp(timeStampString());
        orderBase.setVersion(alipayVersion);
        orderBase.setNotify_url(alipayNotifyUrl);
        orderBase.setBiz_content(contentString);
        try {
            String sign = AlipayUtil.rsaSign(BeanToMapUtil.convertBean(orderBase));
            orderBase.setSign(sign);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return orderBase;

    }

    /**
     * 回调订单
     * @param aliResultDTO
     * @return
     */
    @RequestMapping(value="/notifyOrder", method={RequestMethod.GET , RequestMethod.POST})
    @ResponseBody
    public void notifyOrder(@RequestBody SignWithApp aliResultDTO, HttpServletRequest req) {
        try {
            log.info("<=====notifyOrder====>" +  aliResultDTO.toString());
            log.info("<=====req====>" +  req.toString());
            buildAliPayLog(aliResultDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 更新订单
     * @param aliResultDTO
     * @return
     */
    @RequestMapping(value="/updateOrderStatus")
    @ResponseBody
    public RespMessage updateOrderStatus(@RequestBody SignWithApp aliResultDTO) {
        try {
            log.info("<=====updateOrderStatus====> " + aliResultDTO.toString());

            SysAcc sysAcc = getUserByToken(aliResultDTO.getToken());
            if(sysAcc == null){
                return  respMessage("2", "");
            }
            buildAliPayLog(aliResultDTO);
            String result =  aliResultDTO.getAlipay_trade_app_pay_response();
            AppPayResponse checkSignWithApp = null;
            boolean flag = false;
            boolean resultStatusFlag = "9000".equals( aliResultDTO.getResultStatus());

            if(StringUtils.isNotBlank(result)){
                checkSignWithApp = JSON.parseObject(result, AppPayResponse.class);
           }

            String responseAll =  aliResultDTO.getResult();
            if(StringUtils.isNotBlank(responseAll)){

                Map map = (Map) JSON.parse(responseAll, Feature.OrderedField);

                flag = AlipayUtil.rsaCheckContent(map.get("alipay_trade_app_pay_response").toString(), map.get("sign").toString());
            }

            //检查数据完整性
            if(resultStatusFlag && flag && checkSignWithApp != null  && checkSignWithApp.getOut_trade_no() !=null ){

                FruitOrder fruitOrder = new FruitOrder();
                fruitOrder.setId(checkSignWithApp.getOut_trade_no());
                fruitOrder.setOrderStatus(3);
                fruitOrderServiceImpl.update(fruitOrder);
                return  respMessage("1", "支付成功");
            }else{
                return  respMessage("-1", "支付失败");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return respMessage("-1", "失败，系统异常，稍后再试！");
        }


    }

    private void buildAliPayLog(SignWithApp aliResultDTO){
        FruitOrderLog fruitOrderLog = new FruitOrderLog();
        //原始数据
        fruitOrderLog.setOrderData(JSON.toJSONString(aliResultDTO));
        try {
            //解析数据
            fruitOrderLog.setPayStatus(aliResultDTO.getResultStatus());
            fruitOrderLog.setCreateTime(new Date());
            String result =  aliResultDTO.getAlipay_trade_app_pay_response();
            if(StringUtils.isNotBlank(result)){
                AppPayResponse appPayResponse = JSON.parseObject(result, AppPayResponse.class);
                fruitOrderLog.setCode(appPayResponse.getCode());
                fruitOrderLog.setMsg(appPayResponse.getMsg());
                fruitOrderLog.setSellerId(appPayResponse.getSeller_id());
                fruitOrderLog.setUserPayTime(appPayResponse.getTimestamp());
                fruitOrderLog.setTradeNo(appPayResponse.getTrade_no());
                fruitOrderLog.setOutTradeNo(appPayResponse.getOut_trade_no());
                fruitOrderLog.setTotalAmount(appPayResponse.getTotal_amount());
            }
        }catch (Exception e){
            log.error("记录日志异常，原始数据：" + JSON.toJSONString(aliResultDTO));
        }

        fruitOrderLogServiceImpl.insert(fruitOrderLog);
    }

    public SysAcc getUserByToken(String token){
        if(StringUtils.isBlank(token)){
            return null;
        }

        SysAcc sysAcc = new SysAcc();
        sysAcc.setSysAccToken(token);
        sysAcc = sysAccService.getSysAccByToken(sysAcc);
        if(sysAcc == null || sysAcc.getSysAccId() == null || sysAcc.getSysAccId() < 0 ){
            return null;
        }

        return sysAcc;

    }

}