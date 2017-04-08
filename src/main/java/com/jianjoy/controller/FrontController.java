package com.jianjoy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jianjoy.business.AccountBusinessImpl;
import com.jianjoy.business.BatchImportDataTask;
import com.jianjoy.business.EmployeBusinessImpl;
import com.jianjoy.business.IAccountBusiness;
import com.jianjoy.business.IEmployeeBusiness;
import com.jianjoy.business.ILoginLogBusiness;
import com.jianjoy.business.ISalaryInfoBusiness;
import com.jianjoy.business.LoginLogBusinessImpl;
import com.jianjoy.business.SalaryInfoBusinessImpl;
import com.jianjoy.log.Business;
import com.jianjoy.model.Account;
import com.jianjoy.model.AccountRoleType;
import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.EmployeeInfo;
import com.jianjoy.model.FileMeta;
import com.jianjoy.model.FrontApiResponse;
import com.jianjoy.model.Pager;
import com.jianjoy.utils.ClientIpUtils;
import com.jianjoy.utils.ConfigUtils;
import com.jianjoy.utils.Md5Utils;
import com.jianjoy.utils.StringUtils;
import com.sun.mail.imap.protocol.BODY;

/**
 * api 服务
 * @author zhoujian
 *
 */
@Controller
@RequestMapping(value = "/front")
public class FrontController {

	static {
		//后台处理薪资信息批量保存任务
		new Thread(BatchImportDataTask.getInstance(), "BatchImportDataTask").start();
	}

	/**
	 * 账户业务服务
	 */
	private IAccountBusiness accountBiz = new AccountBusinessImpl();

	/**
	 * 登录日志业务服务
	 */
	private ILoginLogBusiness loginLogBiz = new LoginLogBusinessImpl();

	/**
	 * 薪资信息服务
	 */
	private ISalaryInfoBusiness salaryInfoBiz = new SalaryInfoBusinessImpl();

