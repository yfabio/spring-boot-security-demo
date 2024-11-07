package com.tech.pro.utils;

import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PwdBuffer {

	private Properties prop = new Properties();

	public void save(String name, String pwd) {
		prop.put(name, pwd);
	}

	public String get(String name) {
		return prop.getProperty(name);
	}

}
