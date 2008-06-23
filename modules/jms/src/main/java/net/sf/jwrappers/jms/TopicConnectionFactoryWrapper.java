package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

public class TopicConnectionFactoryWrapper implements TopicConnectionFactory {
    WrapperFactory wrapperFactory;
    TopicConnectionFactory parent;
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

    public TopicConnectionFactory unwrap() {
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
     * Delegate method for {@link TopicConnectionFactory#createTopicConnection()}.
     * This method wraps the {@link TopicConnection} object using
     * {@link WrapperFactory#wrapTopicConnection(TopicConnection)}.
     * 
     * {@inheritDoc}
     */
    public TopicConnection createTopicConnection() throws JMSException {
        return wrapperFactory.wrapTopicConnection(parent.createTopicConnection());
    }

    /**
     * Delegate method for {@link TopicConnectionFactory#createTopicConnection(String, String)}.
     * This method wraps the {@link TopicConnection} object using
     * {@link WrapperFactory#wrapTopicConnection(TopicConnection)}.
     * 
     * {@inheritDoc}
     */
    public TopicConnection createTopicConnection(String arg0, String arg1) throws JMSException {
        return wrapperFactory.wrapTopicConnection(parent.createTopicConnection(arg0, arg1));
    }
}
