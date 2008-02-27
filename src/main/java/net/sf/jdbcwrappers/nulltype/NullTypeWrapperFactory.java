package net.sf.jdbcwrappers.nulltype;

import net.sf.jdbcwrappers.PreparedStatementWrapper;
import net.sf.jdbcwrappers.WrapperFactory;

public class NullTypeWrapperFactory extends WrapperFactory {
	@Override
	public PreparedStatementWrapper createPreparedStatementWrapper() {
		return new NullTypePreparedStatementWrapper();
	}
}
