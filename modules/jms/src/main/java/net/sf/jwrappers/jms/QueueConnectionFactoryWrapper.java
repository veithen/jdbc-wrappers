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
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

/**
 * Base class for {@link QueueConnectionFactory} wrappers.
 * 
 * @author Andreas Veithen
 * @version $Id$
 */
public class QueueConnectionFactoryWrapper extends AbstractConnectionFactoryWrapper<QueueConnectionFactory> implements QueueConnectionFactory {
    /**
     * Delegate method for {@link QueueConnectionFactory#createConnection()}.
     * This method wraps the {@link QueueConnection} object using
     * {@link WrapperFactory#wrapQueueConnection(QueueConnection)}.
     * 
     * {@inheritDoc}
     */
    public QueueConnection createQueueConnection() throws JMSException {
        // TODO
        return parent.createQueueConnection();
    }

    /**
     * Delegate method for {@link QueueConnectionFactory#createConnection(String, String)}.
     * This method wraps the {@link QueueConnection} object using
     * {@link WrapperFactory#wrapQueueConnection(QueueConnection)}.
     * 
     * {@inheritDoc}
     */
    public QueueConnection createQueueConnection(String userName, String password) throws JMSException {
        // TODO
        return parent.createQueueConnection(userName, password);
    }
}
