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

public class SessionWrapper extends AbstractWrapper<Session> implements Session {
    public void close() throws JMSException {
        parent.close();
    }

    public void commit() throws JMSException {
        parent.commit();
    }

    public QueueBrowser createBrowser(Queue queue, String messageSelector) throws JMSException {
        return parent.createBrowser(queue, messageSelector);
    }

    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        return parent.createBrowser(queue);
    }

    public BytesMessage createBytesMessage() throws JMSException {
        return parent.createBytesMessage();
    }

    public MessageConsumer createConsumer(Destination destination,
            String messageSelector, boolean NoLocal) throws JMSException {
        return parent.createConsumer(destination, messageSelector, NoLocal);
    }

    public MessageConsumer createConsumer(Destination destination,
            String messageSelector) throws JMSException {
        return parent.createConsumer(destination, messageSelector);
    }

    public MessageConsumer createConsumer(Destination destination)
            throws JMSException {
        return parent.createConsumer(destination);
    }

    public TopicSubscriber createDurableSubscriber(Topic topic, String name,
            String messageSelector, boolean noLocal) throws JMSException {
        return parent.createDurableSubscriber(topic, name, messageSelector,
                noLocal);
    }

    public TopicSubscriber createDurableSubscriber(Topic topic, String name)
            throws JMSException {
        return parent.createDurableSubscriber(topic, name);
    }

    public MapMessage createMapMessage() throws JMSException {
        return parent.createMapMessage();
    }

    public Message createMessage() throws JMSException {
        return parent.createMessage();
    }

    public ObjectMessage createObjectMessage() throws JMSException {
        return parent.createObjectMessage();
    }

    public ObjectMessage createObjectMessage(Serializable object)
            throws JMSException {
        return parent.createObjectMessage(object);
    }

    public MessageProducer createProducer(Destination destination)
            throws JMSException {
        return parent.createProducer(destination);
    }

    public Queue createQueue(String queueName) throws JMSException {
        return parent.createQueue(queueName);
    }

    public StreamMessage createStreamMessage() throws JMSException {
        return parent.createStreamMessage();
    }

    public TemporaryQueue createTemporaryQueue() throws JMSException {
        return parent.createTemporaryQueue();
    }

    public TemporaryTopic createTemporaryTopic() throws JMSException {
        return parent.createTemporaryTopic();
    }

    public TextMessage createTextMessage() throws JMSException {
        return parent.createTextMessage();
    }

    public TextMessage createTextMessage(String text) throws JMSException {
        return parent.createTextMessage(text);
    }

    public Topic createTopic(String topicName) throws JMSException {
        return parent.createTopic(topicName);
    }

    public int getAcknowledgeMode() throws JMSException {
        return parent.getAcknowledgeMode();
    }

    public MessageListener getMessageListener() throws JMSException {
        return parent.getMessageListener();
    }

    public boolean getTransacted() throws JMSException {
        return parent.getTransacted();
    }

    public void recover() throws JMSException {
        parent.recover();
    }

    public void rollback() throws JMSException {
        parent.rollback();
    }

    public void run() {
        parent.run();
    }

    public void setMessageListener(MessageListener listener) throws JMSException {
        parent.setMessageListener(listener);
    }

    public void unsubscribe(String name) throws JMSException {
        parent.unsubscribe(name);
    }
}
