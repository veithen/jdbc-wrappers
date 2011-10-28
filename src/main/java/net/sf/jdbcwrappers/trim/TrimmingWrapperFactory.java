package net.sf.jdbcwrappers.trim;

import net.sf.jdbcwrappers.ResultSetType;
import net.sf.jdbcwrappers.ResultSetWrapper;
import net.sf.jdbcwrappers.WrapperFactory;

public class TrimmingWrapperFactory extends WrapperFactory {
	@Override
	protected ResultSetWrapper createResultSetWrapper(ResultSetType resultSetType) {
		return resultSetType == ResultSetType.QUERY ? new TrimmingResultSetWrapper() : super.createResultSetWrapper(resultSetType);
	}
}
