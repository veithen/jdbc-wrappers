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
import javax.jms.Session;
import javax.jms.TopicConnectionFactory;

/**
 * 
 * @author Andreas Veithen
 * @version $Id$
 */
public class WrapperFactory {
    private boolean allowUnwrap;
    
    public synchronized boolean isAllowUnwrap() {
        return allowUnwrap;
    }

    public synchronized void setAllowUnwrap(boolean allowUnwrap) {
        this.allowUnwrap = allowUnwrap;
    }

    protected ConnectionFactoryWrapper createConnectionFactoryWrapper() {
        return new ConnectionFactoryWrapper();
    }
    
    public final ConnectionFactoryWrapper wrapConnectionFactory(ConnectionFactory parent) throws JMSException {
        ConnectionFactoryWrapper wrapper = createConnectionFactoryWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }
    
    protected QueueConnectionFactoryWrapper createQueueConnectionFactoryWrapper() {
        return new QueueConnectionFactoryWrapper();
    }
    
    public final QueueConnectionFactoryWrapper wrapQueueConnectionFactory(QueueConnectionFactory parent) throws JMSException {
        QueueConnectionFactoryWrapper wrapper = createQueueConnectionFactoryWrapper();
        wrapper.wrapperFactory = this;
        wrapper.connectionFactoryWrapper = wrapConnectionFactory(parent);
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }
    
    protected TopicConnectionFactoryWrapper createTopicConnectionFactoryWrapper() {
        return new TopicConnectionFactoryWrapper();
    }
    
    public final TopicConnectionFactoryWrapper wrapTopicConnectionFactory(TopicConnectionFactory parent) throws JMSException {
        TopicConnectionFactoryWrapper wrapper = createTopicConnectionFactoryWrapper();
        wrapper.wrapperFactory = this;
        wrapper.connectionFactoryWrapper = wrapConnectionFactory(parent);
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }
    
    protected ConnectionWrapper createConnectionWrapper() {
        return new ConnectionWrapper();
    }
    
    public final ConnectionWrapper wrapConnection(Connection parent) throws JMSException {
        ConnectionWrapper wrapper = createConnectionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }
    
    protected QueueConnectionWrapper createQueueConnectionWrapper() {
        return new QueueConnectionWrapper();
    }
    
    public final QueueConnectionWrapper wrapQueueConnection(QueueConnection parent) throws JMSException {
        QueueConnectionWrapper wrapper = createQueueConnectionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }
    
    protected SessionWrapper createSessionWrapper() {
        return new SessionWrapper();
    }
    
    // TODO: public?
    final SessionWrapper wrapSession(Session parent) throws JMSException {
        SessionWrapper wrapper = createSessionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }
}
