package net.sf.jwrappers.jdbc.log;

import net.sf.jwrappers.jdbc.ConnectionWrapper;
import net.sf.jwrappers.jdbc.PreparedStatementWrapper;
import net.sf.jwrappers.jdbc.StatementWrapper;
import net.sf.jwrappers.jdbc.WrapperFactory;

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
	public ConnectionWrapper createConnectionWrapper() {
		return new LoggingConnectionWrapper(logger, getConfiguration());
	}

	@Override
	public StatementWrapper createStatementWrapper() {
		return new LoggingStatementWrapper(logger, getConfiguration());
	}

	@Override
	public PreparedStatementWrapper createPreparedStatementWrapper() {
		return new LoggingPreparedStatementWrapper(logger, getConfiguration());
	}
}
