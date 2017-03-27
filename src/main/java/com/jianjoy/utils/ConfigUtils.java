package com.jianjoy.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 配置文件工具类
 * @author zhoujian
 *
 */
public class ConfigUtils {

	private static final String name = "conf.properties";
	private static volatile ConcurrentHashMap<String, String> PROPERTIES = new ConcurrentHashMap<>();

	static {
		init(PROPERTIES);
	}

	private static void init(ConcurrentHashMap<String, String> configMap) {
		try {
			// 先加载配置文件
			Properties properties = PropertiesLoaderUtils.loadAllProperties(name);
			Iterator<Object> keys = properties.keySet().iterator();
			while (keys.hasNext()) {
				Object keyObject = keys.next();
				String key = keyObject.toString();
				configMap.put(key, (String) properties.get(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getConfig(String key) {
		return PROPERTIES.get(key);
	}

	

	public static Map<String, String> getConfigByStartWithWords(String start) {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> keys = PROPERTIES.keySet().iterator();
		while (keys.hasNext()) {
			Object keyObject = keys.next();
			String key = keyObject.toString();
			if (key.startsWith(start)) {
				map.put(key, PROPERTIES.get(key));
			}
		}
		return map;
	}

	public static String getAllConfig() {
		return PROPERTIES.toString();
	}

}
