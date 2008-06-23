package net.sf.jwrappers.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

public class TopicSubscriberWrapper implements TopicSubscriber {
    WrapperFactory wrapperFactory;
    TopicSubscriber parent;
    MessageConsumerWrapper baseWrapper;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws JMSException if an error occurs
     */
    protected void init() throws JMSException {
    }

    public TopicSubscriber unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    public void close() throws JMSException {
        baseWrapper.close();
    }

    public MessageListener getMessageListener() throws JMSException {
        return baseWrapper.getMessageListener();
    }

    public String getMessageSelector() throws JMSException {
        return baseWrapper.getMessageSelector();
    }

    public Message receive() throws JMSException {
        return baseWrapper.receive();
    }

    public Message receive(long arg0) throws JMSException {
        return baseWrapper.receive(arg0);
    }

    public Message receiveNoWait() throws JMSException {
        return baseWrapper.receiveNoWait();
    }

    public void setMessageListener(MessageListener arg0) throws JMSException {
        baseWrapper.setMessageListener(arg0);
    }

    /**
     * Delegate method for {@link TopicSubscriber#getNoLocal()}.
     * 
     * {@inheritDoc}
     */
    public boolean getNoLocal() throws JMSException {
        return parent.getNoLocal();
    }

    /**
     * Delegate method for {@link TopicSubscriber#getTopic()}.
     * 
     * {@inheritDoc}
     */
    public Topic getTopic() throws JMSException {
        return parent.getTopic();
    }
}
