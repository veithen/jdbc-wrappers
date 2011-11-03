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

import java.sql.SQLException;

import net.sf.jdbcwrappers.ConnectionWrapper;

public class LoggingConnectionWrapper extends ConnectionWrapper {
    private static int transactionIdSequence;
    
    private final Logger logger;
    private final boolean trackTransactions;
    private final boolean logCommitRollback;
    
    private boolean autoCommit;
    private Integer transactionId;
    
    public LoggingConnectionWrapper(Logger logger, LoggingConfiguration configuration) {
        this.logger = logger;
        trackTransactions = configuration.isTrackTransactions();
        logCommitRollback = configuration.isLogCommitRollback();
    }
    
    public boolean isTrackTransactions() {
        return trackTransactions;
    }

    @Override
    protected void init() throws SQLException {
        if (trackTransactions) {
            autoCommit = getAutoCommit();
        }
    }
    
    public Integer getTransactionId() {
        if (trackTransactions) {
            if (transactionId == null && !autoCommit) {
                synchronized (ConnectionWrapper.class) {
                    transactionId = ++transactionIdSequence;
                }
            }
            return transactionId;
        } else {
            throw new IllegalStateException("Transaction tracking is not enabled");
        }
    }
    
    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        super.setAutoCommit(autoCommit);
        if (trackTransactions) {
            this.autoCommit = autoCommit;
        }
    }

    @Override
    public void commit() throws SQLException {
        super.commit();
        if (logCommitRollback) {
            if (trackTransactions) {
                logger.log("Transaction " + getTransactionId() + " committed");
            } else {
                logger.log("Transaction committed");
            }
        }
        if (trackTransactions) {
            transactionId = null;
        }
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
        if (logCommitRollback) {
            if (trackTransactions) {
                logger.log("Transaction " + getTransactionId() + " rolled back");
            } else {
                logger.log("Transaction rolled back");
            }
        }
        if (trackTransactions) {
            transactionId = null;
        }
    }
}
