package net.sf.jwrappers.jdbc.trim;

import net.sf.jwrappers.jdbc.ResultSetType;
import net.sf.jwrappers.jdbc.ResultSetWrapper;
import net.sf.jwrappers.jdbc.WrapperFactory;

public class TrimmingWrapperFactory extends WrapperFactory {
	@Override
	protected ResultSetWrapper createResultSetWrapper(ResultSetType resultSetType) {
		return resultSetType == ResultSetType.QUERY ? new TrimmingResultSetWrapper() : super.createResultSetWrapper(resultSetType);
	}
}