	/**
	 * 员工信息服务
	 */
	private IEmployeeBusiness employeeBiz = new EmployeBusinessImpl();

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/login.do", method = { RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String ip = ClientIpUtils.getRemoteIp(request);
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		BusinessResult<Account> result = accountBiz.login(user, pass, ip);
		if (result.getData() != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("x-token", UUID.randomUUID().toString());
			session.setAttribute("accountRole", result.getData());
		}
		return renderJson(getResult(result));
	}

	/**
	 * 路由控制
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/api/index.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String authToken = (String) request.getSession(true).getAttribute("x-token");
		String viewName = request.getParameter("t");
		if(viewName!=null&&viewName.equals("resetpass")){
			mv.setViewName("/resetpass");
		}else{
			if (authToken != null && authToken.trim().length() > 0) {
				mv.setViewName(viewName != null && viewName.trim().length() > 0 ? "/" + viewName : "/salaryinfo");
			} else {
				mv.setViewName("/login");
			}
		}
		return mv;
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/logout.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		return new ModelAndView("/login");
	}

	/**
	 * 获取工资信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/getSalaryList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String getSalaryInfoList(HttpServletRequest request, HttpServletResponse response) {
		int recordIndex = StringUtils.hasLength(request.getParameter("jtStartIndex"))
				? Integer.parseInt(request.getParameter("jtStartIndex")) : -1;
		int pageSize = StringUtils.hasLength(request.getParameter("jtPageSize"))
				? Integer.parseInt(request.getParameter("jtPageSize")) : 10;
		Account account = (Account) request.getSession().getAttribute("accountRole");
		String beginTime = request.getParameter("beginDate");
		String endTime = request.getParameter("endDate");
		Pager pager = buildPager(recordIndex, pageSize);
		return salaryInfoBiz.query(account.getEmployeeInfo().getId(), beginTime, endTime, pager);
	}

	
	/**
	 * 检索员工列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/getEmployeeInfos.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String getEmployeeInfos(HttpServletRequest request, HttpServletResponse response) {
		int recordIndex = StringUtils.hasLength(request.getParameter("jtStartIndex"))
				? Integer.parseInt(request.getParameter("jtStartIndex")) : -1;
		int pageSize = StringUtils.hasLength(request.getParameter("jtPageSize"))
				? Integer.parseInt(request.getParameter("jtPageSize")) : 10;
		String department = request.getParameter("department");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		Pager pager = buildPager(recordIndex, pageSize);
		return employeeBiz.query(department, name, email, pager);
	}

	/**
	 * 保存员工信息
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/api/saveEmployee.do", method = { RequestMethod.POST })
	public void saveEmployee(HttpServletRequest request, HttpServletResponse response) {
		String departmentParam = request.getParameter("departmentParam");
		String identityNoParam = request.getParameter("identityNoParam");
		String nameParam = request.getParameter("nameParam");
		String sexParam = request.getParameter("sexParam");
		String emailParam = request.getParameter("emailParam");
		String regDateParam = request.getParameter("regDateParam");
		EmployeeInfo e = new EmployeeInfo();
		e.setDepartment(departmentParam);
		e.setIdentityNo(identityNoParam);
		e.setName(nameParam);
		e.setSex(Integer.parseInt(sexParam));
		e.setEmail(emailParam);
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = dateFormat2.parse(regDateParam);
			e.setRegDate(new java.sql.Date(d.getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		 employeeBiz.add(e);
		try {
			response.sendRedirect("index.do?t=employeeinfo");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 获取账户信息列表 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/getAccountList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String getAccountList(HttpServletRequest request, HttpServletResponse response) {
		int recordIndex = StringUtils.hasLength(request.getParameter("jtStartIndex"))
				? Integer.parseInt(request.getParameter("jtStartIndex")) : -1;
		int pageSize = StringUtils.hasLength(request.getParameter("jtPageSize"))
				? Integer.parseInt(request.getParameter("jtPageSize")) : 10;
		Pager pager = buildPager(recordIndex, pageSize);
		return accountBiz.getAccountList(pager);
	}

	/**
	 * 构造错误信息响应
	 * @param msg
	 * @return
	 */
	private FrontApiResponse getErrorResult(String msg) {
		FrontApiResponse response = new FrontApiResponse();
		response.setStatus("error");
		response.setMessage(msg);
		return response;
	}

	/**
	 * 构造响应
	 * @param result
	 * @return
	 */
	private <T> FrontApiResponse getResult(BusinessResult<T> result) {
		FrontApiResponse response = new FrontApiResponse();
		if (result.getError() != null) {
			response.setStatus("error");
			response.setMessage(result.getError());
			result.setError(null);
		}
		response.setResult(result);
		return response;
	}

	/**
	 * 上传工资信息excel文件
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/upload.do", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam("file") CommonsMultipartFile file) {
		LinkedList<FileMeta> files = new LinkedList<>();
		FileMeta fileMata = null;
		Business.getLogger().info("upload file {}", file.getOriginalFilename());
		if (files.size() > 10) {
			files.pop();
		}
		fileMata = new FileMeta();
		fileMata.setFileName(file.getOriginalFilename());
		fileMata.setFileSize(file.getSize() / 1024 + "kb");
		fileMata.setFileType(file.getContentType());
		try {
			fileMata.setBytes(file.getBytes());
			File outputFile = new File(ConfigUtils.getConfig("uploadFileDir") + "/" + file.getOriginalFilename());
			FileCopyUtils.copy(file.getBytes(), new FileOutputStream(outputFile));
			files.add(fileMata);
			BatchImportDataTask.addToQueue(outputFile);
		} catch (IOException e) {
			Business.getLogger().error(e);
		}
		return new ModelAndView("/upload");
	}

	/**
	 * 修改账户密码
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/api/updatePass.do",method = {RequestMethod.POST})
	public String updatePass(HttpServletRequest request,HttpServletResponse response){
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		Account account = (Account)request.getSession().getAttribute("accountRole");
		if(!Md5Utils.getMd5(oldPass).equals(account.getPass())){
			return renderJson(getErrorResult("密码错误，你没有权限修改密码!"));
		}
		BusinessResult<Boolean> result =accountBiz.updatePass(account.getId(), newPass);
		if(result.getData().booleanValue()){
			account.setPass(Md5Utils.getMd5(newPass));
			request.getSession().setAttribute("accountRole", account);
		}
		return renderJson(getResult(result));
	}
	
	
	/**
	 * 检索登录日志
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/queryLog.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String getLoginLog(HttpServletRequest request, HttpServletResponse response) {
		int recordIndex = StringUtils.hasLength(request.getParameter("jtStartIndex"))
				? Integer.parseInt(request.getParameter("jtStartIndex")) : -1;
		int pageSize = StringUtils.hasLength(request.getParameter("jtPageSize"))
				? Integer.parseInt(request.getParameter("jtPageSize")) : 10;
		Account accountInfo = new Account();
		String uname = request.getParameter("uname");
		if (StringUtils.hasLength(uname)) {
			accountInfo.setUname(uname);
		}
		String accountId = request.getParameter("accountId");
		if (StringUtils.hasLength(accountId)) {
			accountInfo.setId(Integer.parseInt(accountId));
		}
		String ip = request.getParameter("ip");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Pager pager = buildPager(recordIndex, pageSize);
		return loginLogBiz.query(accountInfo, ip, startTime, endTime, pager);
	}

	/**
	 * 添加账号
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/api/addAccount.do", method = { RequestMethod.POST })
	public void addAccount(HttpServletRequest request,HttpServletResponse response){
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String empId = request.getParameter("empId");
		String accountRoleType = request.getParameter("accountRoleType");
		accountBiz.addAccount(uname, pass, Integer.parseInt(empId), AccountRoleType.findRoleType(Integer.parseInt(accountRoleType)));
		try {
			response.sendRedirect("index.do?t=accountlist");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 逻辑删除账号
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/deleteAccount.do", method = { RequestMethod.POST,RequestMethod.GET })
	public String pauseAccount(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		int accountId = Integer.parseInt(id);
		Account account = (Account)request.getSession().getAttribute("accountRole");
		JSONObject jsonObjectResult = new JSONObject();
		if(accountId==account.getId()){
			jsonObjectResult.put("Result", "ERROR");
			jsonObjectResult.put("Message","不能删除当前用户");
			return renderJson(jsonObjectResult);
		}
		accountBiz.updateAccountStatus(accountId,0);
		jsonObjectResult.put("Result", "OK");
		return renderJson(jsonObjectResult);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/api/resetPass.do", method = { RequestMethod.POST})
	public String resetPass(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("email");
		BusinessResult<Account> result = accountBiz.validateAccount(email);
		if(result.getData()==null){
			return renderJson(getErrorResult(result.getError()));
		}
		String newPass = request.getParameter("pass");
		String uuid = accountBiz.sendResetPassMail(email);
		if(uuid!=null){
			request.getSession(true).setAttribute(uuid,result.getData().getId()+"_"+newPass);
		}
		BusinessResult<Boolean> successResult = new BusinessResult<>();
		successResult.setData(true);
		return renderJson(getResult(successResult));
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/resetPass2.do", method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView resetPass2(HttpServletRequest request,HttpServletResponse response){
		String uuid = request.getParameter("k");
		HttpSession session = request.getSession(true);
		Object atr = session.getAttribute(uuid);
		int status = 0;
		if(uuid!=null&&atr!=null){
			String[] datas =atr.toString().split("_");
			accountBiz.updatePass(Integer.parseInt(datas[0]), datas[1]);
			status=1;
			session.removeAttribute(uuid);
		}
		request.setAttribute("status", status);
		return new ModelAndView("resetpass2");
	}
	
	
	/**
	 * 构造分页对象
	 * @param recordIndex
	 * @param pageSize
	 * @return
	 */
	private Pager buildPager(int recordIndex, int pageSize) {
		Pager pager = new Pager();
		int currentPage = recordIndex <= 0 ? 1 : (recordIndex / pageSize) + 1;
		pager.setCurrentPage(currentPage);
		pager.setPageSize(pageSize);
		return pager;
	}

	
	/**
	 * 渲染返回json格式数据
	 * @param obj
	 * @return
	 */
	public static String renderJson(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullListAsEmpty);
	}

}
