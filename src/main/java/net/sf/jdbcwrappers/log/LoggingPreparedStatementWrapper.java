package net.sf.jdbcwrappers.log;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jdbcwrappers.PreparedStatementWrapper;

public class LoggingPreparedStatementWrapper extends PreparedStatementWrapper {
	private final Logger logger;
	
	public LoggingPreparedStatementWrapper(Logger logger) {
		this.logger = logger;
	}

	private void log(String method) {
		logger.log(this, method, getSql());
	}

	@Override
	public boolean execute() throws SQLException {
		log("PreparedStatement#execute");
		return super.execute();
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		log("PreparedStatement#executeQuery");
		return super.executeQuery();
	}

	@Override
	public int executeUpdate() throws SQLException {
		log("PreparedStatement#executeUpdate");
		return super.executeUpdate();
	}
}
