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
package net.sf.jdbcwrappers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * Wrapper factory base class.
 */
public class WrapperFactory {
    private boolean allowUnwrap;

    public synchronized boolean isAllowUnwrap() {
        return allowUnwrap;
    }

    public synchronized void setAllowUnwrap(boolean allowUnwrap) {
        this.allowUnwrap = allowUnwrap;
    }

    protected DataSourceWrapper createDataSourceWrapper() {
        return new DataSourceWrapper();
    }

    public final DataSourceWrapper wrapDataSource(DataSource parent) throws SQLException {
        DataSourceWrapper wrapper = createDataSourceWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }

    protected ConnectionWrapper createConnectionWrapper() {
        return new ConnectionWrapper();
    }

    public final ConnectionWrapper wrapConnection(Connection parent) throws SQLException {
        ConnectionWrapper wrapper = createConnectionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }

    protected StatementWrapper createStatementWrapper() {
        return new StatementWrapper();
    }

    final StatementWrapper wrapStatement(Statement parent, Connection connection) throws SQLException {
        StatementWrapper wrapper = createStatementWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.connection = connection;
        wrapper.init();
        return wrapper;
    }

    protected DatabaseMetaDataWrapper createDatabaseMetaDataWrapper() {
        return new DatabaseMetaDataWrapper();
    }

    final DatabaseMetaDataWrapper wrapDatabaseMetaData(DatabaseMetaData parent, Connection connection) throws SQLException {
        DatabaseMetaDataWrapper wrapper = createDatabaseMetaDataWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.connection = connection;
        wrapper.init();
        return wrapper;
    }

    protected CallableStatementWrapper createCallableStatementWrapper() {
        return new CallableStatementWrapper();
    }

    final CallableStatementWrapper wrapCallableStatement(ConnectionWrapper connectionWrapper, CallableStatement parent, String sql) throws SQLException {
        CallableStatementWrapper wrapper = createCallableStatementWrapper();
        wrapper.wrapperFactory = this;
        wrapper.baseWrapper = wrapPreparedStatement(connectionWrapper, parent, sql);
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }

    protected PreparedStatementWrapper createPreparedStatementWrapper() {
        return new PreparedStatementWrapper();
    }

    final PreparedStatementWrapper wrapPreparedStatement(ConnectionWrapper connectionWrapper, PreparedStatement parent, String sql) throws SQLException {
        PreparedStatementWrapper wrapper = createPreparedStatementWrapper();
        wrapper.wrapperFactory = this;
        wrapper.statementWrapper = wrapStatement(parent, connectionWrapper);
        wrapper.parent = parent;
        wrapper.sql = sql;
        wrapper.init();
        return wrapper;
    }

    protected ResultSetWrapper createResultSetWrapper(@SuppressWarnings("unused") ResultSetType resultSetType) {
        return new ResultSetWrapper();
    }

    final ResultSetWrapper wrapResultSet(ResultSet parent, Statement statement, ResultSetType resultSetType) throws SQLException {
        ResultSetWrapper wrapper = createResultSetWrapper(resultSetType);
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.statement = statement;
        wrapper.init();
        return wrapper;
    }
}
