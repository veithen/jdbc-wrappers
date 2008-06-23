package net.sf.jwrappers.jms;

import javax.jms.ConnectionConsumer;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;

public class QueueConnectionWrapper implements QueueConnection {
    WrapperFactory wrapperFactory;
    QueueConnection parent;
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

    public QueueConnection unwrap() {
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
     * Delegate method for {@link QueueConnection#createConnectionConsumer(Queue, String, ServerSessionPool, int)}.
     * 
     * {@inheritDoc}
     */
    public ConnectionConsumer createConnectionConsumer(Queue arg0, String arg1, ServerSessionPool arg2, int arg3) throws JMSException {
        return parent.createConnectionConsumer(arg0, arg1, arg2, arg3);
    }

    /**
     * Delegate method for {@link QueueConnection#createQueueSession(boolean, int)}.
     * This method wraps the {@link QueueSession} object using
     * {@link WrapperFactory#wrapQueueSession(QueueSession)}.
     * 
     * {@inheritDoc}
     */
    public QueueSession createQueueSession(boolean arg0, int arg1) throws JMSException {
        return wrapperFactory.wrapQueueSession(parent.createQueueSession(arg0, arg1));
    }
}
