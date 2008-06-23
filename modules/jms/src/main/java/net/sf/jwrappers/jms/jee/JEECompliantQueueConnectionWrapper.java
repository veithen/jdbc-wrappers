package net.sf.jwrappers.jms.jee;

import javax.jms.ConnectionConsumer;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.ServerSessionPool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jwrappers.jms.QueueConnectionWrapper;

public class JEECompliantQueueConnectionWrapper extends QueueConnectionWrapper {
    private static final Log log = LogFactory.getLog(JEECompliantQueueConnectionWrapper.class);
    
    private final boolean warnOnly;
    
    public JEECompliantQueueConnectionWrapper(boolean warnOnly) {
        this.warnOnly = warnOnly;
    }

    @Override
    public ConnectionConsumer createConnectionConsumer(Queue arg0, String arg1, ServerSessionPool arg2, int arg3) throws JMSException {
        String msg = "createConnectionConsumer not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            return super.createConnectionConsumer(arg0, arg1, arg2, arg3);
        } else {
            throw new JMSException(msg);
        }
    }
}
