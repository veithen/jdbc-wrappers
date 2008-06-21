package net.sf.jwrappers.jms;

import javax.jms.ConnectionConsumer;
import javax.jms.JMSException;
import javax.jms.ServerSessionPool;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;

public class TopicConnectionWrapper extends AbstractConnectionWrapper<TopicConnection> implements TopicConnection {
    public ConnectionConsumer createConnectionConsumer(Topic topic, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return parent.createConnectionConsumer(topic, messageSelector, sessionPool, maxMessages);
    }

    public TopicSession createTopicSession(boolean transacted, int acknowledgeMode) throws JMSException {
        return parent.createTopicSession(transacted, acknowledgeMode);
    }
}
