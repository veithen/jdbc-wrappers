package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.ConnectionConsumer;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;

public class ConnectionWrapper implements Connection {
    WrapperFactory wrapperFactory;
    Connection parent;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws JMSException if an error occurs
     */
    protected void init() throws JMSException {
    }

    public Connection unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    /**
     * Delegate method for {@link Connection#close()}.
     * 
     * {@inheritDoc}
     */
    public void close() throws JMSException {
        parent.close();
    }

    /**
     * Delegate method for {@link Connection#createConnectionConsumer(Destination, String, ServerSessionPool, int)}.
     * 
     * {@inheritDoc}
     */
    public ConnectionConsumer createConnectionConsumer(Destination arg0, String arg1, ServerSessionPool arg2, int arg3) throws JMSException {
        return parent.createConnectionConsumer(arg0, arg1, arg2, arg3);
    }

    /**
     * Delegate method for {@link Connection#createDurableConnectionConsumer(Topic, String, String, ServerSessionPool, int)}.
     * 
     * {@inheritDoc}
     */
    public ConnectionConsumer createDurableConnectionConsumer(Topic arg0, String arg1, String arg2, ServerSessionPool arg3, int arg4) throws JMSException {
        return parent.createDurableConnectionConsumer(arg0, arg1, arg2, arg3, arg4);
    }

    /**
     * Delegate method for {@link Connection#createSession(boolean, int)}.
     * This method wraps the {@link Session} object using
     * {@link WrapperFactory#wrapSession(Session)}.
     * 
     * {@inheritDoc}
     */
    public Session createSession(boolean arg0, int arg1) throws JMSException {
        return wrapperFactory.wrapSession(parent.createSession(arg0, arg1));
    }

    /**
     * Delegate method for {@link Connection#getClientID()}.
     * 
     * {@inheritDoc}
     */
    public String getClientID() throws JMSException {
        return parent.getClientID();
    }

    /**
     * Delegate method for {@link Connection#getExceptionListener()}.
     * 
     * {@inheritDoc}
     */
    public ExceptionListener getExceptionListener() throws JMSException {
        return parent.getExceptionListener();
    }

    /**
     * Delegate method for {@link Connection#getMetaData()}.
     * 
     * {@inheritDoc}
     */
    public ConnectionMetaData getMetaData() throws JMSException {
        return parent.getMetaData();
    }

    /**
     * Delegate method for {@link Connection#setClientID(String)}.
     * 
     * {@inheritDoc}
     */
    public void setClientID(String arg0) throws JMSException {
        parent.setClientID(arg0);
    }

    /**
     * Delegate method for {@link Connection#setExceptionListener(ExceptionListener)}.
     * 
     * {@inheritDoc}
     */
    public void setExceptionListener(ExceptionListener arg0) throws JMSException {
        parent.setExceptionListener(arg0);
    }

    /**
     * Delegate method for {@link Connection#start()}.
     * 
     * {@inheritDoc}
     */
    public void start() throws JMSException {
        parent.start();
    }

    /**
     * Delegate method for {@link Connection#stop()}.
     * 
     * {@inheritDoc}
     */
    public void stop() throws JMSException {
        parent.stop();
    }
}
