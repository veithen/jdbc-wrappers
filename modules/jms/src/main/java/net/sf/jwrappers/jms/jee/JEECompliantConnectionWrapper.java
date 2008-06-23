package net.sf.jwrappers.jms.jee;

import javax.jms.ConnectionConsumer;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.ServerSessionPool;
import javax.jms.Topic;

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
    public ConnectionConsumer createConnectionConsumer(Destination arg0, String arg1, ServerSessionPool arg2, int arg3) throws JMSException {
        String msg = "createConnectionConsumer not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            return super.createConnectionConsumer(arg0, arg1, arg2, arg3);
        } else {
            throw new JMSException(msg);
        }
    }

    @Override
    public ConnectionConsumer createDurableConnectionConsumer(Topic arg0, String arg1, String arg2, ServerSessionPool arg3, int arg4) throws JMSException {
        String msg = "createDurableConnectionConsumer not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            return super.createDurableConnectionConsumer(arg0, arg1, arg2, arg3, arg4);
        } else {
            throw new JMSException(msg);
        }
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
