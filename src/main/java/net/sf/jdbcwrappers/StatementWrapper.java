/*
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
package net.sf.jdbcwrappers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * Base class for {@link Statement} wrappers.
 * Except for {@link #getConnection()}, all methods delegate to the target {@link Statement} object.
 * {@link ResultSet} objects are wrapped using the {@link WrapperFactory} object.
 * 
 * @author Andreas Veithen
 * @version $Id$
 */
public class StatementWrapper implements Statement, HasConnection {
    Connection connection;
    WrapperFactory wrapperFactory;
    Statement parent;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws SQLException if a database access error occurs
     */
    protected void init() throws SQLException {
    }

    public Statement unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    /**
     * Delegate method for {@link Statement#addBatch(String)}.
     * 
     * {@inheritDoc}
     */
    public void addBatch(String arg0) throws SQLException {
        parent.addBatch(arg0);
    }

    /**
     * Delegate method for {@link Statement#cancel()}.
     * 
     * {@inheritDoc}
     */
    public void cancel() throws SQLException {
        parent.cancel();
    }

    /**
     * Delegate method for {@link Statement#clearBatch()}.
     * 
     * {@inheritDoc}
     */
    public void clearBatch() throws SQLException {
        parent.clearBatch();
    }

    /**
     * Delegate method for {@link Statement#clearWarnings()}.
     * 
     * {@inheritDoc}
     */
    public void clearWarnings() throws SQLException {
        parent.clearWarnings();
    }

    /**
     * Delegate method for {@link Statement#close()}.
     * 
     * {@inheritDoc}
     */
    public void close() throws SQLException {
        parent.close();
    }

    /**
     * Delegate method for {@link Statement#execute(String)}.
     * 
     * {@inheritDoc}
     */
    public boolean execute(String arg0) throws SQLException {
        return parent.execute(arg0);
    }

    /**
     * Delegate method for {@link Statement#execute(String, int)}.
     * 
     * {@inheritDoc}
     */
    public boolean execute(String arg0, int arg1) throws SQLException {
        return parent.execute(arg0, arg1);
    }

    /**
     * Delegate method for {@link Statement#execute(String, int[])}.
     * 
     * {@inheritDoc}
     */
    public boolean execute(String arg0, int[] arg1) throws SQLException {
        return parent.execute(arg0, arg1);
    }

    /**
     * Delegate method for {@link Statement#execute(String, String[])}.
     * 
     * {@inheritDoc}
     */
    public boolean execute(String arg0, String[] arg1) throws SQLException {
        return parent.execute(arg0, arg1);
    }

    /**
     * Delegate method for {@link Statement#executeBatch()}.
     * 
     * {@inheritDoc}
     */
    public int[] executeBatch() throws SQLException {
        return parent.executeBatch();
    }

    /**
     * Delegate method for {@link Statement#executeQuery(String)}.
     * This method wraps the {@link ResultSet} object using
     * {@link WrapperFactory#wrapResultSet(ResultSetType, ResultSet)} with
     * {@link ResultSetType#QUERY}.
     * 
     * {@inheritDoc}
     */
    public ResultSet executeQuery(String arg0) throws SQLException {
        return wrapperFactory.wrapResultSet(parent.executeQuery(arg0), this, ResultSetType.QUERY);
    }

    /**
     * Delegate method for {@link Statement#executeUpdate(String)}.
     * 
     * {@inheritDoc}
     */
    public int executeUpdate(String arg0) throws SQLException {
        return parent.executeUpdate(arg0);
    }

    /**
     * Delegate method for {@link Statement#executeUpdate(String, int)}.
     * 
     * {@inheritDoc}
     */
    public int executeUpdate(String arg0, int arg1) throws SQLException {
        return parent.executeUpdate(arg0, arg1);
    }

    /**
     * Delegate method for {@link Statement#executeUpdate(String, int[])}.
     * 
     * {@inheritDoc}
     */
    public int executeUpdate(String arg0, int[] arg1) throws SQLException {
        return parent.executeUpdate(arg0, arg1);
    }

    /**
     * Delegate method for {@link Statement#executeUpdate(String, String[])}.
     * 
     * {@inheritDoc}
     */
    public int executeUpdate(String arg0, String[] arg1) throws SQLException {
        return parent.executeUpdate(arg0, arg1);
    }

    /**
     * Delegate method for {@link Statement#getConnection()}.
     * This method returns the {@link ConnectionWrapper} object that
     * created this wrapper. For consistency reasons, it can't be
     * overridden.
     * 
     * {@inheritDoc}
     */
    public final Connection getConnection() throws SQLException {
        return connection;
    }

    /**
     * Delegate method for {@link Statement#getFetchDirection()}.
     * 
     * {@inheritDoc}
     */
    public int getFetchDirection() throws SQLException {
        return parent.getFetchDirection();
    }

    /**
     * Delegate method for {@link Statement#getFetchSize()}.
     * 
     * {@inheritDoc}
     */
    public int getFetchSize() throws SQLException {
        return parent.getFetchSize();
    }

