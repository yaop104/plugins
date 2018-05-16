package com.sme.util;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.sme.service.impl.TsmSendMessageServiceImpl.sendPhone;


/**
 * Created by yaoping on 2018/1/10.
 */
public class HttpSendExecutor {

    private static ExecutorService executor =
            new ThreadPoolExecutor(5, 20, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());

    public static void sendMessageInfo(String mobile, String code) {
        if(StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(code)){
            executor.submit(() -> sendPhone(mobile, code));
        }
    }
}
