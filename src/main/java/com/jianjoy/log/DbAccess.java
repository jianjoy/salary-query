package com.jianjoy.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbAccess {

	private static final Logger logger = LogManager.getLogger(DbAccess.class);
	
	public static Logger getLogger(){
		return logger;
	}
}
