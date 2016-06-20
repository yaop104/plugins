package com.sme.util;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSON;
import com.sme.entity.Msgcontent;
import com.sme.entity.OuterAPPInfo;
import com.sme.entity.PlgSend;
import com.sme.service.MsgcontentService;
import com.sme.service.PlgSendService;


/**
 * 自动刷新app数据标示
 * @author yao
 *
 */

public class RefreshInterfaceFlag extends QuartzJobBean {
	

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
			try {
				String apkString = ReadProperties.getString("config", "apk.path");
				PlgSendService plgSendServiceImpl =  (PlgSendService)SpringContextUtil.getBean("plgSendServiceImplContext");
				MsgcontentService msgcontentServiceImpl =  (MsgcontentService)SpringContextUtil.getBean("msgcontentServiceImplContext");
				
				PlgSend pSend = new PlgSend();
				pSend.setPsdIssend("0");
				
				Msgcontent msgcontent = new Msgcontent();
				msgcontent.setSystemmode(4);
				msgcontent.setUserid(3L);
				msgcontent.setIsAdd(2);
				msgcontent.setType(3);
				
				List<PlgSend> list = plgSendServiceImpl.selectForSend(pSend);
				
				for (PlgSend plgSend : list)
				{
					msgcontent.setTouserids(plgSend.getPsdAccount());
					
					OuterAPPInfo oAppInfo = new OuterAPPInfo();
					oAppInfo.setVersionName(plgSend.getPsdPlgname());
					oAppInfo.setVersionNo(plgSend.getPsdPlgversion());
					oAppInfo.setSize(plgSend.getPsdsize());
					oAppInfo.setUrl(apkString + plgSend.getPsdPlgurl());
					oAppInfo.setReleaseNote(plgSend.getPsddesc());
					
					String oAppInfoString = JSON.toJSONString(oAppInfo);
					
					msgcontent.setExtra(oAppInfoString);
					
					msgcontentServiceImpl.insert(msgcontent);
					
					plgSendServiceImpl.updateForSend(plgSend.getPsdUnid());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}

