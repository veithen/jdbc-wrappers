package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

public class QueueConnectionFactoryWrapper extends AbstractWrapper<QueueConnectionFactory> implements QueueConnectionFactory {
    final void init(WrapperFactory wrapperFactory, QueueConnectionFactory parent) {
        this.wrapperFactory = wrapperFactory;
        this.parent = parent;
    }
    
    public Connection createConnection() throws JMSException {
        return parent.createConnection();
    }

    public Connection createConnection(String userName, String password)
            throws JMSException {
        return parent.createConnection(userName, password);
    }

    public QueueConnection createQueueConnection() throws JMSException {
        return parent.createQueueConnection();
    }

    public QueueConnection createQueueConnection(String userName,
            String password) throws JMSException {
        return parent.createQueueConnection(userName, password);
    }
}
