package com.sme.view;

import com.sme.core.model.StringJSON;
import com.sme.entity.PApplication;
import com.sme.entity.TdcDictionary;
import com.sme.service.PApplicationService;
import com.sme.service.impl.TdcDictionaryServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
            List<PApplication> pAppDetails = pApplicationService.select(pApplication);
            return getSuccess(true,"",pAppDetails);
        } catch (Exception e) {
            log.error(e.getMessage());
            return getSuccess(false,"app列表获取失败,系统异常！！");
        }

    }

}
