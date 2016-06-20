package com.sme.core.spring;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sme.entity.OperateLog;
import com.sme.entity.SysAcc;
import com.sme.service.OperateLogService;

public class OperateLogAspect {
	
	@Autowired
	private OperateLogService operateLogService;

	public void doAfter(JoinPoint jp) {
		
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		Signature sign = jp.getSignature();
		
		MethodSignature methodSign = (MethodSignature) sign;
		Method method = methodSign.getMethod();					//获取目标对象的方法
		
		
		Log log = method.getAnnotation(Log.class);
		
		if (log != null) {
			OperateLog operateLog = new OperateLog();
			
			SysAcc session = (SysAcc) req.getSession().getAttribute("loginUser");
			if (session != null) {
				operateLog.setAccId(session.getSysAccId());
			}
			
			operateLog.setOperateType(log.type());
			operateLog.setOperateDes(log.desc());
			
			operateLogService.insert(operateLog);
		}
		
	}
}
