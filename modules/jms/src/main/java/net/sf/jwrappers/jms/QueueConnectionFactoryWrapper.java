package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

public class QueueConnectionFactoryWrapper implements QueueConnectionFactory {
    WrapperFactory wrapperFactory;
    QueueConnectionFactory parent;
    ConnectionFactoryWrapper baseWrapper;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws JMSException if an error occurs
     */
    protected void init() throws JMSException {
    }

    public QueueConnectionFactory unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    public Connection createConnection() throws JMSException {
        return baseWrapper.createConnection();
    }

    public Connection createConnection(String arg0, String arg1) throws JMSException {
        return baseWrapper.createConnection(arg0, arg1);
    }

    /**
     * Delegate method for {@link QueueConnectionFactory#createQueueConnection()}.
     * This method wraps the {@link QueueConnection} object using
     * {@link WrapperFactory#wrapQueueConnection(QueueConnection)}.
     * 
     * {@inheritDoc}
     */
    public QueueConnection createQueueConnection() throws JMSException {
        return wrapperFactory.wrapQueueConnection(parent.createQueueConnection());
    }

    /**
     * Delegate method for {@link QueueConnectionFactory#createQueueConnection(String, String)}.
     * This method wraps the {@link QueueConnection} object using
     * {@link WrapperFactory#wrapQueueConnection(QueueConnection)}.
     * 
     * {@inheritDoc}
     */
    public QueueConnection createQueueConnection(String arg0, String arg1) throws JMSException {
        return wrapperFactory.wrapQueueConnection(parent.createQueueConnection(arg0, arg1));
    }
}
