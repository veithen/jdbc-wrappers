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
package net.sf.jdbcwrappers.log;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jdbcwrappers.PreparedStatementWrapper;

public class LoggingPreparedStatementWrapper extends PreparedStatementWrapper {
    private final Logger logger;
    private final boolean prettyPrint;
    
    public LoggingPreparedStatementWrapper(Logger logger, LoggingConfiguration configuration) {
        this.logger = logger;
        prettyPrint = configuration.isPrettyPrintSqlStatements();
    }

    private void log(String method) {
        logger.log(this, method, getSql(), prettyPrint);
    }

    @Override
    public boolean execute() throws SQLException {
        log("PreparedStatement#execute");
        return super.execute();
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        log("PreparedStatement#executeQuery");
        return super.executeQuery();
    }

    @Override
    public int executeUpdate() throws SQLException {
        log("PreparedStatement#executeUpdate");
        return super.executeUpdate();
    }
}
