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

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Base class for {@link DataSource} wrappers.
 * All methods delegate to the target {@link DataSource} object. Returned
 * {@link Connection} objects are wrapped using the {@link WrapperFactory}
 * object that created the {@link DataSourceWrapper}.
 * 
 * @author Andreas Veithen
 * @version $Id$
 */
public class DataSourceWrapper extends AbstractWrapper<DataSource> implements DataSource {
    /**
     * Delegate method for {@link DataSource#getConnection()}.
     * This method wraps the {@link Connection} object using
     * {@link WrapperFactory#wrapConnection(Connection)}.
     * 
     * {@inheritDoc}
     */
	public Connection getConnection() throws SQLException {
		return wrapperFactory.wrapConnection(parent.getConnection());
	}

    /**
     * Delegate method for {@link DataSource#getConnection(String, String)}.
     * This method wraps the {@link Connection} object using
     * {@link WrapperFactory#wrapConnection(Connection)}.
     * 
     * {@inheritDoc}
     */
	public Connection getConnection(String username, String password) throws SQLException {
		return wrapperFactory.wrapConnection(parent.getConnection(username, password));
	}

    /**
     * Delegate method for {@link DataSource#getLoginTimeout()}.
     * 
     * {@inheritDoc}
     */
	public int getLoginTimeout() throws SQLException {
		return parent.getLoginTimeout();
	}

    /**
     * Delegate method for {@link DataSource#getLogWriter()}.
     * 
     * {@inheritDoc}
     */
	public PrintWriter getLogWriter() throws SQLException {
		return parent.getLogWriter();
	}

    /**
     * Delegate method for {@link DataSource#setLoginTimeout(int)}.
     * 
     * {@inheritDoc}
     */
	public void setLoginTimeout(int seconds) throws SQLException {
		parent.setLoginTimeout(seconds);
	}

    /**
     * Delegate method for {@link DataSource#setLogWriter(PrintWriter)}.
     * 
     * {@inheritDoc}
     */
	public void setLogWriter(PrintWriter out) throws SQLException {
		parent.setLogWriter(out);
	}
}
