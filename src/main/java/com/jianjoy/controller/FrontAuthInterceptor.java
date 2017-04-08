package com.jianjoy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jianjoy.model.BusinessResult;

/**
 * 登录权限拦截器
 * @author zhoujian
 *
 */
public class FrontAuthInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 处理未登录拦截控制请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("x-token") == null) {
			response.setStatus(HttpStatus.SC_OK);
			BusinessResult<Boolean> errorResult = new BusinessResult<>();
			errorResult.setData(Boolean.FALSE);
			errorResult.setError("AUTH_FAILED");
			response.getWriter().write(FrontController.renderJson(errorResult));
			return false;
		}
		return true;
	}

}
