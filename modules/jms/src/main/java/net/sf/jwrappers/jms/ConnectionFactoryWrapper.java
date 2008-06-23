package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

public class ConnectionFactoryWrapper implements ConnectionFactory {
    WrapperFactory wrapperFactory;
    ConnectionFactory parent;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws JMSException if an error occurs
     */
    protected void init() throws JMSException {
    }

    public ConnectionFactory unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
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
    public Connection createConnection(String arg0, String arg1) throws JMSException {
        return wrapperFactory.wrapConnection(parent.createConnection(arg0, arg1));
    }
}
