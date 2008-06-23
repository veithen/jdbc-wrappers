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
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

public class SessionWrapper implements Session {
    WrapperFactory wrapperFactory;
    Session parent;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws JMSException if an error occurs
     */
    protected void init() throws JMSException {
    }

    public Session unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    /**
     * Delegate method for {@link Session#close()}.
     * 
     * {@inheritDoc}
     */
    public void close() throws JMSException {
        parent.close();
    }

    /**
     * Delegate method for {@link Session#commit()}.
     * 
     * {@inheritDoc}
     */
    public void commit() throws JMSException {
        parent.commit();
    }

    /**
     * Delegate method for {@link Session#createBrowser(Queue)}.
     * 
     * {@inheritDoc}
     */
    public QueueBrowser createBrowser(Queue arg0) throws JMSException {
        return parent.createBrowser(arg0);
    }

    /**
     * Delegate method for {@link Session#createBrowser(Queue, String)}.
     * 
     * {@inheritDoc}
     */
    public QueueBrowser createBrowser(Queue arg0, String arg1) throws JMSException {
        return parent.createBrowser(arg0, arg1);
    }

    /**
     * Delegate method for {@link Session#createBytesMessage()}.
     * 
     * {@inheritDoc}
     */
    public BytesMessage createBytesMessage() throws JMSException {
        return parent.createBytesMessage();
    }

    /**
     * Delegate method for {@link Session#createConsumer(Destination)}.
     * This method wraps the {@link MessageConsumer} object using
     * {@link WrapperFactory#wrapMessageConsumer(MessageConsumer)}.
     * 
     * {@inheritDoc}
     */
    public MessageConsumer createConsumer(Destination arg0) throws JMSException {
        return wrapperFactory.wrapMessageConsumer(parent.createConsumer(arg0));
    }

    /**
     * Delegate method for {@link Session#createConsumer(Destination, String)}.
     * This method wraps the {@link MessageConsumer} object using
     * {@link WrapperFactory#wrapMessageConsumer(MessageConsumer)}.
     * 
     * {@inheritDoc}
     */
    public MessageConsumer createConsumer(Destination arg0, String arg1) throws JMSException {
        return wrapperFactory.wrapMessageConsumer(parent.createConsumer(arg0, arg1));
    }

    /**
     * Delegate method for {@link Session#createConsumer(Destination, String, boolean)}.
     * This method wraps the {@link MessageConsumer} object using
     * {@link WrapperFactory#wrapMessageConsumer(MessageConsumer)}.
     * 
     * {@inheritDoc}
     */
    public MessageConsumer createConsumer(Destination arg0, String arg1, boolean arg2) throws JMSException {
        return wrapperFactory.wrapMessageConsumer(parent.createConsumer(arg0, arg1, arg2));
    }

