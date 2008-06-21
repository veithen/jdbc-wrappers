package net.sf.jwrappers.jdbc.nulltype;

import net.sf.jwrappers.jdbc.PreparedStatementWrapper;
import net.sf.jwrappers.jdbc.WrapperFactory;

public class NullTypeWrapperFactory extends WrapperFactory {
	@Override
	protected PreparedStatementWrapper createPreparedStatementWrapper() {
		return new NullTypePreparedStatementWrapper();
	}
}
