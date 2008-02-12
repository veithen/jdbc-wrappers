package net.sf.jdbcwrappers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class WrapperFactory {
	public ConnectionWrapper createConnectionWrapper() {
		return new ConnectionWrapper();
	}
	
	final ConnectionWrapper wrapConnection(Connection parent) {
		ConnectionWrapper wrapper = createConnectionWrapper();
		wrapper.init(this, parent);
		return wrapper;
	}
	
	public DatabaseMetaDataWrapper createDatabaseMetaDataWrapper() {
		return new DatabaseMetaDataWrapper();
	}
	
	final DatabaseMetaDataWrapper wrapDatabaseMetaData(ConnectionWrapper connectionWrapper, DatabaseMetaData parent) {
		DatabaseMetaDataWrapper wrapper = createDatabaseMetaDataWrapper();
		wrapper.init(connectionWrapper, parent);
		return wrapper;
	}
	
	public StatementWrapper createStatementWrapper() {
		return new StatementWrapper();
	}
	
	final StatementWrapper wrapStatement(ConnectionWrapper connectionWrapper, Statement parent) {
		StatementWrapper wrapper = createStatementWrapper();
		wrapper.init(this, connectionWrapper, parent);
		return wrapper;
	}
	
	public PreparedStatementWrapper createPreparedStatementWrapper() {
		return new PreparedStatementWrapper();
	}
	
	final PreparedStatementWrapper wrapPreparedStatement(ConnectionWrapper connectionWrapper, PreparedStatement parent, String sql) {
		PreparedStatementWrapper wrapper = createPreparedStatementWrapper();
		wrapper.init(this, connectionWrapper, parent, sql);
		return wrapper;
	}
	
	public CallableStatementWrapper createCallableStatementWrapper() {
		return new CallableStatementWrapper();
	}
	
	final CallableStatementWrapper wrapCallableStatement(ConnectionWrapper connectionWrapper, CallableStatement parent, String sql) {
		CallableStatementWrapper wrapper = createCallableStatementWrapper();
		wrapper.init(this, connectionWrapper, parent, sql);
		return wrapper;
	}
	
	public ResultSetWrapper createResultSetWrapper(@SuppressWarnings("unused") ResultSetType resultSetType) {
		return new ResultSetWrapper();
	}
	
	final ResultSetWrapper wrapResultSet(ResultSetType resultSetType, Statement statementWrapper, ResultSet parent) {
		ResultSetWrapper wrapper = createResultSetWrapper(resultSetType);
		wrapper.init(statementWrapper, parent);
		return wrapper;
	}
}
