package net.sf.jwrappers.jms;

import javax.jms.ConnectionConsumer;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.ServerSessionPool;

public class QueueConnectionWrapper extends AbstractConnectionWrapper<QueueConnection> implements QueueConnection {
    public ConnectionConsumer createConnectionConsumer(Queue queue, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return parent.createConnectionConsumer(queue, messageSelector, sessionPool, maxMessages);
    }

    public QueueSession createQueueSession(boolean transacted, int acknowledgeMode) throws JMSException {
        return parent.createQueueSession(transacted, acknowledgeMode);
    }
}
