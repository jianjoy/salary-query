package com.jianjoy.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager {

	private static final Logger log = LogManager.getLogger(ConnectionManager.class);

	private ConnectionManager() {
		cpds = new ComboPooledDataSource();
	}

	public static ConnectionManager getInstance() {
		return ComboPooledDataSourceHolder.INSTANCE;
	}

	private ComboPooledDataSource cpds;

	private static class ComboPooledDataSourceHolder {
		private static final ConnectionManager INSTANCE = new ConnectionManager();
	}

	public Connection getConnection() {
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	public void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connection =null;
			} catch (SQLException e) {
				log.error(e.toString());
			}
		}
	}

}
