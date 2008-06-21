/**
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package net.sf.jwrappers.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;

/**
 * Base class for {@link Connection} wrappers.
 * All methods delegate to the target {@link Connection} object. Returned
 * {@link DatabaseMetaData}, {@link Statement}, {@link PreparedStatement} and
 * {@link CallableStatement} objects are wrapped using the {@link WrapperFactory}
 * object that created the {@link ConnectionWrapper}.
 * 
 * @author Andreas Veithen
 * @version $Id$
 */
public class ConnectionWrapper extends AbstractWrapper<Connection> implements Connection {
	final void init(WrapperFactory wrapperFactory, Connection parent) throws SQLException {
		this.wrapperFactory = wrapperFactory;
		this.parent = parent;
		init();
	}
	
	/**
	 * Delegate method for {@link Connection#clearWarnings()}.
	 * 
	 * {@inheritDoc}
	 */
	public void clearWarnings() throws SQLException {
		parent.clearWarnings();
	}
	
	/**
	 * Delegate method for {@link Connection#close()}.
	 * 
	 * {@inheritDoc}
	 */
	public void close() throws SQLException {
		parent.close();
	}
	
	/**
	 * Delegate method for {@link Connection#commit()}.
	 * 
	 * {@inheritDoc}
	 */
	public void commit() throws SQLException {
		parent.commit();
	}
	
	/**
	 * Delegate method for {@link Connection#createStatement()}.
	 * This method wraps the {@link Statement} object using
	 * {@link WrapperFactory#wrapStatement(ConnectionWrapper, Statement)}.
	 * 
	 * {@inheritDoc}
	 */
	public Statement createStatement() throws SQLException {
		return wrapperFactory.wrapStatement(this, parent.createStatement());
	}
	
	/**
	 * Delegate method for {@link Connection#createStatement(int, int, int))}.
	 * This method wraps the {@link Statement} object using
	 * {@link WrapperFactory#wrapStatement(ConnectionWrapper, Statement)}.
	 * 
	 * {@inheritDoc}
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return wrapperFactory.wrapStatement(this, parent.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
	}
	
	/**
	 * Delegate method for {@link Connection#createStatement(int, int))}.
	 * This method wraps the {@link Statement} object using
	 * {@link WrapperFactory#wrapStatement(ConnectionWrapper, Statement)}.
	 * 
	 * {@inheritDoc}
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return wrapperFactory.wrapStatement(this, parent.createStatement(resultSetType, resultSetConcurrency));
	}
	
	/**
	 * Delegate method for {@link Connection#getAutoCommit()}.
	 * 
	 * {@inheritDoc}
	 */
	public boolean getAutoCommit() throws SQLException {
		return parent.getAutoCommit();
	}
	
	/**
	 * Delegate method for {@link Connection#getCatalog()}.
	 * 
	 * {@inheritDoc}
	 */
	public String getCatalog() throws SQLException {
		return parent.getCatalog();
	}
	
	/**
	 * Delegate method for {@link Connection#getHoldability()}.
	 * 
	 * {@inheritDoc}
	 */
	public int getHoldability() throws SQLException {
		return parent.getHoldability();
	}
	
	/**
	 * Delegate method for {@link Connection#getMetaData()}.
	 * This method wraps the {@link DatabaseMetaData} object using
	 * {@link WrapperFactory#wrapDatabaseMetaData(DatabaseMetaData)}.
	 * 
	 * {@inheritDoc}
	 */
	public DatabaseMetaData getMetaData() throws SQLException {
		return wrapperFactory.wrapDatabaseMetaData(this, parent.getMetaData());
	}
	
	/**
	 * Delegate method for {@link Connection#getTransactionIsolation()}.
	 * 
	 * {@inheritDoc}
	 */
	public int getTransactionIsolation() throws SQLException {
		return parent.getTransactionIsolation();
	}
	
	/**
	 * Delegate method for {@link Connection#getTypeMap()}.
	 * 
	 * {@inheritDoc}
	 */
	public Map<String,Class<?>> getTypeMap() throws SQLException {
		return parent.getTypeMap();
	}
	
	/**
	 * Delegate method for {@link Connection#getWarnings()}.
	 * 
	 * {@inheritDoc}
	 */
	public SQLWarning getWarnings() throws SQLException {
		return parent.getWarnings();
	}
	
	/**
	 * Delegate method for {@link Connection#isClosed()}.
	 * 
	 * {@inheritDoc}
	 */
	public boolean isClosed() throws SQLException {
		return parent.isClosed();
	}
	
	/**
	 * Delegate method for {@link Connection#isReadOnly()}.
	 * 
	 * {@inheritDoc}
	 */
	public boolean isReadOnly() throws SQLException {
		return parent.isReadOnly();
	}
	
