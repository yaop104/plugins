package com.sme.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;

import java.util.Map;

import static com.alipay.api.internal.util.AlipaySignature.getSignContent;
import static com.sme.util.Config.*;

/**
 * Created by yaoping on 2018/5/27.
 */
public class AlipayUtil {

    private  static  AlipayClient client = null;

    public static AlipayClient getAlipayClient() {
        if(client == null){
            client = new DefaultAlipayClient(alipayGateway,alipayAppId,alipayPrivateKey,alipayFormat,alipayCharset,alipayPublicKey,alipayAignType);

        }
        return client;
    }

    /**
     * 功能：RSA签名
     输入：params 待签名参数map
     privateKey 私钥
     charset 签名编码格式
     输出：签名结果
     * @param params
     * @return
     * @throws AlipayApiException
     */
    public static String rsaSign(Map<String, String> params) throws AlipayApiException {
        String signContent = getSignContent(params);
       return AlipaySignature.rsaSign(signContent, alipayPrivateKey, alipayCharset, alipayAignType);
    }


    /**
     *功能：RSA验签
     输入：params 签名参数内容map
     publicKey 公钥
     charset 签名编码格式
     输出：验签结果
     * @param params
     * @return
     * @throws AlipayApiException
     */
    public static boolean rsaCheckV2(Map<String, String> params) throws AlipayApiException {
        return AlipaySignature.rsaCheckV2(params, alipayPublicKey, alipayCharset, alipayAignType);
    }

    /**
     *功能：RSA验签
     输入：content 签名参数内容字符串
     sign 签名
     publicKey 公钥
     charset 签名编码格式
     输出：验签结果
     * @param content
     * @return
     * @throws AlipayApiException
     */
    public static boolean rsaCheckContent(String content, String sign) throws AlipayApiException {
        return AlipaySignature.rsaCheck(content, sign, alipayPublicKey, alipayCharset, alipayAignType);
    }
}
