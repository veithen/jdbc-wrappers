package net.sf.jwrappers.jms.jee;

import javax.jms.ConnectionConsumer;
import javax.jms.JMSException;
import javax.jms.ServerSessionPool;
import javax.jms.Topic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jwrappers.jms.TopicConnectionWrapper;

public class JEECompliantTopicConnectionWrapper extends TopicConnectionWrapper {
    private static final Log log = LogFactory.getLog(JEECompliantTopicConnectionWrapper.class);
    
    private final boolean warnOnly;
    
    public JEECompliantTopicConnectionWrapper(boolean warnOnly) {
        this.warnOnly = warnOnly;
    }

    @Override
    public ConnectionConsumer createConnectionConsumer(Topic arg0, String arg1, ServerSessionPool arg2, int arg3) throws JMSException {
        String msg = "createConnectionConsumer not allowed in a JEE environment";
        if (warnOnly) {
            log.warn(msg);
            return super.createConnectionConsumer(arg0, arg1, arg2, arg3);
        } else {
            throw new JMSException(msg);
        }
    }
}