    /**
     * Delegate method for {@link Session#createDurableSubscriber(Topic, String)}.
     * This method wraps the {@link TopicSubscriber} object using
     * {@link WrapperFactory#wrapTopicSubscriber(TopicSubscriber)}.
     * 
     * {@inheritDoc}
     */
    public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1) throws JMSException {
        return wrapperFactory.wrapTopicSubscriber(parent.createDurableSubscriber(arg0, arg1));
    }

    /**
     * Delegate method for {@link Session#createDurableSubscriber(Topic, String, String, boolean)}.
     * This method wraps the {@link TopicSubscriber} object using
     * {@link WrapperFactory#wrapTopicSubscriber(TopicSubscriber)}.
     * 
     * {@inheritDoc}
     */
    public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
        return wrapperFactory.wrapTopicSubscriber(parent.createDurableSubscriber(arg0, arg1, arg2, arg3));
    }

    /**
     * Delegate method for {@link Session#createMapMessage()}.
     * 
     * {@inheritDoc}
     */
    public MapMessage createMapMessage() throws JMSException {
        return parent.createMapMessage();
    }

    /**
     * Delegate method for {@link Session#createMessage()}.
     * 
     * {@inheritDoc}
     */
    public Message createMessage() throws JMSException {
        return parent.createMessage();
    }

    /**
     * Delegate method for {@link Session#createObjectMessage()}.
     * 
     * {@inheritDoc}
     */
    public ObjectMessage createObjectMessage() throws JMSException {
        return parent.createObjectMessage();
    }

    /**
     * Delegate method for {@link Session#createObjectMessage(Serializable)}.
     * 
     * {@inheritDoc}
     */
    public ObjectMessage createObjectMessage(Serializable arg0) throws JMSException {
        return parent.createObjectMessage(arg0);
    }

    /**
     * Delegate method for {@link Session#createProducer(Destination)}.
     * 
     * {@inheritDoc}
     */
    public MessageProducer createProducer(Destination arg0) throws JMSException {
        return parent.createProducer(arg0);
    }

    /**
     * Delegate method for {@link Session#createQueue(String)}.
     * 
     * {@inheritDoc}
     */
    public Queue createQueue(String arg0) throws JMSException {
        return parent.createQueue(arg0);
    }

    /**
     * Delegate method for {@link Session#createStreamMessage()}.
     * 
     * {@inheritDoc}
     */
    public StreamMessage createStreamMessage() throws JMSException {
        return parent.createStreamMessage();
    }

    /**
     * Delegate method for {@link Session#createTemporaryQueue()}.
     * 
     * {@inheritDoc}
     */
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        return parent.createTemporaryQueue();
    }

    /**
     * Delegate method for {@link Session#createTemporaryTopic()}.
     * 
     * {@inheritDoc}
     */
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        return parent.createTemporaryTopic();
    }

    /**
     * Delegate method for {@link Session#createTextMessage()}.
     * 
     * {@inheritDoc}
     */
    public TextMessage createTextMessage() throws JMSException {
        return parent.createTextMessage();
    }

    /**
     * Delegate method for {@link Session#createTextMessage(String)}.
     * 
     * {@inheritDoc}
     */
    public TextMessage createTextMessage(String arg0) throws JMSException {
        return parent.createTextMessage(arg0);
    }

    /**
     * Delegate method for {@link Session#createTopic(String)}.
     * 
     * {@inheritDoc}
     */
    public Topic createTopic(String arg0) throws JMSException {
        return parent.createTopic(arg0);
    }

    /**
     * Delegate method for {@link Session#getAcknowledgeMode()}.
     * 
     * {@inheritDoc}
     */
    public int getAcknowledgeMode() throws JMSException {
        return parent.getAcknowledgeMode();
    }

    /**
     * Delegate method for {@link Session#getMessageListener()}.
     * 
     * {@inheritDoc}
     */
    public MessageListener getMessageListener() throws JMSException {
        return parent.getMessageListener();
    }

    /**
     * Delegate method for {@link Session#getTransacted()}.
     * 
     * {@inheritDoc}
     */
    public boolean getTransacted() throws JMSException {
        return parent.getTransacted();
    }

    /**
     * Delegate method for {@link Session#recover()}.
     * 
     * {@inheritDoc}
     */
    public void recover() throws JMSException {
        parent.recover();
    }

    /**
     * Delegate method for {@link Session#rollback()}.
     * 
     * {@inheritDoc}
     */
    public void rollback() throws JMSException {
        parent.rollback();
    }

    /**
     * Delegate method for {@link Session#run()}.
     * 
     * {@inheritDoc}
     */
    public void run() {
        parent.run();
    }

    /**
     * Delegate method for {@link Session#setMessageListener(MessageListener)}.
     * 
     * {@inheritDoc}
     */
    public void setMessageListener(MessageListener arg0) throws JMSException {
        parent.setMessageListener(arg0);
    }

    /**
     * Delegate method for {@link Session#unsubscribe(String)}.
     * 
     * {@inheritDoc}
     */
    public void unsubscribe(String arg0) throws JMSException {
        parent.unsubscribe(arg0);
    }
}
