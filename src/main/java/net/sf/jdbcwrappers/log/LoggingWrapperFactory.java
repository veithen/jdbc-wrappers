package net.sf.jdbcwrappers.log;

import net.sf.jdbcwrappers.ConnectionWrapper;
import net.sf.jdbcwrappers.PreparedStatementWrapper;
import net.sf.jdbcwrappers.StatementWrapper;
import net.sf.jdbcwrappers.WrapperFactory;

public class LoggingWrapperFactory extends WrapperFactory {
	private final Logger logger = new Logger();
	private LoggingConfiguration configuration;
	
	/**
	 * Get the configuration of the logging wrapper.
	 * If no configuration has been set, a default configuration is created.
	 * 
	 * @return the configuration of the logging wrapper
	 */
	public LoggingConfiguration getConfiguration() {
		if (configuration == null) {
			configuration = new LoggingConfiguration();
		}
		return configuration;
	}

	public void setConfiguration(LoggingConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected ConnectionWrapper createConnectionWrapper() {
		return new LoggingConnectionWrapper(logger, getConfiguration());
	}

	@Override
	protected StatementWrapper createStatementWrapper() {
		return new LoggingStatementWrapper(logger, getConfiguration());
	}

	@Override
	protected PreparedStatementWrapper createPreparedStatementWrapper() {
		return new LoggingPreparedStatementWrapper(logger, getConfiguration());
	}
}