    /**
     * Delegate method for {@link Statement#getGeneratedKeys()}.
     * This method wraps the {@link ResultSet} object using
     * {@link WrapperFactory#wrapResultSet(ResultSetType, ResultSet)} with
     * {@link ResultSetType#KEYS}.
     * 
     * {@inheritDoc}
     */
    public ResultSet getGeneratedKeys() throws SQLException {
        return wrapperFactory.wrapResultSet(parent.getGeneratedKeys(), this, ResultSetType.KEYS);
    }

    /**
     * Delegate method for {@link Statement#getMaxFieldSize()}.
     * 
     * {@inheritDoc}
     */
    public int getMaxFieldSize() throws SQLException {
        return parent.getMaxFieldSize();
    }

    /**
     * Delegate method for {@link Statement#getMaxRows()}.
     * 
     * {@inheritDoc}
     */
    public int getMaxRows() throws SQLException {
        return parent.getMaxRows();
    }

    /**
     * Delegate method for {@link Statement#getMoreResults()}.
     * 
     * {@inheritDoc}
     */
    public boolean getMoreResults() throws SQLException {
        return parent.getMoreResults();
    }

    /**
     * Delegate method for {@link Statement#getMoreResults(int)}.
     * 
     * {@inheritDoc}
     */
    public boolean getMoreResults(int arg0) throws SQLException {
        return parent.getMoreResults(arg0);
    }

    /**
     * Delegate method for {@link Statement#getQueryTimeout()}.
     * 
     * {@inheritDoc}
     */
    public int getQueryTimeout() throws SQLException {
        return parent.getQueryTimeout();
    }

    /**
     * Delegate method for {@link Statement#getResultSet()}.
     * This method wraps the {@link ResultSet} object using
     * {@link WrapperFactory#wrapResultSet(ResultSetType, ResultSet)} with
     * {@link ResultSetType#QUERY}.
     * 
     * {@inheritDoc}
     */
    public ResultSet getResultSet() throws SQLException {
        return wrapperFactory.wrapResultSet(parent.getResultSet(), this, ResultSetType.QUERY);
    }

    /**
     * Delegate method for {@link Statement#getResultSetConcurrency()}.
     * 
     * {@inheritDoc}
     */
    public int getResultSetConcurrency() throws SQLException {
        return parent.getResultSetConcurrency();
    }

    /**
     * Delegate method for {@link Statement#getResultSetHoldability()}.
     * 
     * {@inheritDoc}
     */
    public int getResultSetHoldability() throws SQLException {
        return parent.getResultSetHoldability();
    }

    /**
     * Delegate method for {@link Statement#getResultSetType()}.
     * 
     * {@inheritDoc}
     */
    public int getResultSetType() throws SQLException {
        return parent.getResultSetType();
    }

    /**
     * Delegate method for {@link Statement#getUpdateCount()}.
     * 
     * {@inheritDoc}
     */
    public int getUpdateCount() throws SQLException {
        return parent.getUpdateCount();
    }

    /**
     * Delegate method for {@link Statement#getWarnings()}.
     * 
     * {@inheritDoc}
     */
    public SQLWarning getWarnings() throws SQLException {
        return parent.getWarnings();
    }

    /**
     * Delegate method for {@link Statement#setCursorName(String)}.
     * 
     * {@inheritDoc}
     */
    public void setCursorName(String arg0) throws SQLException {
        parent.setCursorName(arg0);
    }

    /**
     * Delegate method for {@link Statement#setEscapeProcessing(boolean)}.
     * 
     * {@inheritDoc}
     */
    public void setEscapeProcessing(boolean arg0) throws SQLException {
        parent.setEscapeProcessing(arg0);
    }

    /**
     * Delegate method for {@link Statement#setFetchDirection(int)}.
     * 
     * {@inheritDoc}
     */
    public void setFetchDirection(int arg0) throws SQLException {
        parent.setFetchDirection(arg0);
    }

    /**
     * Delegate method for {@link Statement#setFetchSize(int)}.
     * 
     * {@inheritDoc}
     */
    public void setFetchSize(int arg0) throws SQLException {
        parent.setFetchSize(arg0);
    }

    /**
     * Delegate method for {@link Statement#setMaxFieldSize(int)}.
     * 
     * {@inheritDoc}
     */
    public void setMaxFieldSize(int arg0) throws SQLException {
        parent.setMaxFieldSize(arg0);
    }

    /**
     * Delegate method for {@link Statement#setMaxRows(int)}.
     * 
     * {@inheritDoc}
     */
    public void setMaxRows(int arg0) throws SQLException {
        parent.setMaxRows(arg0);
    }

    /**
     * Delegate method for {@link Statement#setQueryTimeout(int)}.
     * 
     * {@inheritDoc}
     */
    public void setQueryTimeout(int arg0) throws SQLException {
        parent.setQueryTimeout(arg0);
    }
}
