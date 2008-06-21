package net.sf.jwrappers.jms.jee;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;

import net.sf.jwrappers.jms.ConnectionWrapper;
import net.sf.jwrappers.jms.WrapperFactory;

import org.junit.Before;
import org.junit.Test;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockConnection;
import com.mockrunner.mock.jms.MockConnectionFactory;
import com.mockrunner.mock.jms.MockServerSessionPool;

public class JEECompliantTest {
    private static final MessageListener noopMessageListener = new MessageListener() {
        public void onMessage(Message message) {
        }
    };
    
    private static final ExceptionListener noopExceptionListener = new ExceptionListener() {
        public void onException(JMSException exception) {
        }
    };
    
    private Queue queue;
    private Topic topic;
    
    private Session session;
    private QueueConnection queueConnection;
    private TopicConnection topicConnection;
    private MessageConsumer messageConsumer;
    private Connection connection;
    private ServerSessionPool sessionPool;
    
    @Before
    public void init() throws Exception {
        DestinationManager destinationManager = new DestinationManager();
        queue = destinationManager.createQueue("test");
        topic = destinationManager.createTopic("test");
        ConfigurationManager configurationManager = new ConfigurationManager();
        MockConnectionFactory cf = new MockConnectionFactory(destinationManager, configurationManager);
        WrapperFactory wrapperFactory = new JEECompliantWrapperFactory(false);
        ConnectionFactory wrapper = wrapperFactory.wrapConnectionFactory(cf);
        connection = wrapper.createConnection();
        session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        wrapperFactory.setAllowUnwrap(true);
        sessionPool = new MockServerSessionPool((MockConnection)((ConnectionWrapper)connection).unwrap());
        wrapperFactory.setAllowUnwrap(false);
    }
    
    @Test(expected=JMSException.class)
    public void testSessionSetMessageListener() throws JMSException {
        session.setMessageListener(noopMessageListener);
    }
    
    @Test(expected=JMSException.class)
    public void testSessionGetMessageListener() throws JMSException {
        session.getMessageListener();
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testSessionRun() {
        session.run();
    }
    
    @Test(expected=JMSException.class)
    public void testQueueConnectionCreateConnectionConsumer() throws JMSException {
        queueConnection.createConnectionConsumer(queue, null, sessionPool, 1);
    }
    
    @Test(expected=JMSException.class)
    public void testTopicConnectionCreateConnectionConsumer() throws JMSException {
        topicConnection.createConnectionConsumer(topic, null, sessionPool, 1);
    }
    
    @Test(expected=JMSException.class)
    public void testTopicConnectionCreateDurableConnectionConsumer() throws JMSException {
        topicConnection.createDurableConnectionConsumer(topic, "testSubscription", null, sessionPool, 1);
    }
    
    @Test(expected=JMSException.class)
    public void testMessageConsumerGetMessageListener() throws JMSException {
        messageConsumer.getMessageListener();
    }
    
    @Test(expected=JMSException.class)
    public void testMessageConsumerSetMessageListener() throws JMSException {
        messageConsumer.setMessageListener(noopMessageListener);
    }
    
    @Test(expected=JMSException.class)
    public void testConnectionSetExceptionListener() throws JMSException {
        connection.setExceptionListener(noopExceptionListener);
    }
    
    @Test(expected=JMSException.class)
    public void testConnectionStop() throws JMSException {
        connection.stop();
    }
    
    @Test(expected=JMSException.class)
    public void testConnectionSetClientID() throws JMSException {
        connection.setClientID("test");
    }
}
