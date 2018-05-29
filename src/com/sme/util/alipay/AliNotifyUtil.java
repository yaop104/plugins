package com.sme.util.alipay;

import com.alibaba.fastjson.JSON;
import com.sme.entity.alipay.AppPayResponse;
import com.sme.entity.alipay.CheckSignWithApp;

import java.io.UnsupportedEncodingException;

import static com.sme.util.Config.alipayCharset;
import static com.sme.util.Config.alipayPublicKey;

/**
 * Created by yaoping on 2018/5/28.
 */
public class AliNotifyUtil {

    /**
     * 签名验证通过后
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号；2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）；3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）；4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明同步校验结果是无效的，只有全部验证通过后，才可以认定买家付款成功。
     * @param checkSignWithApp
     * @return
     * @throws UnsupportedEncodingException
     */
    public  static boolean tradeAppPayResponse(CheckSignWithApp checkSignWithApp) throws UnsupportedEncodingException {

        AppPayResponse appPayResponse = checkSignWithApp.getAlipay_trade_app_pay_response();

        String sign = checkSignWithApp.getSign();

        String signType = checkSignWithApp.getSign_type();

        byte[] signBytes = sign.getBytes(alipayCharset);

        return SignUtil.verify256(JSON.toJSONString(appPayResponse), signBytes, alipayPublicKey);
    }
}
