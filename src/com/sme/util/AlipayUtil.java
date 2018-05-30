package com.sme.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
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

    public static void main(String[] args) throws AlipayApiException {

        String result = "{\"alipay_trade_app_pay_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2018051860110836\",\"auth_app_id\":\"2018051860110836\",\"charset\":\"utf-8\",\"timestamp\":\"2018-05-28 14:56:48\",\"total_amount\":\"0.01\",\"trade_no\":\"2018052821001004780577715275\",\"seller_id\":\"2088721562460647\",\"out_trade_no\":\"1527490596200911021\"},\"sign\":\"ApxVIH1Y2oh060ScQbwgh+HyUr9HQBHLOc2y03T23GJQfoDRIbE5MH2A4V73ZL/IkuGqvJiarS+QXhd+PCw/gDC5AgCwVNu2L4z9uBZI3A1VVNddALOkp8/EH0RqM7v1JDET4nAQQL51XPvGZ1H2QL+1Fr5/A/X5Cyi5yZxm1yR443IcXSyE84xL4I52xbCuRqhDJyn351MaLYAcEeCE7+iDOzNNwLWIoTtHo+3WZux+Fi/o3qmCmvE9O6M5Dj0MagX4zWyml5yoLcHwrCJiXoc7KnBkMJQU4QHTsjxwV92fk8tSVEtfXCyNiLMiC6wnY+fSZqKhSV2p5r3/q3zFlA==\",\"sign_type\":\"RSA2\"}";

        Map map = (Map) JSON.parse(result, Feature.OrderedField);

        System.out.println("start of encry data...");
        boolean flag = AlipayUtil.rsaCheckContent(map.get("alipay_trade_app_pay_response").toString(), map.get("sign").toString());
        System.out.println("end of encry data..." + flag);

    }
}
