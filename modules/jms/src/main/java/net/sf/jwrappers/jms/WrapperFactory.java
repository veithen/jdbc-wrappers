package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

/**
 * Wrapper factory base class.
 */
public class WrapperFactory {
    private boolean allowUnwrap;

    public synchronized boolean isAllowUnwrap() {
        return allowUnwrap;
    }

    public synchronized void setAllowUnwrap(boolean allowUnwrap) {
        this.allowUnwrap = allowUnwrap;
    }

    protected ConnectionFactoryWrapper createConnectionFactoryWrapper() {
        return new ConnectionFactoryWrapper();
    }

    public final ConnectionFactoryWrapper wrapConnectionFactory(ConnectionFactory parent) throws JMSException {
        ConnectionFactoryWrapper wrapper = createConnectionFactoryWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }

    protected ConnectionWrapper createConnectionWrapper() {
        return new ConnectionWrapper();
    }

    public final ConnectionWrapper wrapConnection(Connection parent) throws JMSException {
        ConnectionWrapper wrapper = createConnectionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }

    protected QueueConnectionFactoryWrapper createQueueConnectionFactoryWrapper() {
        return new QueueConnectionFactoryWrapper();
    }

    public final QueueConnectionFactoryWrapper wrapQueueConnectionFactory(QueueConnectionFactory parent) throws JMSException {
        QueueConnectionFactoryWrapper wrapper = createQueueConnectionFactoryWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.baseWrapper = wrapConnectionFactory(parent);
        wrapper.init();
        return wrapper;
    }

    protected QueueConnectionWrapper createQueueConnectionWrapper() {
        return new QueueConnectionWrapper();
    }

    public final QueueConnectionWrapper wrapQueueConnection(QueueConnection parent) throws JMSException {
        QueueConnectionWrapper wrapper = createQueueConnectionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.baseWrapper = wrapConnection(parent);
        wrapper.init();
        return wrapper;
    }

    protected TopicConnectionFactoryWrapper createTopicConnectionFactoryWrapper() {
        return new TopicConnectionFactoryWrapper();
    }

    public final TopicConnectionFactoryWrapper wrapTopicConnectionFactory(TopicConnectionFactory parent) throws JMSException {
        TopicConnectionFactoryWrapper wrapper = createTopicConnectionFactoryWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.baseWrapper = wrapConnectionFactory(parent);
        wrapper.init();
        return wrapper;
    }

    protected TopicConnectionWrapper createTopicConnectionWrapper() {
        return new TopicConnectionWrapper();
    }

    public final TopicConnectionWrapper wrapTopicConnection(TopicConnection parent) throws JMSException {
        TopicConnectionWrapper wrapper = createTopicConnectionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.baseWrapper = wrapConnection(parent);
        wrapper.init();
        return wrapper;
    }

    protected SessionWrapper createSessionWrapper() {
        return new SessionWrapper();
    }

    public final SessionWrapper wrapSession(Session parent) throws JMSException {
        SessionWrapper wrapper = createSessionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }

    protected QueueSessionWrapper createQueueSessionWrapper() {
        return new QueueSessionWrapper();
    }

    public final QueueSessionWrapper wrapQueueSession(QueueSession parent) throws JMSException {
        QueueSessionWrapper wrapper = createQueueSessionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.baseWrapper = wrapSession(parent);
        wrapper.init();
        return wrapper;
    }

    protected TopicSessionWrapper createTopicSessionWrapper() {
        return new TopicSessionWrapper();
    }

    public final TopicSessionWrapper wrapTopicSession(TopicSession parent) throws JMSException {
        TopicSessionWrapper wrapper = createTopicSessionWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.baseWrapper = wrapSession(parent);
        wrapper.init();
        return wrapper;
    }

    protected MessageConsumerWrapper createMessageConsumerWrapper() {
        return new MessageConsumerWrapper();
    }

    public final MessageConsumerWrapper wrapMessageConsumer(MessageConsumer parent) throws JMSException {
        MessageConsumerWrapper wrapper = createMessageConsumerWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.init();
        return wrapper;
    }

    protected TopicSubscriberWrapper createTopicSubscriberWrapper() {
        return new TopicSubscriberWrapper();
    }

    public final TopicSubscriberWrapper wrapTopicSubscriber(TopicSubscriber parent) throws JMSException {
        TopicSubscriberWrapper wrapper = createTopicSubscriberWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.baseWrapper = wrapMessageConsumer(parent);
        wrapper.init();
        return wrapper;
    }

    protected QueueReceiverWrapper createQueueReceiverWrapper() {
        return new QueueReceiverWrapper();
    }

    public final QueueReceiverWrapper wrapQueueReceiver(QueueReceiver parent) throws JMSException {
        QueueReceiverWrapper wrapper = createQueueReceiverWrapper();
        wrapper.wrapperFactory = this;
        wrapper.parent = parent;
        wrapper.baseWrapper = wrapMessageConsumer(parent);
        wrapper.init();
        return wrapper;
    }
}