	/**
	 * Delegate method for {@link Connection#nativeSQL(String)}.
	 * 
	 * {@inheritDoc}
	 */
	public String nativeSQL(String sql) throws SQLException {
		return parent.nativeSQL(sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareCall(String, int, int, int)}.
	 * This method wraps the {@link CallableStatement} object using
	 * {@link WrapperFactory#wrapCallableStatement(ConnectionWrapper, CallableStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return wrapperFactory.wrapCallableStatement(this, parent.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareCall(String, int, int)}.
	 * This method wraps the {@link CallableStatement} object using
	 * {@link WrapperFactory#wrapCallableStatement(ConnectionWrapper, CallableStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return wrapperFactory.wrapCallableStatement(this, parent.prepareCall(sql, resultSetType, resultSetConcurrency), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareCall(String)}.
	 * This method wraps the {@link CallableStatement} object using
	 * {@link WrapperFactory#wrapCallableStatement(ConnectionWrapper, CallableStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public CallableStatement prepareCall(String sql) throws SQLException {
		return wrapperFactory.wrapCallableStatement(this, parent.prepareCall(sql), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareStatement(String, int, int, int)}.
	 * This method wraps the {@link PreparedStatement} object using
	 * {@link WrapperFactory#wrapPreparedStatement(ConnectionWrapper, PreparedStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return wrapperFactory.wrapPreparedStatement(this, parent.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareStatement(String, int, int)}.
	 * This method wraps the {@link PreparedStatement} object using
	 * {@link WrapperFactory#wrapPreparedStatement(ConnectionWrapper, PreparedStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return wrapperFactory.wrapPreparedStatement(this, parent.prepareStatement(sql, resultSetType, resultSetConcurrency), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareStatement(String, int)}.
	 * This method wraps the {@link PreparedStatement} object using
	 * {@link WrapperFactory#wrapPreparedStatement(ConnectionWrapper, PreparedStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return wrapperFactory.wrapPreparedStatement(this, parent.prepareStatement(sql, autoGeneratedKeys), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareStatement(String, int[])}.
	 * This method wraps the {@link PreparedStatement} object using
	 * {@link WrapperFactory#wrapPreparedStatement(ConnectionWrapper, PreparedStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return wrapperFactory.wrapPreparedStatement(this, parent.prepareStatement(sql, columnIndexes), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareStatement(String, String[])}.
	 * This method wraps the {@link PreparedStatement} object using
	 * {@link WrapperFactory#wrapPreparedStatement(ConnectionWrapper, PreparedStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return wrapperFactory.wrapPreparedStatement(this, parent.prepareStatement(sql, columnNames), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#prepareStatement(String)}.
	 * This method wraps the {@link PreparedStatement} object using
	 * {@link WrapperFactory#wrapPreparedStatement(ConnectionWrapper, PreparedStatement)}.
	 * 
	 * {@inheritDoc}
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return wrapperFactory.wrapPreparedStatement(this, parent.prepareStatement(sql), sql);
	}
	
	/**
	 * Delegate method for {@link Connection#releaseSavepoint(Savepoint)}.
	 * 
	 * {@inheritDoc}
	 */
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		parent.releaseSavepoint(savepoint);
	}
	
	/**
	 * Delegate method for {@link Connection#rollback()}.
	 * 
	 * {@inheritDoc}
	 */
	public void rollback() throws SQLException {
		parent.rollback();
	}
	
	/**
	 * Delegate method for {@link Connection#rollback(Savepoint)}.
	 * 
	 * {@inheritDoc}
	 */
	public void rollback(Savepoint savepoint) throws SQLException {
		parent.rollback(savepoint);
	}
	
	/**
	 * Delegate method for {@link Connection#setAutoCommit(boolean)}.
	 * 
	 * {@inheritDoc}
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		parent.setAutoCommit(autoCommit);
	}
	
	/**
	 * Delegate method for {@link Connection#setCatalog(String)}.
	 * 
	 * {@inheritDoc}
	 */
	public void setCatalog(String catalog) throws SQLException {
		parent.setCatalog(catalog);
	}
	
	/**
	 * Delegate method for {@link Connection#setHoldability(int)}.
	 * 
	 * {@inheritDoc}
	 */
	public void setHoldability(int holdability) throws SQLException {
		parent.setHoldability(holdability);
	}
	
	/**
	 * Delegate method for {@link Connection#setReadOnly(boolean)}.
	 * 
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) throws SQLException {
		parent.setReadOnly(readOnly);
	}
	
	/**
	 * Delegate method for {@link Connection#setSavepoint()}.
	 * 
	 * {@inheritDoc}
	 */
	public Savepoint setSavepoint() throws SQLException {
		return parent.setSavepoint();
	}
	
	/**
	 * Delegate method for {@link Connection#setSavepoint(String)}.
	 * 
	 * {@inheritDoc}
	 */
	public Savepoint setSavepoint(String name) throws SQLException {
		return parent.setSavepoint(name);
	}
	
	/**
	 * Delegate method for {@link Connection#setTransactionIsolation(int)}.
	 * 
	 * {@inheritDoc}
	 */
	public void setTransactionIsolation(int level) throws SQLException {
		parent.setTransactionIsolation(level);
	}
	
	/**
	 * Delegate method for {@link Connection#setTypeMap(Map)}.
	 * 
	 * {@inheritDoc}
	 */
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		parent.setTypeMap(map);
	}
}
