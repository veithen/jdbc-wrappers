package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

public class AbstractConnectionFactoryWrapper<T extends ConnectionFactory> extends AbstractWrapper<T> {
    ConnectionFactoryWrapper connectionFactoryWrapper;
    
    /**
     * Delegate method for {@link ConnectionFactory#createConnection()}.
     * This method delegates to the linked {@link ConnectionFactoryWrapper} object.
     * 
     * {@inheritDoc}
     */
    public Connection createConnection() throws JMSException {
        return connectionFactoryWrapper.createConnection();
    }

    /**
     * Delegate method for {@link ConnectionFactory#createConnection(String, String)}.
     * This method delegates to the linked {@link ConnectionFactoryWrapper} object.
     * 
     * {@inheritDoc}
     */
    public Connection createConnection(String userName, String password) throws JMSException {
        return connectionFactoryWrapper.createConnection(userName, password);
    }
}
