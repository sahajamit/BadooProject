package com.badoo.utils;

import java.io.FileReader;
import java.util.Properties;

public class ConfigManager {
	public static ThreadLocal<Properties> commonProp = new ThreadLocal<Properties>();
	
	public static void setProperties(){
		try{
				commonProp.set(new Properties());
				FileReader reader;
				reader = new FileReader("config.properties");
				commonProp.get().load(reader);
		}catch(Exception e){
			
		}		  
	}
	public static Properties getProperties(){
		return commonProp.get();
	}
	public static Properties UpdateProperties(){
		return commonProp.get();
	}
}
