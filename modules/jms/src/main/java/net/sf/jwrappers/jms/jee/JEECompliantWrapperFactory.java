package net.sf.jwrappers.jms.jee;

import net.sf.jwrappers.jms.ConnectionWrapper;
import net.sf.jwrappers.jms.SessionWrapper;
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
    protected SessionWrapper createSessionWrapper() {
        return new JEECompliantSessionWrapper(warnOnly);
    }
}
