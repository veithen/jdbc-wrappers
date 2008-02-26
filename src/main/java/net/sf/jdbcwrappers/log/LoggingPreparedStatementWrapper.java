package net.sf.jdbcwrappers.log;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jdbcwrappers.PreparedStatementWrapper;

public class LoggingPreparedStatementWrapper extends PreparedStatementWrapper {
	private final Logger logger;
	private final boolean prettyPrint;
	
	public LoggingPreparedStatementWrapper(Logger logger, LoggingConfiguration configuration) {
		this.logger = logger;
		prettyPrint = configuration.isPrettyPrintSqlStatements();
	}

	private void log(String method) {
		logger.log(this, method, getSql(), prettyPrint);
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
