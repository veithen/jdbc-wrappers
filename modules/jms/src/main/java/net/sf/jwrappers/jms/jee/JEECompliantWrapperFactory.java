package net.sf.jwrappers.jms.jee;

import net.sf.jwrappers.jms.ConnectionWrapper;
import net.sf.jwrappers.jms.MessageConsumerWrapper;
import net.sf.jwrappers.jms.QueueConnectionWrapper;
import net.sf.jwrappers.jms.SessionWrapper;
import net.sf.jwrappers.jms.TopicConnectionWrapper;
import net.sf.jwrappers.jms.WrapperFactory;

public class JEECompliantWrapperFactory extends WrapperFactory {
    private final boolean warnOnly;
    
    public JEECompliantWrapperFactory(boolean warnOnly) {
        this.warnOnly = warnOnly;
    }

    @Override
    protected ConnectionWrapper createConnectionWrapper() {
        return new JEECompliantConnectionWrapper(warnOnly);
    }

    @Override
    protected QueueConnectionWrapper createQueueConnectionWrapper() {
        return new JEECompliantQueueConnectionWrapper(warnOnly);
    }

    @Override
    protected TopicConnectionWrapper createTopicConnectionWrapper() {
        return new JEECompliantTopicConnectionWrapper(warnOnly);
    }

    @Override
    protected SessionWrapper createSessionWrapper() {
        return new JEECompliantSessionWrapper(warnOnly);
    }

    @Override
    protected MessageConsumerWrapper createMessageConsumerWrapper() {
        return new JEECompliantMessageConsumerWrapper(warnOnly);
    }
}
