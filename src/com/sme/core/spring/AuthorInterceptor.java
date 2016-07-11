package com.sme.core.spring;

import com.sme.util.Pass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 拦截类
 * 防止非法访问
 * @author haoy
 *
 */
public class AuthorInterceptor extends HandlerInterceptorAdapter {

	private Logger log = LoggerFactory.getLogger(AuthorInterceptor.class);

	private static List<String> ignoreRequest = new ArrayList<>();

	static {
		ignoreRequest.add("login.do");
		ignoreRequest.add("register.do");
		ignoreRequest.add("/outInterface");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Pass pass = method.getAnnotation(Pass.class);
            if (pass != null) {
                return true;
            }
        }

		String uri = request.getRequestURI();

		if (log.isDebugEnabled()) {
			log.debug("拦截请求：" + uri);
		}
		
		//登陆请求不需要拦截
		for(String url : ignoreRequest) {
			if (uri.contains(url)) {
				return true;
			}
		}
		
		Object session = request.getSession().getAttribute("loginUser");
		if (session == null) {
			//非法访问 转到登陆页面
			request.getRequestDispatcher("").forward(request, response);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}

}
