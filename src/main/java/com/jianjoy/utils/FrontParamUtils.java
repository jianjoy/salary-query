package com.jianjoy.utils;

import javax.servlet.ServletRequest;

/**
 * 请求参数处理工具类
 * @author zhoujian
 *
 */
public class FrontParamUtils {

	public static boolean required(String[] keys, ServletRequest request) {
		boolean result = true;
		for (String k : keys) {
			String value = request.getParameter(k);
			if (value == null || value.trim().length() == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

}
