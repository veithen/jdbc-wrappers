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

public class AbstractConnectionWrapper<T extends Connection> extends AbstractWrapper<T> {
    ConnectionWrapper connectionWrapper;

    public void close() throws JMSException {
        connectionWrapper.close();
    }

    public ConnectionConsumer createConnectionConsumer(Destination destination, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return connectionWrapper.createConnectionConsumer(destination, messageSelector, sessionPool, maxMessages);
    }

    public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return connectionWrapper.createDurableConnectionConsumer(topic, subscriptionName, messageSelector, sessionPool, maxMessages);
    }

    public Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
        return connectionWrapper.createSession(transacted, acknowledgeMode);
    }

    public String getClientID() throws JMSException {
        return connectionWrapper.getClientID();
    }

    public ExceptionListener getExceptionListener() throws JMSException {
        return connectionWrapper.getExceptionListener();
    }

    public ConnectionMetaData getMetaData() throws JMSException {
        return connectionWrapper.getMetaData();
    }

    public void setClientID(String clientID) throws JMSException {
        connectionWrapper.setClientID(clientID);
    }

    public void setExceptionListener(ExceptionListener listener) throws JMSException {
        connectionWrapper.setExceptionListener(listener);
    }

    public void start() throws JMSException {
        connectionWrapper.start();
    }

    public void stop() throws JMSException {
        connectionWrapper.stop();
    }
}
