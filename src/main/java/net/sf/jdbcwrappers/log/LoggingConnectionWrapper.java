package net.sf.jdbcwrappers.log;

import java.sql.SQLException;

import net.sf.jdbcwrappers.ConnectionWrapper;

public class LoggingConnectionWrapper extends ConnectionWrapper {
	private static int transactionIdSequence;
	
	private boolean autoCommit;
	private Integer transactionId;
	
	@Override
	protected void init() throws SQLException {
		autoCommit = getAutoCommit();
	}
	
	public Integer getTransactionId() {
		if (transactionId == null && !autoCommit) {
			synchronized (ConnectionWrapper.class) {
				transactionId = ++transactionIdSequence;
			}
		}
		return transactionId;
	}
	
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		super.setAutoCommit(autoCommit);
		this.autoCommit = autoCommit;
	}

	@Override
	public void commit() throws SQLException {
		super.commit();
		transactionId = null;
	}

	@Override
	public void rollback() throws SQLException {
		super.rollback();
		transactionId = null;
	}
}
