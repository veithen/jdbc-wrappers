/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package net.sf.jdbcwrappers.trim;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import net.sf.jdbcwrappers.ResultSetWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * {@link java.sql.ResultSet} wrapper that automatically trims strings retrieved
 * from <tt>CHAR</tt> columns.
 * Note that trimming is strictly limited to <tt>CHAR</tt> columns; values
 * retrieved from <tt>VARCHAR</tt> columns remain unchanged.
 * The wrapper relies on {@link ResultSetMetaData} to determine the column
 * type.
 * 
 * @author Andreas Veithen
 * @version $Id:TrimmingResultSetWrapper.java 24 2008-06-21 15:08:14Z veithen $
 */
public class TrimmingResultSetWrapper extends ResultSetWrapper {
    private final static Log log = LogFactory.getLog(TrimmingResultSetWrapper.class);
    
    private Set<String> charColumns;
    private boolean[] isCharColumn;
    
    private void fetchCharColumns() throws SQLException {
        if (charColumns == null) {
            ResultSetMetaData metadata = getMetaData();
            int columnCount = metadata.getColumnCount();
            charColumns = new HashSet<String>();
            isCharColumn = new boolean[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                if (metadata.getColumnType(i) == Types.CHAR) {
                    charColumns.add(metadata.getColumnName(i).toUpperCase());
                    isCharColumn[i-1] = true;
                }
            }
            if (log.isDebugEnabled()) {
                log.debug("CHAR columns: " + charColumns);
            }
        }
    }
    
    private boolean isCharColumn(int columnIndex) throws SQLException {
        fetchCharColumns();
        return isCharColumn[columnIndex-1];
    }
    
    private boolean isCharColumn(String columnName) throws SQLException {
        fetchCharColumns();
        return charColumns.contains(columnName.toUpperCase());
    }
    
    private String trim(String string) {
        int length = string.length();
        int trimmedLength = length;
        while (trimmedLength > 0 && string.charAt(trimmedLength-1) == ' ') {
            trimmedLength--;
        }
        return trimmedLength == length ? string : string.substring(0, trimmedLength);
    }
    
    /**
     * Get the value of the designated column as a Java object.
     * If the column is of type <tt>CHAR</tt>, the returned object is
     * a {@link String} holding the column value without trailing spaces.
     * 
     * @param columnIndex the column index
     * @return an {@link Object} holding the column value  
     * @exception SQLException if a database access error occurs
     */
    @Override
    public Object getObject(int columnIndex) throws SQLException {
        Object result = super.getObject(columnIndex);
        return result == null || !(result instanceof String) ? result : (isCharColumn(columnIndex) ? trim((String)result) : result);
    }

    /**
     * Get the value of the designated column as a Java object.
     * If the column is of type <tt>CHAR</tt>, the returned object is
     * a {@link String} holding the column value without trailing spaces.
     * 
     * @param columnName the column name
     * @return an {@link Object} holding the column value  
     * @exception SQLException if a database access error occurs
     */
    @Override
    public Object getObject(String columnName) throws SQLException {
        Object result = super.getObject(columnName);
        return result == null || !(result instanceof String) ? result : (isCharColumn(columnName) ? trim((String)result) : result);
    }
    
    /**
     * Get the value of the designated column as a {@link String} object.
     * If the column is of type <tt>CHAR</tt>, the trailing spaces are
     * stripped from the column value.
     * 
     * @param columnIndex the column index
     * @return the column value  
     * @exception SQLException if a database access error occurs
     */
    @Override
    public String getString(int columnIndex) throws SQLException {
        String result = super.getString(columnIndex);
        return result == null ? null : (isCharColumn(columnIndex) ? trim(result) : result);
    }

    /**
     * Get the value of the designated column as a {@link String} object.
     * If the column is of type <tt>CHAR</tt>, the trailing spaces are
     * stripped from the column value.
     * 
     * @param columnName the column name
     * @return the column value  
     * @exception SQLException if a database access error occurs
     */
    @Override
    public String getString(String columnName) throws SQLException {
        String result = super.getString(columnName);
        return result == null ? null : (isCharColumn(columnName) ? trim(result) : result);
    }
}
