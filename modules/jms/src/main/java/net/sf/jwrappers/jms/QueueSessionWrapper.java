package net.sf.jwrappers.jms;

import java.io.Serializable;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

public class QueueSessionWrapper implements QueueSession {
    WrapperFactory wrapperFactory;
    QueueSession parent;
    SessionWrapper baseWrapper;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws JMSException if an error occurs
     */
    protected void init() throws JMSException {
    }

    public QueueSession unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    public void close() throws JMSException {
        baseWrapper.close();
    }

    public void commit() throws JMSException {
        baseWrapper.commit();
    }

    public QueueBrowser createBrowser(Queue arg0) throws JMSException {
        return baseWrapper.createBrowser(arg0);
    }

    public QueueBrowser createBrowser(Queue arg0, String arg1) throws JMSException {
        return baseWrapper.createBrowser(arg0, arg1);
    }

    public BytesMessage createBytesMessage() throws JMSException {
        return baseWrapper.createBytesMessage();
    }

    public MessageConsumer createConsumer(Destination arg0) throws JMSException {
        return baseWrapper.createConsumer(arg0);
    }

    public MessageConsumer createConsumer(Destination arg0, String arg1) throws JMSException {
        return baseWrapper.createConsumer(arg0, arg1);
    }

    public MessageConsumer createConsumer(Destination arg0, String arg1, boolean arg2) throws JMSException {
        return baseWrapper.createConsumer(arg0, arg1, arg2);
    }

    public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1) throws JMSException {
        return baseWrapper.createDurableSubscriber(arg0, arg1);
    }

    public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
        return baseWrapper.createDurableSubscriber(arg0, arg1, arg2, arg3);
    }

    public MapMessage createMapMessage() throws JMSException {
        return baseWrapper.createMapMessage();
    }

    public Message createMessage() throws JMSException {
        return baseWrapper.createMessage();
    }

    public ObjectMessage createObjectMessage() throws JMSException {
        return baseWrapper.createObjectMessage();
    }

    public ObjectMessage createObjectMessage(Serializable arg0) throws JMSException {
        return baseWrapper.createObjectMessage(arg0);
    }

    public MessageProducer createProducer(Destination arg0) throws JMSException {
        return baseWrapper.createProducer(arg0);
    }

    public Queue createQueue(String arg0) throws JMSException {
        return baseWrapper.createQueue(arg0);
    }

    public StreamMessage createStreamMessage() throws JMSException {
        return baseWrapper.createStreamMessage();
    }

    public TemporaryQueue createTemporaryQueue() throws JMSException {
        return baseWrapper.createTemporaryQueue();
    }

    public TemporaryTopic createTemporaryTopic() throws JMSException {
        return baseWrapper.createTemporaryTopic();
    }

    public TextMessage createTextMessage() throws JMSException {
        return baseWrapper.createTextMessage();
    }

    public TextMessage createTextMessage(String arg0) throws JMSException {
        return baseWrapper.createTextMessage(arg0);
    }

    public Topic createTopic(String arg0) throws JMSException {
        return baseWrapper.createTopic(arg0);
    }

    public int getAcknowledgeMode() throws JMSException {
        return baseWrapper.getAcknowledgeMode();
    }

    public MessageListener getMessageListener() throws JMSException {
        return baseWrapper.getMessageListener();
    }

    public boolean getTransacted() throws JMSException {
        return baseWrapper.getTransacted();
    }

    public void recover() throws JMSException {
        baseWrapper.recover();
    }

    public void rollback() throws JMSException {
        baseWrapper.rollback();
    }

    public void run() {
        baseWrapper.run();
    }

    public void setMessageListener(MessageListener arg0) throws JMSException {
        baseWrapper.setMessageListener(arg0);
    }

    public void unsubscribe(String arg0) throws JMSException {
        baseWrapper.unsubscribe(arg0);
    }

    /**
     * Delegate method for {@link QueueSession#createReceiver(Queue)}.
     * This method wraps the {@link QueueReceiver} object using
     * {@link WrapperFactory#wrapQueueReceiver(QueueReceiver)}.
     * 
     * {@inheritDoc}
     */
    public QueueReceiver createReceiver(Queue arg0) throws JMSException {
        return wrapperFactory.wrapQueueReceiver(parent.createReceiver(arg0));
    }

    /**
     * Delegate method for {@link QueueSession#createReceiver(Queue, String)}.
     * This method wraps the {@link QueueReceiver} object using
     * {@link WrapperFactory#wrapQueueReceiver(QueueReceiver)}.
     * 
     * {@inheritDoc}
     */
    public QueueReceiver createReceiver(Queue arg0, String arg1) throws JMSException {
        return wrapperFactory.wrapQueueReceiver(parent.createReceiver(arg0, arg1));
    }

    /**
     * Delegate method for {@link QueueSession#createSender(Queue)}.
     * 
     * {@inheritDoc}
     */
    public QueueSender createSender(Queue arg0) throws JMSException {
        return parent.createSender(arg0);
    }
}
