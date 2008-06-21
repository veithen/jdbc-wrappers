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
package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * Base class for {@link ConnectionFactory} wrappers.
 * All methods delegate to the target {@link ConnectionFactory} object. Returned
 * {@link Connection} objects are wrapped using the {@link WrapperFactory}
 * object that created the {@link ConnectionFactoryWrapper}.
 * 
 * @author Andreas Veithen
 * @version $Id$
 */
public class ConnectionFactoryWrapper extends AbstractWrapper<ConnectionFactory> implements ConnectionFactory {
    final void init(WrapperFactory wrapperFactory, ConnectionFactory parent) throws JMSException {
        this.wrapperFactory = wrapperFactory;
        this.parent = parent;
        init();
    }
    
    /**
     * Delegate method for {@link ConnectionFactory#createConnection()}.
     * This method wraps the {@link Connection} object using
     * {@link WrapperFactory#wrapConnection(Connection)}.
     * 
     * {@inheritDoc}
     */
    public Connection createConnection() throws JMSException {
        return wrapperFactory.wrapConnection(parent.createConnection());
    }
    
    /**
     * Delegate method for {@link ConnectionFactory#createConnection(String, String)}.
     * This method wraps the {@link Connection} object using
     * {@link WrapperFactory#wrapConnection(Connection)}.
     * 
     * {@inheritDoc}
     */
    public Connection createConnection(String userName, String password) throws JMSException {
        return wrapperFactory.wrapConnection(parent.createConnection(userName, password));
    }
}
