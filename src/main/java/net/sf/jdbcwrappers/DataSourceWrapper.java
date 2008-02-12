package net.sf.jdbcwrappers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DataSourceWrapper implements DataSource {
	private final WrapperFactory wrapperFactory;
	private final DataSource parent;
	
	public DataSourceWrapper(WrapperFactory wrapperFactory, DataSource parent) {
		this.wrapperFactory = wrapperFactory;
		this.parent = parent;
	}

	public Connection getConnection() throws SQLException {
		return wrapperFactory.wrapConnection(parent.getConnection());
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return wrapperFactory.wrapConnection(parent.getConnection(username, password));
	}

	public int getLoginTimeout() throws SQLException {
		return parent.getLoginTimeout();
	}

	public PrintWriter getLogWriter() throws SQLException {
		return parent.getLogWriter();
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		parent.setLoginTimeout(seconds);
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		parent.setLogWriter(out);
	}
}
