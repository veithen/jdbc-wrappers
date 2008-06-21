package net.sf.jwrappers.jdbc.nulltype;

import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.sql.Types;

import net.sf.jwrappers.jdbc.PreparedStatementWrapper;

public class NullTypePreparedStatementWrapper extends PreparedStatementWrapper {
	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		if (sqlType == Types.NULL) {
			ParameterMetaData parameterMetaData = getParameterMetaData();
			sqlType = parameterMetaData.getParameterType(parameterIndex);
		}
		super.setNull(parameterIndex, sqlType);
	}
}
