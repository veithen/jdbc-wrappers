package net.sf.jdbcwrappers.log;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jdbcwrappers.HasConnection;

public class Logger {
	private final static Log log = LogFactory.getLog("JDBC");
	
	public void log(HasConnection source, String method, String sql) {
		try {
			log.info("TX " + ((LoggingConnectionWrapper)source.getConnection()).getTransactionId() + ", " + method + ": " + sql);
		}
		catch (SQLException ex) {
			log.error("Unexpected exception", ex);
		}
	}
}
