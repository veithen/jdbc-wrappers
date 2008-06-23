package net.sf.jwrappers.jms.jee;

import javax.jms.JMSException;
import javax.jms.MessageListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jwrappers.jms.MessageConsumerWrapper;

public class JEECompliantMessageConsumerWrapper extends MessageConsumerWrapper {
    private static final Log log = LogFactory.getLog(JEECompliantMessageConsumerWrapper.class);
    
    private final boolean warnOnly;
    
    public JEECompliantMessageConsumerWrapper(boolean warnOnly) {
        this.warnOnly = warnOnly;
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        String msg = "getMessageListener() not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            return super.getMessageListener();
        } else {
            throw new JMSException(msg);
        }
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        String msg = "setMessageListener(MessageListener) not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            super.setMessageListener(listener);
        } else {
            throw new JMSException(msg);
        }
    }
}
