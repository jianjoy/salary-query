package com.jianjoy.utils;

/**
 * 字符串工具
 * @author zhoujian
 *
 */
public class StringUtils {

	/**
	 * 字符串为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str==null||str.trim().length()==0;
	}
	
	
	/**
	 * 字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str){
		return !isEmpty(str);
	}
	
	
	
	
}
