package net.sf.jwrappers.jms;

import javax.jms.ConnectionConsumer;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;

public class TopicConnectionWrapper implements TopicConnection {
    WrapperFactory wrapperFactory;
    TopicConnection parent;
    ConnectionWrapper baseWrapper;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws JMSException if an error occurs
     */
    protected void init() throws JMSException {
    }

    public TopicConnection unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    public void close() throws JMSException {
        baseWrapper.close();
    }

    public ConnectionConsumer createConnectionConsumer(Destination arg0, String arg1, ServerSessionPool arg2, int arg3) throws JMSException {
        return baseWrapper.createConnectionConsumer(arg0, arg1, arg2, arg3);
    }

    public ConnectionConsumer createDurableConnectionConsumer(Topic arg0, String arg1, String arg2, ServerSessionPool arg3, int arg4) throws JMSException {
        return baseWrapper.createDurableConnectionConsumer(arg0, arg1, arg2, arg3, arg4);
    }

    public Session createSession(boolean arg0, int arg1) throws JMSException {
        return baseWrapper.createSession(arg0, arg1);
    }

    public String getClientID() throws JMSException {
        return baseWrapper.getClientID();
    }

    public ExceptionListener getExceptionListener() throws JMSException {
        return baseWrapper.getExceptionListener();
    }

    public ConnectionMetaData getMetaData() throws JMSException {
        return baseWrapper.getMetaData();
    }

    public void setClientID(String arg0) throws JMSException {
        baseWrapper.setClientID(arg0);
    }

    public void setExceptionListener(ExceptionListener arg0) throws JMSException {
        baseWrapper.setExceptionListener(arg0);
    }

    public void start() throws JMSException {
        baseWrapper.start();
    }

    public void stop() throws JMSException {
        baseWrapper.stop();
    }

    /**
     * Delegate method for {@link TopicConnection#createConnectionConsumer(Topic, String, ServerSessionPool, int)}.
     * 
     * {@inheritDoc}
     */
    public ConnectionConsumer createConnectionConsumer(Topic arg0, String arg1, ServerSessionPool arg2, int arg3) throws JMSException {
        return parent.createConnectionConsumer(arg0, arg1, arg2, arg3);
    }

    /**
     * Delegate method for {@link TopicConnection#createTopicSession(boolean, int)}.
     * This method wraps the {@link TopicSession} object using
     * {@link WrapperFactory#wrapTopicSession(TopicSession)}.
     * 
     * {@inheritDoc}
     */
    public TopicSession createTopicSession(boolean arg0, int arg1) throws JMSException {
        return wrapperFactory.wrapTopicSession(parent.createTopicSession(arg0, arg1));
    }
}
