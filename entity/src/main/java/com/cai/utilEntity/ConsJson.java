package com.cai.utilEntity;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultDefaultValueProcessor;

import java.util.Date;

public class ConsJson {

	public ConsJson() {
		// TODO Auto-generated constructor stub
	}

	public static String Object2StringJson(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerDefaultValueProcessor(Date.class, new DefaultDefaultValueProcessor() {
			public Object getDefaultValue(Class type) {
				return null;
			}
		});
		jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultDefaultValueProcessor() {
			public Object getDefaultValue(Class type) {
				return null;
			}
		});
		jsonConfig.registerDefaultValueProcessor(String.class, new DefaultDefaultValueProcessor() {
			public Object getDefaultValue(Class type) {
				return null;
			}
		});
		JSONObject json = JSONObject.fromObject(obj, jsonConfig); // 将java对象转换为json对象
		String str = json.toString();// 将json对象转换为字符串
		return str;
	}

}
