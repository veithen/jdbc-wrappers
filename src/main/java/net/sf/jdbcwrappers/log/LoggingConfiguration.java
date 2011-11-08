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

import java.sql.Connection;

/**
 * Configuration bean for the logging wrapper.
 * 
 * @author Andreas Veithen
 * @version $Id:LoggingConfiguration.java 24 2008-06-21 15:08:14Z veithen $
 */
public class LoggingConfiguration {
    private boolean trackTransactions = false;
    private boolean logCommitRollback = false;
    private boolean prettyPrintSqlStatements = true;
    
    public boolean isTrackTransactions() {
        return trackTransactions;
    }
    
    /**
     * Specify whether transaction tracking is enabled.
     * When transaction tracking is enabled, a unique (sequential) ID for each transaction is
     * generated. This ID is logged together with the SQL statements.
     * By default, transaction tracking is disabled.
     * 
     * @param trackTransactions
     */
    public void setTrackTransactions(boolean trackTransactions) {
        this.trackTransactions = trackTransactions;
    }
    
    public boolean isLogCommitRollback() {
        return logCommitRollback;
    }
    
    /**
     * Specify whether calls to {@link Connection#commit()} and {@link Connection#rollback()} are
     * logged. By default this is disabled.
     * 
     * @param logCommitRollback
     *            <code>true</code> if transaction commits and rollbacks should be logged,
     *            <code>false</code> otherwise.
     */
    public void setLogCommitRollback(boolean logCommitRollback) {
        this.logCommitRollback = logCommitRollback;
    }
    
    public boolean isPrettyPrintSqlStatements() {
        return prettyPrintSqlStatements;
    }
    
    /**
     * Specify whether SQL statements are pretty printed. By default this is enabled.
     * 
     * @param prettyPrintSqlStatements
     *            <code>true</code> if SQL statements should be pretty printed, <code>false</code>
     *            otherwise
     */
    public void setPrettyPrintSqlStatements(boolean prettyPrintSqlStatements) {
        this.prettyPrintSqlStatements = prettyPrintSqlStatements;
    }
}
