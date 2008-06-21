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

public class ConnectionWrapper extends AbstractWrapper<Connection> implements Connection {
    final void init(WrapperFactory wrapperFactory, Connection parent) throws JMSException {
        this.wrapperFactory = wrapperFactory;
        this.parent = parent;
        init();
    }

    public void close() throws JMSException {
        parent.close();
    }

    public ConnectionConsumer createConnectionConsumer(Destination destination, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return parent.createConnectionConsumer(destination, messageSelector, sessionPool, maxMessages);
    }

    public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return parent.createDurableConnectionConsumer(topic, subscriptionName, messageSelector, sessionPool, maxMessages);
    }

    public Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
        return parent.createSession(transacted, acknowledgeMode);
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

    public ConnectionMetaData getMetaData() throws JMSException {
        return parent.getMetaData();
    }

    /**
     * Delegate method for {@link Connection#setClientID(String)}.
     * 
     * {@inheritDoc}
     */
    public void setClientID(String clientID) throws JMSException {
        parent.setClientID(clientID);
    }

    /**
     * Delegate method for {@link Connection#setExceptionListener(ExceptionListener)}.
     * 
     * {@inheritDoc}
     */
    public void setExceptionListener(ExceptionListener listener) throws JMSException {
        parent.setExceptionListener(listener);
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
