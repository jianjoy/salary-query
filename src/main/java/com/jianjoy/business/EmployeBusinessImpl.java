package com.jianjoy.business; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jianjoy.dao.EmployeeInfoDaoImpl;
import com.jianjoy.dao.IEmployeeInfoDao;
import com.jianjoy.dao.dbbean.EmployeeInfoDbDataModel;
import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.EmployeeInfo;
import com.jianjoy.model.JTablePage;
import com.jianjoy.model.Pager;

/** 
 * @author  zhoujian
 *
 * @version 创建时间：2017年4月1日 下午6:47:58
 * 
 */
public class EmployeBusinessImpl implements IEmployeeBusiness {
	
	
	private IEmployeeInfoDao dao = new EmployeeInfoDaoImpl();

	@Override
	public BusinessResult<Boolean> add(EmployeeInfo info) {
		BusinessResult<Boolean> result = new BusinessResult<>();
		dao.save(info);
		return result;
	}

	@Override
	public String query(String department, String name, String email,
			Pager pager) {
		List<Map<String,Object>> infos = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeInfoDbDataModel> dbList = dao.query(department, name, email, pager);
		if(dbList!=null){
			for(EmployeeInfoDbDataModel m:dbList){
				EmployeeInfo info = m.getModel();
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", info.getId());
				map.put("department", info.getDepartment());
				map.put("identityNo", info.getIdentityNo());
				if(info.getRegDate()!=null){
					map.put("regDate", dateFormat2.format(info.getRegDate()));
				}
				map.put("name", info.getName());
				map.put("sex", info.getSex()==1?"男":"女");
				map.put("ctime", dateFormat.format(info.getCtime()));
				map.put("email", info.getEmail());
				infos.add(map);
			}
		}
		JTablePage jtp = new JTablePage(infos, pager);
		return jtp.buildJsonString();
	}
	
}
 