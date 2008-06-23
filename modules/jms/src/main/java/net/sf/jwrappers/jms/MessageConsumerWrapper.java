package net.sf.jwrappers.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

public class MessageConsumerWrapper implements MessageConsumer {
    WrapperFactory wrapperFactory;
    MessageConsumer parent;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws JMSException if an error occurs
     */
    protected void init() throws JMSException {
    }

    public MessageConsumer unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    /**
     * Delegate method for {@link MessageConsumer#close()}.
     * 
     * {@inheritDoc}
     */
    public void close() throws JMSException {
        parent.close();
    }

    /**
     * Delegate method for {@link MessageConsumer#getMessageListener()}.
     * 
     * {@inheritDoc}
     */
    public MessageListener getMessageListener() throws JMSException {
        return parent.getMessageListener();
    }

    /**
     * Delegate method for {@link MessageConsumer#getMessageSelector()}.
     * 
     * {@inheritDoc}
     */
    public String getMessageSelector() throws JMSException {
        return parent.getMessageSelector();
    }

    /**
     * Delegate method for {@link MessageConsumer#receive()}.
     * 
     * {@inheritDoc}
     */
    public Message receive() throws JMSException {
        return parent.receive();
    }

    /**
     * Delegate method for {@link MessageConsumer#receive(long)}.
     * 
     * {@inheritDoc}
     */
    public Message receive(long arg0) throws JMSException {
        return parent.receive(arg0);
    }

    /**
     * Delegate method for {@link MessageConsumer#receiveNoWait()}.
     * 
     * {@inheritDoc}
     */
    public Message receiveNoWait() throws JMSException {
        return parent.receiveNoWait();
    }

    /**
     * Delegate method for {@link MessageConsumer#setMessageListener(MessageListener)}.
     * 
     * {@inheritDoc}
     */
    public void setMessageListener(MessageListener arg0) throws JMSException {
        parent.setMessageListener(arg0);
    }
}
