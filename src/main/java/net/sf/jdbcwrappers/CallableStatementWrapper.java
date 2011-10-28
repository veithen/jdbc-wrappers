package net.sf.jdbcwrappers;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class CallableStatementWrapper implements CallableStatement, HasConnection {
    WrapperFactory wrapperFactory;
    CallableStatement parent;
    PreparedStatementWrapper baseWrapper;

    /**
     * Wrapper initialization method. This method is executed once before any
     * delegate method is called on the wrapper. Subclasses can override this
     * method to do initialization work. The default implementation does
     * nothing.
     * @throws SQLException if a database access error occurs
     */
    protected void init() throws SQLException {
    }

    public CallableStatement unwrap() {
        if (wrapperFactory.isAllowUnwrap()) {
            return parent;
        } else {
            throw new IllegalStateException("unwrap not allowed");
        }
    }

    /**
     * Get the SQL statement used to construct the {@link PreparedStatement} object. 
     * 
     * @return the SQL statement
     */
    protected final String getSql() {
        return baseWrapper.getSql();
    }
    
    public void addBatch() throws SQLException {
        baseWrapper.addBatch();
    }

    public void addBatch(String arg0) throws SQLException {
        baseWrapper.addBatch(arg0);
    }

    public void cancel() throws SQLException {
        baseWrapper.cancel();
    }

    public void clearBatch() throws SQLException {
        baseWrapper.clearBatch();
    }

    public void clearParameters() throws SQLException {
        baseWrapper.clearParameters();
    }

    public void clearWarnings() throws SQLException {
        baseWrapper.clearWarnings();
    }

    public void close() throws SQLException {
        baseWrapper.close();
    }

    public boolean execute() throws SQLException {
        return baseWrapper.execute();
    }

    public boolean execute(String arg0) throws SQLException {
        return baseWrapper.execute(arg0);
    }

    public boolean execute(String arg0, int arg1) throws SQLException {
        return baseWrapper.execute(arg0, arg1);
    }

    public boolean execute(String arg0, int[] arg1) throws SQLException {
        return baseWrapper.execute(arg0, arg1);
    }

    public boolean execute(String arg0, String[] arg1) throws SQLException {
        return baseWrapper.execute(arg0, arg1);
    }

    public int[] executeBatch() throws SQLException {
        return baseWrapper.executeBatch();
    }

    public ResultSet executeQuery() throws SQLException {
        return baseWrapper.executeQuery();
    }

    public ResultSet executeQuery(String arg0) throws SQLException {
        return baseWrapper.executeQuery(arg0);
    }

    public int executeUpdate() throws SQLException {
        return baseWrapper.executeUpdate();
    }

    public int executeUpdate(String arg0) throws SQLException {
        return baseWrapper.executeUpdate(arg0);
    }

    public int executeUpdate(String arg0, int arg1) throws SQLException {
        return baseWrapper.executeUpdate(arg0, arg1);
    }

    public int executeUpdate(String arg0, int[] arg1) throws SQLException {
        return baseWrapper.executeUpdate(arg0, arg1);
    }

    public int executeUpdate(String arg0, String[] arg1) throws SQLException {
        return baseWrapper.executeUpdate(arg0, arg1);
    }

    public Connection getConnection() throws SQLException {
        return baseWrapper.getConnection();
    }

    public int getFetchDirection() throws SQLException {
        return baseWrapper.getFetchDirection();
    }

    public int getFetchSize() throws SQLException {
        return baseWrapper.getFetchSize();
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return baseWrapper.getGeneratedKeys();
    }

    public int getMaxFieldSize() throws SQLException {
        return baseWrapper.getMaxFieldSize();
    }

    public int getMaxRows() throws SQLException {
        return baseWrapper.getMaxRows();
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return baseWrapper.getMetaData();
    }

    public boolean getMoreResults() throws SQLException {
        return baseWrapper.getMoreResults();
    }

    public boolean getMoreResults(int arg0) throws SQLException {
        return baseWrapper.getMoreResults(arg0);
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        return baseWrapper.getParameterMetaData();
    }

    public int getQueryTimeout() throws SQLException {
        return baseWrapper.getQueryTimeout();
    }

    public ResultSet getResultSet() throws SQLException {
        return baseWrapper.getResultSet();
    }

    public int getResultSetConcurrency() throws SQLException {
        return baseWrapper.getResultSetConcurrency();
    }

    public int getResultSetHoldability() throws SQLException {
        return baseWrapper.getResultSetHoldability();
    }

    public int getResultSetType() throws SQLException {
        return baseWrapper.getResultSetType();
    }

    public int getUpdateCount() throws SQLException {
        return baseWrapper.getUpdateCount();
    }

    public SQLWarning getWarnings() throws SQLException {
        return baseWrapper.getWarnings();
    }

    public void setArray(int arg0, Array arg1) throws SQLException {
        baseWrapper.setArray(arg0, arg1);
    }

    public void setAsciiStream(int arg0, InputStream arg1, int arg2) throws SQLException {
        baseWrapper.setAsciiStream(arg0, arg1, arg2);
    }

    public void setBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
        baseWrapper.setBigDecimal(arg0, arg1);
    }

    public void setBinaryStream(int arg0, InputStream arg1, int arg2) throws SQLException {
        baseWrapper.setBinaryStream(arg0, arg1, arg2);
    }

    public void setBlob(int arg0, Blob arg1) throws SQLException {
        baseWrapper.setBlob(arg0, arg1);
    }

    public void setBoolean(int arg0, boolean arg1) throws SQLException {
        baseWrapper.setBoolean(arg0, arg1);
    }

    public void setByte(int arg0, byte arg1) throws SQLException {
        baseWrapper.setByte(arg0, arg1);
    }

    public void setBytes(int arg0, byte[] arg1) throws SQLException {
        baseWrapper.setBytes(arg0, arg1);
    }

    public void setCharacterStream(int arg0, Reader arg1, int arg2) throws SQLException {
        baseWrapper.setCharacterStream(arg0, arg1, arg2);
    }

    public void setClob(int arg0, Clob arg1) throws SQLException {
        baseWrapper.setClob(arg0, arg1);
    }

    public void setCursorName(String arg0) throws SQLException {
        baseWrapper.setCursorName(arg0);
    }

    public void setDate(int arg0, Date arg1) throws SQLException {
        baseWrapper.setDate(arg0, arg1);
    }

    public void setDate(int arg0, Date arg1, Calendar arg2) throws SQLException {
        baseWrapper.setDate(arg0, arg1, arg2);
    }

    public void setDouble(int arg0, double arg1) throws SQLException {
        baseWrapper.setDouble(arg0, arg1);
    }

    public void setEscapeProcessing(boolean arg0) throws SQLException {
        baseWrapper.setEscapeProcessing(arg0);
    }

    public void setFetchDirection(int arg0) throws SQLException {
        baseWrapper.setFetchDirection(arg0);
    }

    public void setFetchSize(int arg0) throws SQLException {
        baseWrapper.setFetchSize(arg0);
    }

    public void setFloat(int arg0, float arg1) throws SQLException {
        baseWrapper.setFloat(arg0, arg1);
    }

    public void setInt(int arg0, int arg1) throws SQLException {
        baseWrapper.setInt(arg0, arg1);
    }

    public void setLong(int arg0, long arg1) throws SQLException {
        baseWrapper.setLong(arg0, arg1);
    }

    public void setMaxFieldSize(int arg0) throws SQLException {
        baseWrapper.setMaxFieldSize(arg0);
    }

    public void setMaxRows(int arg0) throws SQLException {
        baseWrapper.setMaxRows(arg0);
    }

    public void setNull(int arg0, int arg1) throws SQLException {
        baseWrapper.setNull(arg0, arg1);
    }

    public void setNull(int arg0, int arg1, String arg2) throws SQLException {
        baseWrapper.setNull(arg0, arg1, arg2);
    }

    public void setObject(int arg0, Object arg1) throws SQLException {
        baseWrapper.setObject(arg0, arg1);
    }

    public void setObject(int arg0, Object arg1, int arg2) throws SQLException {
        baseWrapper.setObject(arg0, arg1, arg2);
    }

    public void setObject(int arg0, Object arg1, int arg2, int arg3) throws SQLException {
        baseWrapper.setObject(arg0, arg1, arg2, arg3);
    }

    public void setQueryTimeout(int arg0) throws SQLException {
        baseWrapper.setQueryTimeout(arg0);
    }

    public void setRef(int arg0, Ref arg1) throws SQLException {
        baseWrapper.setRef(arg0, arg1);
    }

    public void setShort(int arg0, short arg1) throws SQLException {
        baseWrapper.setShort(arg0, arg1);
    }

    public void setString(int arg0, String arg1) throws SQLException {
        baseWrapper.setString(arg0, arg1);
    }

    public void setTime(int arg0, Time arg1) throws SQLException {
        baseWrapper.setTime(arg0, arg1);
    }

    public void setTime(int arg0, Time arg1, Calendar arg2) throws SQLException {
        baseWrapper.setTime(arg0, arg1, arg2);
    }

    public void setTimestamp(int arg0, Timestamp arg1) throws SQLException {
        baseWrapper.setTimestamp(arg0, arg1);
    }

    public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2) throws SQLException {
        baseWrapper.setTimestamp(arg0, arg1, arg2);
    }

    public void setURL(int arg0, URL arg1) throws SQLException {
        baseWrapper.setURL(arg0, arg1);
    }

    public void setUnicodeStream(int arg0, InputStream arg1, int arg2) throws SQLException {
        baseWrapper.setUnicodeStream(arg0, arg1, arg2);
    }

    public Array getArray(int i) throws SQLException {
        return parent.getArray(i);
    }

    public Array getArray(String arg0) throws SQLException {
        return parent.getArray(arg0);
    }

    public BigDecimal getBigDecimal(int arg0, int scale)
            throws SQLException {
        return parent.getBigDecimal(arg0, scale);
    }

    public BigDecimal getBigDecimal(int arg0) throws SQLException {
        return parent.getBigDecimal(arg0);
    }

    public BigDecimal getBigDecimal(String arg0) throws SQLException {
        return parent.getBigDecimal(arg0);
    }

    public Blob getBlob(int i) throws SQLException {
        return parent.getBlob(i);
    }

    public Blob getBlob(String arg0) throws SQLException {
        return parent.getBlob(arg0);
    }

    public boolean getBoolean(int arg0) throws SQLException {
        return parent.getBoolean(arg0);
    }

    public boolean getBoolean(String arg0) throws SQLException {
        return parent.getBoolean(arg0);
    }

    public byte getByte(int arg0) throws SQLException {
        return parent.getByte(arg0);
    }

    public byte getByte(String arg0) throws SQLException {
        return parent.getByte(arg0);
    }

    public byte[] getBytes(int arg0) throws SQLException {
        return parent.getBytes(arg0);
    }

    public byte[] getBytes(String arg0) throws SQLException {
        return parent.getBytes(arg0);
    }

    public Clob getClob(int i) throws SQLException {
        return parent.getClob(i);
    }

    public Clob getClob(String arg0) throws SQLException {
        return parent.getClob(arg0);
    }

    public Date getDate(int arg0, Calendar cal) throws SQLException {
        return parent.getDate(arg0, cal);
    }

    public Date getDate(int arg0) throws SQLException {
        return parent.getDate(arg0);
    }

    public Date getDate(String arg0, Calendar cal) throws SQLException {
        return parent.getDate(arg0, cal);
    }

    public Date getDate(String arg0) throws SQLException {
        return parent.getDate(arg0);
    }

    public double getDouble(int arg0) throws SQLException {
        return parent.getDouble(arg0);
    }

    public double getDouble(String arg0) throws SQLException {
        return parent.getDouble(arg0);
    }

    public float getFloat(int arg0) throws SQLException {
        return parent.getFloat(arg0);
    }

    public float getFloat(String arg0) throws SQLException {
        return parent.getFloat(arg0);
    }

    public int getInt(int arg0) throws SQLException {
        return parent.getInt(arg0);
    }

    public int getInt(String arg0) throws SQLException {
        return parent.getInt(arg0);
    }

    public long getLong(int arg0) throws SQLException {
        return parent.getLong(arg0);
    }

    public long getLong(String arg0) throws SQLException {
        return parent.getLong(arg0);
    }

    public Object getObject(int i, Map<String, Class<?>> map)
            throws SQLException {
        return parent.getObject(i, map);
    }

    public Object getObject(int arg0) throws SQLException {
        return parent.getObject(arg0);
    }

    public Object getObject(String arg0, Map<String, Class<?>> map)
            throws SQLException {
        return parent.getObject(arg0, map);
    }

    public Object getObject(String arg0) throws SQLException {
        return parent.getObject(arg0);
    }

    public Ref getRef(int i) throws SQLException {
        return parent.getRef(i);
    }

    public Ref getRef(String arg0) throws SQLException {
        return parent.getRef(arg0);
    }

    public short getShort(int arg0) throws SQLException {
        return parent.getShort(arg0);
    }

    public short getShort(String arg0) throws SQLException {
        return parent.getShort(arg0);
    }

    public String getString(int arg0) throws SQLException {
        return parent.getString(arg0);
    }

    public String getString(String arg0) throws SQLException {
        return parent.getString(arg0);
    }

    public Time getTime(int arg0, Calendar cal) throws SQLException {
        return parent.getTime(arg0, cal);
    }

    public Time getTime(int arg0) throws SQLException {
        return parent.getTime(arg0);
    }

    public Time getTime(String arg0, Calendar cal) throws SQLException {
        return parent.getTime(arg0, cal);
    }

    public Time getTime(String arg0) throws SQLException {
        return parent.getTime(arg0);
    }

    public Timestamp getTimestamp(int arg0, Calendar cal)
            throws SQLException {
        return parent.getTimestamp(arg0, cal);
    }

    public Timestamp getTimestamp(int arg0) throws SQLException {
        return parent.getTimestamp(arg0);
    }

    public Timestamp getTimestamp(String arg0, Calendar cal)
            throws SQLException {
        return parent.getTimestamp(arg0, cal);
    }

    public Timestamp getTimestamp(String arg0) throws SQLException {
        return parent.getTimestamp(arg0);
    }

    public URL getURL(int arg0) throws SQLException {
        return parent.getURL(arg0);
    }

    public URL getURL(String arg0) throws SQLException {
        return parent.getURL(arg0);
    }

    public void registerOutParameter(int arg0, int sqlType, int scale)
            throws SQLException {
        parent.registerOutParameter(arg0, sqlType, scale);
    }

    public void registerOutParameter(int paramIndex, int sqlType,
            String typeName) throws SQLException {
        parent.registerOutParameter(paramIndex, sqlType, typeName);
    }

    public void registerOutParameter(int arg0, int sqlType)
            throws SQLException {
        parent.registerOutParameter(arg0, sqlType);
    }

    public void registerOutParameter(String arg0, int sqlType,
            int scale) throws SQLException {
        parent.registerOutParameter(arg0, sqlType, scale);
    }

    public void registerOutParameter(String arg0, int sqlType,
            String typeName) throws SQLException {
        parent.registerOutParameter(arg0, sqlType, typeName);
    }

    public void registerOutParameter(String arg0, int sqlType)
            throws SQLException {
        parent.registerOutParameter(arg0, sqlType);
    }

    public void setAsciiStream(String arg0, InputStream x, int length)
            throws SQLException {
        parent.setAsciiStream(arg0, x, length);
    }

    public void setBigDecimal(String arg0, BigDecimal x)
            throws SQLException {
        parent.setBigDecimal(arg0, x);
    }

    public void setBinaryStream(String arg0, InputStream x, int length)
            throws SQLException {
        parent.setBinaryStream(arg0, x, length);
    }

    public void setBoolean(String arg0, boolean x) throws SQLException {
        parent.setBoolean(arg0, x);
    }

    public void setByte(String arg0, byte x) throws SQLException {
        parent.setByte(arg0, x);
    }

    public void setBytes(String arg0, byte[] x) throws SQLException {
        parent.setBytes(arg0, x);
    }

    public void setCharacterStream(String arg0, Reader reader,
            int length) throws SQLException {
        parent.setCharacterStream(arg0, reader, length);
    }

    public void setDate(String arg0, Date x, Calendar cal)
            throws SQLException {
        parent.setDate(arg0, x, cal);
    }

    public void setDate(String arg0, Date x) throws SQLException {
        parent.setDate(arg0, x);
    }

    public void setDouble(String arg0, double x) throws SQLException {
        parent.setDouble(arg0, x);
    }

    public void setFloat(String arg0, float x) throws SQLException {
        parent.setFloat(arg0, x);
    }

    public void setInt(String arg0, int x) throws SQLException {
        parent.setInt(arg0, x);
    }

    public void setLong(String arg0, long x) throws SQLException {
        parent.setLong(arg0, x);
    }

    public void setNull(String arg0, int sqlType, String typeName)
            throws SQLException {
        parent.setNull(arg0, sqlType, typeName);
    }

    public void setNull(String arg0, int sqlType) throws SQLException {
        parent.setNull(arg0, sqlType);
    }

    public void setObject(String arg0, Object x, int targetSqlType,
            int scale) throws SQLException {
        parent.setObject(arg0, x, targetSqlType, scale);
    }

    public void setObject(String arg0, Object x, int targetSqlType)
            throws SQLException {
        parent.setObject(arg0, x, targetSqlType);
    }

    public void setObject(String arg0, Object x) throws SQLException {
        parent.setObject(arg0, x);
    }

    public void setShort(String arg0, short x) throws SQLException {
        parent.setShort(arg0, x);
    }

    public void setString(String arg0, String x) throws SQLException {
        parent.setString(arg0, x);
    }

    public void setTime(String arg0, Time x, Calendar cal)
            throws SQLException {
        parent.setTime(arg0, x, cal);
    }

    public void setTime(String arg0, Time x) throws SQLException {
        parent.setTime(arg0, x);
    }

    public void setTimestamp(String arg0, Timestamp x, Calendar cal)
            throws SQLException {
        parent.setTimestamp(arg0, x, cal);
    }

    public void setTimestamp(String arg0, Timestamp x)
            throws SQLException {
        parent.setTimestamp(arg0, x);
    }

    public void setURL(String arg0, URL val) throws SQLException {
        parent.setURL(arg0, val);
    }

    public boolean wasNull() throws SQLException {
        return parent.wasNull();
    }
    
}
