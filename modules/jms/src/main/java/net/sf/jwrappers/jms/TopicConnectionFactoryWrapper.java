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

import javax.jms.JMSException;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

/**
 * Base class for {@link TopicConnectionFactory} wrappers.
 * 
 * @author Andreas Veithen
 * @version $Id$
 */
public class TopicConnectionFactoryWrapper extends AbstractConnectionFactoryWrapper<TopicConnectionFactory> implements TopicConnectionFactory {
    /**
     * Delegate method for {@link TopicConnectionFactory#createTopicConnection()}.
     * This method wraps the {@link TopicConnection} object using
     * {@link WrapperFactory#wrapTopicConnection(TopicConnection)}.
     * 
     * {@inheritDoc}
     */
    public TopicConnection createTopicConnection() throws JMSException {
        // TODO
        return parent.createTopicConnection();
    }

    /**
     * Delegate method for {@link TopicConnectionFactory#createTopicConnection(String, String)}.
     * This method wraps the {@link TopicConnection} object using
     * {@link WrapperFactory#wrapTopicConnection(TopicConnection)}.
     * 
     * {@inheritDoc}
     */
    public TopicConnection createTopicConnection(String userName, String password) throws JMSException {
        // TODO
        return parent.createTopicConnection(userName, password);
    }
}
