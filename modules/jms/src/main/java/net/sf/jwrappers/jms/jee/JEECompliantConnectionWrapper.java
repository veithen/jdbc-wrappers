package net.sf.jwrappers.jms.jee;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jwrappers.jms.ConnectionWrapper;

public class JEECompliantConnectionWrapper extends ConnectionWrapper {
    private static final Log log = LogFactory.getLog(JEECompliantConnectionWrapper.class);
    
    private final boolean warnOnly;
    
    public JEECompliantConnectionWrapper(boolean warnOnly) {
        this.warnOnly = warnOnly;
    }

    @Override
    public void setClientID(String clientID) throws JMSException {
        String msg = "setClientID(String) not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            super.setClientID(clientID);
        } else {
            throw new JMSException(msg);
        }
    }
    
    @Override
    public void setExceptionListener(ExceptionListener listener) throws JMSException {
        String msg = "setExceptionListener(ExceptionListener) not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            super.setExceptionListener(listener);
        } else {
            throw new JMSException(msg);
        }
    }
    
    @Override
    public void stop() throws JMSException {
        String msg = "stop() not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            super.stop();
        } else {
            throw new JMSException(msg);
        }
    }
}
