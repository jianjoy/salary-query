package com.jianjoy.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Business {

	private static Logger logger = LogManager.getLogger(Business.class);
	
	
	public static Logger getLogger(){
		return logger;
	}
	
}
