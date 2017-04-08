package com.jianjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jianjoy.dao.dbbean.AbstractDbDataBean;
import com.jianjoy.log.DbAccess;
import com.jianjoy.model.Pager;

public abstract class BasePageDao implements IPageDao {

	public <T extends AbstractDbDataBean> List<T> findByPager(Class<T> clz, String sql, List<Object> params, Pager pager, Boolean desc){
		List<T> list = new ArrayList<>();
		try {
			clz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("clz :" + clz + " init error");
		}
		Connection con = JDBCUtils.connect();
		String execSql = sql;
		String countSql = null;
		if (pager != null) {
			int offset = pager.getOffSet();
			int pageSize = pager.getPageSize();
			int endIndex = sql.indexOf(" from ");
			if (endIndex == -1) {
				throw new RuntimeException("sql format error");
			}
			String formatSql = sql.substring(endIndex);
			countSql = "select count(1) from ( select 1 " + formatSql+") a";
			DbAccess.getLogger().info(countSql);
			if (desc != null && desc.booleanValue()) {
				execSql += " order by id desc ";
			}
			execSql += " limit " + offset + "," + pageSize;
		}
		PreparedStatement pst = null;
		PreparedStatement countPst = null;
		ResultSet countRs = null;
		ResultSet rs = null;
		try {
			if (countSql != null) {
				countPst = con.prepareStatement(countSql);
			}
			pst = con.prepareStatement(execSql);

			if (params != null) {
				int paramSize = params.size();
				for (int i = 1; i <= paramSize; i++) {
					if (countPst != null) {
						countPst.setObject(i, params.get(i - 1));
					}
					pst.setObject(i, params.get(i - 1));
				}
			}
			if (countPst != null) {
				countRs = countPst.executeQuery();
				if (countRs.next()) {
					pager.setTotalRows(countRs.getInt(1));
				}
			}
			rs = pst.executeQuery();
			ResultSetMetaData rsMetata = rs.getMetaData();
			int rowCount = rsMetata.getColumnCount();
			while (rs.next()) {
				T t = clz.newInstance();
				Object[] datas = new Object[rowCount];
				for (int i = 1; i <= rowCount; i++) {
					datas[i - 1] = rs.getObject(i);
				}
				t.convertData(datas);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(countRs);
			JDBCUtils.close(rs);
			JDBCUtils.close(countPst);
			JDBCUtils.close(pst);
			JDBCUtils.close(con);
		}
		return list;
	}
	
	
}
