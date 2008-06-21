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
 * 
 * @author Andreas Veithen
 * @version $Id$
 */
public class WrapperFactory {
    protected ConnectionFactoryWrapper createConnectionFactoryWrapper() {
        return new ConnectionFactoryWrapper();
    }
    
    public ConnectionFactoryWrapper wrapConnectionFactory(ConnectionFactory parent) throws JMSException {
        ConnectionFactoryWrapper wrapper = new ConnectionFactoryWrapper();
        wrapper.init(this, parent);
        return wrapper;
    }
    
    protected ConnectionWrapper createConnectionWrapper() {
        return new ConnectionWrapper();
    }
    
    public ConnectionWrapper wrapConnection(Connection parent) throws JMSException {
        ConnectionWrapper wrapper = new ConnectionWrapper();
        wrapper.init(this, parent);
        return wrapper;
    }
    
    protected SessionWrapper createSessionWrapper() {
        return new SessionWrapper();
    }
}
