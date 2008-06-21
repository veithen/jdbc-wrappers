package net.sf.jwrappers.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

public class TopicConnectionFactoryWrapper extends AbstractWrapper<TopicConnectionFactory> implements TopicConnectionFactory {
    public Connection createConnection() throws JMSException {
        return parent.createConnection();
    }

    public Connection createConnection(String userName, String password)
            throws JMSException {
        return parent.createConnection(userName, password);
    }

    public TopicConnection createTopicConnection() throws JMSException {
        return parent.createTopicConnection();
    }

    public TopicConnection createTopicConnection(String userName,
            String password) throws JMSException {
        return parent.createTopicConnection(userName, password);
    }
}
