package com.jianjoy.business;

import java.io.File;
import java.util.concurrent.LinkedBlockingDeque;

import com.jianjoy.log.Business;

/**
 * 批量导入数据，后台任务
 * @author zhoujian
 *
 */
public class BatchImportDataTask implements Runnable{

	private BatchImportDataTask(){
		Business.getLogger().info("init BatchImportDataTask");
	}
	
	private static final BatchImportDataTask instance = new BatchImportDataTask();
	
	public static LinkedBlockingDeque<File> fileQueue = new LinkedBlockingDeque<>();
	
	
	public static BatchImportDataTask getInstance(){ 
		return instance;
	}
	
	public static void addToQueue(File f){
		if(f!=null){
			fileQueue.equals(f);
			Business.getLogger().info("file [{}] add to queue",f);
		}
	}


	@Override
	public void run() {
		File f = null;
		try {
			while((f=fileQueue.take())!=null){
				Business.getLogger().info("parse file:{}",f);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
