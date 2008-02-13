package net.sf.jdbcwrappers.log;

import net.sf.jdbcwrappers.ConnectionWrapper;
import net.sf.jdbcwrappers.PreparedStatementWrapper;
import net.sf.jdbcwrappers.StatementWrapper;
import net.sf.jdbcwrappers.WrapperFactory;

public class LoggingWrapperFactory extends WrapperFactory {
	private final Logger logger = new Logger();
	
	@Override
	public ConnectionWrapper createConnectionWrapper() {
		return new LoggingConnectionWrapper();
	}

	@Override
	public StatementWrapper createStatementWrapper() {
		return new LoggingStatementWrapper(logger);
	}

	@Override
	public PreparedStatementWrapper createPreparedStatementWrapper() {
		return new LoggingPreparedStatementWrapper(logger);
	}
}
