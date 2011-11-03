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

import net.sf.jdbcwrappers.ConnectionWrapper;
import net.sf.jdbcwrappers.PreparedStatementWrapper;
import net.sf.jdbcwrappers.StatementWrapper;
import net.sf.jdbcwrappers.WrapperFactory;

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
    protected ConnectionWrapper createConnectionWrapper() {
        return new LoggingConnectionWrapper(logger, getConfiguration());
    }

    @Override
    protected StatementWrapper createStatementWrapper() {
        return new LoggingStatementWrapper(logger, getConfiguration());
    }

    @Override
    protected PreparedStatementWrapper createPreparedStatementWrapper() {
        return new LoggingPreparedStatementWrapper(logger, getConfiguration());
    }
}
