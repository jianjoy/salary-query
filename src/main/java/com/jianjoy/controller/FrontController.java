package com.jianjoy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jianjoy.business.AccountBusinessImpl;
import com.jianjoy.business.BatchImportDataTask;
import com.jianjoy.business.IAccountBusiness;
import com.jianjoy.log.Business;
import com.jianjoy.model.Account;
import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.FileMeta;
import com.jianjoy.model.FrontApiResponse;
import com.jianjoy.utils.ClientIpUtils;
import com.jianjoy.utils.ConfigUtils;

@Controller
@RequestMapping(value = "/front")
public class FrontController {

	static{
		new Thread(BatchImportDataTask.getInstance(),"BatchImportDataTask").start();
	}
	
	private IAccountBusiness accountBiz = new AccountBusinessImpl();

	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/login.do", method = {RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String ip = ClientIpUtils.getRemoteIp(request);
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		BusinessResult<Account> result = accountBiz.login(user, pass, ip);
		if(result.getData()!=null){
			HttpSession session = request.getSession(true);
			session.setAttribute("x-token", UUID.randomUUID().toString());
			session.setAttribute("accountRole", result.getData());
		}
		return renderJson(getResult(result));
	}
	
	/**
	 * 路由控制
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/api/index.do", method = {RequestMethod.POST,RequestMethod.GET })
	public static ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		String authToken = (String)request.getSession(true).getAttribute("x-token");
		String viewName = request.getParameter("t");
		if(authToken!=null&&authToken.trim().length()>0){
			mv.setViewName(viewName!=null&&viewName.trim().length()>0?"/"+viewName:"/index");
		}else{
			mv.setViewName("/login");
		}
		return mv;
	}

	
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/logout.do", method = {RequestMethod.POST,RequestMethod.GET })
	public static ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session!=null){
		  session.invalidate();
		}
		return new ModelAndView("/login");
	}
	
	private static FrontApiResponse getErrorResult(String msg) {
		FrontApiResponse response = new FrontApiResponse();
		response.setStatus("error");
		response.setMessage(msg);
		return response;
	}
	
	private static <T> FrontApiResponse getResult(BusinessResult<T> result) {
		FrontApiResponse response = new FrontApiResponse();
		if (result.getError() != null) {
			response.setStatus("error");
			response.setMessage(result.getError());
			result.setError(null);
		}
		response.setResult(result);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/upload.do", method = RequestMethod.POST)
	public LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response){
		LinkedList<FileMeta> files = new LinkedList<>();
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		FileMeta fileMata = null;
		while(itr.hasNext()){
			mpf = request.getFile(itr.next());
			Business.getLogger().info("upload file {}",mpf.getOriginalFilename());
			if(files.size()>10){
				files.pop();
			}
			fileMata = new FileMeta();
			fileMata.setFileName(mpf.getOriginalFilename());
			fileMata.setFileSize(mpf.getSize()/1024+"kb");
			fileMata.setFileType(mpf.getContentType());
			try{
				fileMata.setBytes(mpf.getBytes());
				File outputFile = new File(ConfigUtils.getConfig("uploadFileDir")+"/"+mpf.getOriginalFilename());
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(outputFile));
			    files.add(fileMata);
			}catch(IOException e){
				Business.getLogger().error(e);
			}
		}
		return files;
	}
	
	/**
	 * @see http://www.simplecodestuffs.com/pagination-in-java-web-applications-using-jquery-jtable-plugin/
	 * @param obj
	 * @return
	 */
	
	
	/**
	 * @see http://hmkcode.com/spring-mvc-jquery-file-upload-multiple-dragdrop-progress/
	 * @param obj
	 * @return
	 */
	
	public static String renderJson(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
	}

}
