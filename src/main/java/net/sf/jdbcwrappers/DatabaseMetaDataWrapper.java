/**
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
package net.sf.jdbcwrappers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Base class for {@link DatabaseMetaData} wrappers.
 * Except for {@link #getConnection()}, all methods delegate to the target {@link DatabaseMetaData} object.
 * Note that {@link ResultSet} objects returned by methods in this class are not wrapped.
 * 
 * @author Andreas Veithen
 * @version $Id: DatabaseMetaDataWrapper.java 52 2008-02-10 13:42:19Z veithen $
 */
public class DatabaseMetaDataWrapper implements DatabaseMetaData {
	private ConnectionWrapper connectionWrapper;
	private DatabaseMetaData parent;
	
	final void init(ConnectionWrapper connectionWrapper, DatabaseMetaData parent) {
		this.connectionWrapper = connectionWrapper;
		this.parent = parent;
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#allProceduresAreCallable()}.
	 */
	public boolean allProceduresAreCallable() throws SQLException {
		return parent.allProceduresAreCallable();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#allTablesAreSelectable()}.
	 */
	public boolean allTablesAreSelectable() throws SQLException {
		return parent.allTablesAreSelectable();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#dataDefinitionCausesTransactionCommit()}.
	 */
	public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
		return parent.dataDefinitionCausesTransactionCommit();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#dataDefinitionIgnoredInTransactions()}.
	 */
	public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
		return parent.dataDefinitionIgnoredInTransactions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#deletesAreDetected(int)}.
	 */
	public boolean deletesAreDetected(int type) throws SQLException {
		return parent.deletesAreDetected(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#doesMaxRowSizeIncludeBlobs()}.
	 */
	public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
		return parent.doesMaxRowSizeIncludeBlobs();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getAttributes(String, String, String, String)}.
	 */
	public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
		return parent.getAttributes(catalog, schemaPattern, typeNamePattern, attributeNamePattern);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getBestRowIdentifier(String, String, String, int, boolean)}.
	 */
	public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
		return parent.getBestRowIdentifier(catalog, schema, table, scope, nullable);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getCatalogs()}.
	 */
	public ResultSet getCatalogs() throws SQLException {
		return parent.getCatalogs();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getCatalogSeparator()}.
	 */
	public String getCatalogSeparator() throws SQLException {
		return parent.getCatalogSeparator();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getCatalogTerm()}.
	 */
	public String getCatalogTerm() throws SQLException {
		return parent.getCatalogTerm();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getColumnPrivileges(String, String, String, String)}.
	 */
	public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
		return parent.getColumnPrivileges(catalog, schema, table, columnNamePattern);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getColumns(String, String, String, String)}.
	 */
	public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
		return parent.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getConnection()}.
	 * This method returns the {@link ConnectionWrapper} object that
	 * created this wrapper. For consistency reasons, it can't be
	 * overridden.
	 */
	public final Connection getConnection() throws SQLException {
		return connectionWrapper;
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getCrossReference(String, String, String, String, String, String)}.
	 */
	public ResultSet getCrossReference(String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
		return parent.getCrossReference(primaryCatalog, primarySchema, primaryTable, foreignCatalog, foreignSchema, foreignTable);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDatabaseMajorVersion()}.
	 */
	public int getDatabaseMajorVersion() throws SQLException {
		return parent.getDatabaseMajorVersion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDatabaseMinorVersion()}.
	 */
	public int getDatabaseMinorVersion() throws SQLException {
		return parent.getDatabaseMinorVersion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDatabaseProductName()}.
	 */
	public String getDatabaseProductName() throws SQLException {
		return parent.getDatabaseProductName();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDatabaseProductVersion()}.
	 */
	public String getDatabaseProductVersion() throws SQLException {
		return parent.getDatabaseProductVersion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDefaultTransactionIsolation()}.
	 */
	public int getDefaultTransactionIsolation() throws SQLException {
		return parent.getDefaultTransactionIsolation();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDriverMajorVersion()}.
	 */
	public int getDriverMajorVersion() {
		return parent.getDriverMajorVersion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDriverMinorVersion()}.
	 */
	public int getDriverMinorVersion() {
		return parent.getDriverMinorVersion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDriverName()}.
	 */
	public String getDriverName() throws SQLException {
		return parent.getDriverName();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getDriverVersion()}.
	 */
	public String getDriverVersion() throws SQLException {
		return parent.getDriverVersion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getExportedKeys(String, String, String)}.
	 */
	public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
		return parent.getExportedKeys(catalog, schema, table);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getExtraNameCharacters()}.
	 */
	public String getExtraNameCharacters() throws SQLException {
		return parent.getExtraNameCharacters();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getIdentifierQuoteString()}.
	 */
	public String getIdentifierQuoteString() throws SQLException {
		return parent.getIdentifierQuoteString();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getImportedKeys(String, String, String)}.
	 */
	public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
		return parent.getImportedKeys(catalog, schema, table);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getIndexInfo(String, String, String, boolean, boolean)}.
	 */
	public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
		return parent.getIndexInfo(catalog, schema, table, unique, approximate);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getJDBCMajorVersion()}.
	 */
	public int getJDBCMajorVersion() throws SQLException {
		return parent.getJDBCMajorVersion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getJDBCMinorVersion()}.
	 */
	public int getJDBCMinorVersion() throws SQLException {
		return parent.getJDBCMinorVersion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxBinaryLiteralLength()}.
	 */
	public int getMaxBinaryLiteralLength() throws SQLException {
		return parent.getMaxBinaryLiteralLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxCatalogNameLength()}.
	 */
	public int getMaxCatalogNameLength() throws SQLException {
		return parent.getMaxCatalogNameLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxCharLiteralLength()}.
	 */
	public int getMaxCharLiteralLength() throws SQLException {
		return parent.getMaxCharLiteralLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxColumnNameLength()}.
	 */
	public int getMaxColumnNameLength() throws SQLException {
		return parent.getMaxColumnNameLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxColumnsInGroupBy()}.
	 */
	public int getMaxColumnsInGroupBy() throws SQLException {
		return parent.getMaxColumnsInGroupBy();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxColumnsInIndex()}.
	 */
	public int getMaxColumnsInIndex() throws SQLException {
		return parent.getMaxColumnsInIndex();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxColumnsInOrderBy()}.
	 */
	public int getMaxColumnsInOrderBy() throws SQLException {
		return parent.getMaxColumnsInOrderBy();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxColumnsInSelect()}.
	 */
	public int getMaxColumnsInSelect() throws SQLException {
		return parent.getMaxColumnsInSelect();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxColumnsInTable()}.
	 */
	public int getMaxColumnsInTable() throws SQLException {
		return parent.getMaxColumnsInTable();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxConnections()}.
	 */
	public int getMaxConnections() throws SQLException {
		return parent.getMaxConnections();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxCursorNameLength()}.
	 */
	public int getMaxCursorNameLength() throws SQLException {
		return parent.getMaxCursorNameLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxIndexLength()}.
	 */
	public int getMaxIndexLength() throws SQLException {
		return parent.getMaxIndexLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxProcedureNameLength()}.
	 */
	public int getMaxProcedureNameLength() throws SQLException {
		return parent.getMaxProcedureNameLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxRowSize()}.
	 */
	public int getMaxRowSize() throws SQLException {
		return parent.getMaxRowSize();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxSchemaNameLength()}.
	 */
	public int getMaxSchemaNameLength() throws SQLException {
		return parent.getMaxSchemaNameLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxStatementLength()}.
	 */
	public int getMaxStatementLength() throws SQLException {
		return parent.getMaxStatementLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxStatements()}.
	 */
	public int getMaxStatements() throws SQLException {
		return parent.getMaxStatements();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxTableNameLength()}.
	 */
	public int getMaxTableNameLength() throws SQLException {
		return parent.getMaxTableNameLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxTablesInSelect()}.
	 */
	public int getMaxTablesInSelect() throws SQLException {
		return parent.getMaxTablesInSelect();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getMaxUserNameLength()}.
	 */
	public int getMaxUserNameLength() throws SQLException {
		return parent.getMaxUserNameLength();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getNumericFunctions()}.
	 */
	public String getNumericFunctions() throws SQLException {
		return parent.getNumericFunctions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getPrimaryKeys(String, String, String)}.
	 */
	public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
		return parent.getPrimaryKeys(catalog, schema, table);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getProcedureColumns(String, String, String, String)}.
	 */
	public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
		return parent.getProcedureColumns(catalog, schemaPattern, procedureNamePattern, columnNamePattern);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getProcedures(String, String, String)}.
	 */
	public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
		return parent.getProcedures(catalog, schemaPattern, procedureNamePattern);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getProcedureTerm()}.
	 */
	public String getProcedureTerm() throws SQLException {
		return parent.getProcedureTerm();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getResultSetHoldability()}.
	 */
	public int getResultSetHoldability() throws SQLException {
		return parent.getResultSetHoldability();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getSchemas()}.
	 */
	public ResultSet getSchemas() throws SQLException {
		return parent.getSchemas();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getSchemaTerm()}.
	 */
	public String getSchemaTerm() throws SQLException {
		return parent.getSchemaTerm();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getSearchStringEscape()}.
	 */
	public String getSearchStringEscape() throws SQLException {
		return parent.getSearchStringEscape();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getSQLKeywords()}.
	 */
	public String getSQLKeywords() throws SQLException {
		return parent.getSQLKeywords();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getSQLStateType()}.
	 */
	public int getSQLStateType() throws SQLException {
		return parent.getSQLStateType();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getStringFunctions()}.
	 */
	public String getStringFunctions() throws SQLException {
		return parent.getStringFunctions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getSuperTables(String, String, String)}.
	 */
	public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
		return parent.getSuperTables(catalog, schemaPattern, tableNamePattern);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getSuperTypes(String, String, String)}.
	 */
	public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
		return parent.getSuperTypes(catalog, schemaPattern, typeNamePattern);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getSystemFunctions()}.
	 */
	public String getSystemFunctions() throws SQLException {
		return parent.getSystemFunctions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getTablePrivileges(String, String, String)}.
	 */
	public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
		return parent.getTablePrivileges(catalog, schemaPattern, tableNamePattern);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getTables(String, String, String, String[])}.
	 */
	public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
		return parent.getTables(catalog, schemaPattern, tableNamePattern, types);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getTableTypes()}.
	 */
	public ResultSet getTableTypes() throws SQLException {
		return parent.getTableTypes();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getTimeDateFunctions()}.
	 */
	public String getTimeDateFunctions() throws SQLException {
		return parent.getTimeDateFunctions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getTypeInfo()}.
	 */
	public ResultSet getTypeInfo() throws SQLException {
		return parent.getTypeInfo();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getUDTs(String, String, String, int[])}.
	 */
	public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
		return parent.getUDTs(catalog, schemaPattern, typeNamePattern, types);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getURL()}.
	 */
	public String getURL() throws SQLException {
		return parent.getURL();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getUserName()}.
	 */
	public String getUserName() throws SQLException {
		return parent.getUserName();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#getVersionColumns(String, String, String)}.
	 */
	public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
		return parent.getVersionColumns(catalog, schema, table);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#insertsAreDetected(int)}.
	 */
	public boolean insertsAreDetected(int type) throws SQLException {
		return parent.insertsAreDetected(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#isCatalogAtStart()}.
	 */
	public boolean isCatalogAtStart() throws SQLException {
		return parent.isCatalogAtStart();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#isReadOnly()}.
	 */
	public boolean isReadOnly() throws SQLException {
		return parent.isReadOnly();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#locatorsUpdateCopy()}.
	 */
	public boolean locatorsUpdateCopy() throws SQLException {
		return parent.locatorsUpdateCopy();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#nullPlusNonNullIsNull()}.
	 */
	public boolean nullPlusNonNullIsNull() throws SQLException {
		return parent.nullPlusNonNullIsNull();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#nullsAreSortedAtEnd()}.
	 */
	public boolean nullsAreSortedAtEnd() throws SQLException {
		return parent.nullsAreSortedAtEnd();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#nullsAreSortedAtStart()}.
	 */
	public boolean nullsAreSortedAtStart() throws SQLException {
		return parent.nullsAreSortedAtStart();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#nullsAreSortedHigh()}.
	 */
	public boolean nullsAreSortedHigh() throws SQLException {
		return parent.nullsAreSortedHigh();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#nullsAreSortedLow()}.
	 */
	public boolean nullsAreSortedLow() throws SQLException {
		return parent.nullsAreSortedLow();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#othersDeletesAreVisible(int)}.
	 */
	public boolean othersDeletesAreVisible(int type) throws SQLException {
		return parent.othersDeletesAreVisible(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#othersInsertsAreVisible(int)}.
	 */
	public boolean othersInsertsAreVisible(int type) throws SQLException {
		return parent.othersInsertsAreVisible(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#othersUpdatesAreVisible(int)}.
	 */
	public boolean othersUpdatesAreVisible(int type) throws SQLException {
		return parent.othersUpdatesAreVisible(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#ownDeletesAreVisible(int)}.
	 */
	public boolean ownDeletesAreVisible(int type) throws SQLException {
		return parent.ownDeletesAreVisible(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#ownInsertsAreVisible(int)}.
	 */
	public boolean ownInsertsAreVisible(int type) throws SQLException {
		return parent.ownInsertsAreVisible(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#ownUpdatesAreVisible(int)}.
	 */
	public boolean ownUpdatesAreVisible(int type) throws SQLException {
		return parent.ownUpdatesAreVisible(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#storesLowerCaseIdentifiers()}.
	 */
	public boolean storesLowerCaseIdentifiers() throws SQLException {
		return parent.storesLowerCaseIdentifiers();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#storesLowerCaseQuotedIdentifiers()}.
	 */
	public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
		return parent.storesLowerCaseQuotedIdentifiers();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#storesMixedCaseIdentifiers()}.
	 */
	public boolean storesMixedCaseIdentifiers() throws SQLException {
		return parent.storesMixedCaseIdentifiers();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#storesMixedCaseQuotedIdentifiers()}.
	 */
	public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
		return parent.storesMixedCaseQuotedIdentifiers();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#storesUpperCaseIdentifiers()}.
	 */
	public boolean storesUpperCaseIdentifiers() throws SQLException {
		return parent.storesUpperCaseIdentifiers();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#storesUpperCaseQuotedIdentifiers()}.
	 */
	public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
		return parent.storesUpperCaseQuotedIdentifiers();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsAlterTableWithAddColumn()}.
	 */
	public boolean supportsAlterTableWithAddColumn() throws SQLException {
		return parent.supportsAlterTableWithAddColumn();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsAlterTableWithDropColumn()}.
	 */
	public boolean supportsAlterTableWithDropColumn() throws SQLException {
		return parent.supportsAlterTableWithDropColumn();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsANSI92EntryLevelSQL()}.
	 */
	public boolean supportsANSI92EntryLevelSQL() throws SQLException {
		return parent.supportsANSI92EntryLevelSQL();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsANSI92FullSQL()}.
	 */
	public boolean supportsANSI92FullSQL() throws SQLException {
		return parent.supportsANSI92FullSQL();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsANSI92IntermediateSQL()}.
	 */
	public boolean supportsANSI92IntermediateSQL() throws SQLException {
		return parent.supportsANSI92IntermediateSQL();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsBatchUpdates()}.
	 */
	public boolean supportsBatchUpdates() throws SQLException {
		return parent.supportsBatchUpdates();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsCatalogsInDataManipulation()}.
	 */
	public boolean supportsCatalogsInDataManipulation() throws SQLException {
		return parent.supportsCatalogsInDataManipulation();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsCatalogsInIndexDefinitions()}.
	 */
	public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
		return parent.supportsCatalogsInIndexDefinitions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsCatalogsInPrivilegeDefinitions()}.
	 */
	public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
		return parent.supportsCatalogsInPrivilegeDefinitions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsCatalogsInProcedureCalls()}.
	 */
	public boolean supportsCatalogsInProcedureCalls() throws SQLException {
		return parent.supportsCatalogsInProcedureCalls();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsCatalogsInTableDefinitions()}.
	 */
	public boolean supportsCatalogsInTableDefinitions() throws SQLException {
		return parent.supportsCatalogsInTableDefinitions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsColumnAliasing()}.
	 */
	public boolean supportsColumnAliasing() throws SQLException {
		return parent.supportsColumnAliasing();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsConvert()}.
	 */
	public boolean supportsConvert() throws SQLException {
		return parent.supportsConvert();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsConvert(int, int)}.
	 */
	public boolean supportsConvert(int fromType, int toType) throws SQLException {
		return parent.supportsConvert(fromType, toType);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsCoreSQLGrammar()}.
	 */
	public boolean supportsCoreSQLGrammar() throws SQLException {
		return parent.supportsCoreSQLGrammar();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsCorrelatedSubqueries()}.
	 */
	public boolean supportsCorrelatedSubqueries() throws SQLException {
		return parent.supportsCorrelatedSubqueries();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsDataDefinitionAndDataManipulationTransactions()}.
	 */
	public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
		return parent.supportsDataDefinitionAndDataManipulationTransactions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsDataManipulationTransactionsOnly()}.
	 */
	public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
		return parent.supportsDataManipulationTransactionsOnly();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsDifferentTableCorrelationNames()}.
	 */
	public boolean supportsDifferentTableCorrelationNames() throws SQLException {
		return parent.supportsDifferentTableCorrelationNames();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsExpressionsInOrderBy()}.
	 */
	public boolean supportsExpressionsInOrderBy() throws SQLException {
		return parent.supportsExpressionsInOrderBy();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsExtendedSQLGrammar()}.
	 */
	public boolean supportsExtendedSQLGrammar() throws SQLException {
		return parent.supportsExtendedSQLGrammar();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsFullOuterJoins()}.
	 */
	public boolean supportsFullOuterJoins() throws SQLException {
		return parent.supportsFullOuterJoins();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsGetGeneratedKeys()}.
	 */
	public boolean supportsGetGeneratedKeys() throws SQLException {
		return parent.supportsGetGeneratedKeys();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsGroupBy()}.
	 */
	public boolean supportsGroupBy() throws SQLException {
		return parent.supportsGroupBy();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsGroupByBeyondSelect()}.
	 */
	public boolean supportsGroupByBeyondSelect() throws SQLException {
		return parent.supportsGroupByBeyondSelect();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsGroupByUnrelated()}.
	 */
	public boolean supportsGroupByUnrelated() throws SQLException {
		return parent.supportsGroupByUnrelated();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsIntegrityEnhancementFacility()}.
	 */
	public boolean supportsIntegrityEnhancementFacility() throws SQLException {
		return parent.supportsIntegrityEnhancementFacility();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsLikeEscapeClause()}.
	 */
	public boolean supportsLikeEscapeClause() throws SQLException {
		return parent.supportsLikeEscapeClause();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsLimitedOuterJoins()}.
	 */
	public boolean supportsLimitedOuterJoins() throws SQLException {
		return parent.supportsLimitedOuterJoins();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsMinimumSQLGrammar()}.
	 */
	public boolean supportsMinimumSQLGrammar() throws SQLException {
		return parent.supportsMinimumSQLGrammar();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsMixedCaseIdentifiers()}.
	 */
	public boolean supportsMixedCaseIdentifiers() throws SQLException {
		return parent.supportsMixedCaseIdentifiers();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsMixedCaseQuotedIdentifiers()}.
	 */
	public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
		return parent.supportsMixedCaseQuotedIdentifiers();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsMultipleOpenResults()}.
	 */
	public boolean supportsMultipleOpenResults() throws SQLException {
		return parent.supportsMultipleOpenResults();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsMultipleResultSets()}.
	 */
	public boolean supportsMultipleResultSets() throws SQLException {
		return parent.supportsMultipleResultSets();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsMultipleTransactions()}.
	 */
	public boolean supportsMultipleTransactions() throws SQLException {
		return parent.supportsMultipleTransactions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsNamedParameters()}.
	 */
	public boolean supportsNamedParameters() throws SQLException {
		return parent.supportsNamedParameters();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsNonNullableColumns()}.
	 */
	public boolean supportsNonNullableColumns() throws SQLException {
		return parent.supportsNonNullableColumns();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsOpenCursorsAcrossCommit()}.
	 */
	public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
		return parent.supportsOpenCursorsAcrossCommit();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsOpenCursorsAcrossRollback()}.
	 */
	public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
		return parent.supportsOpenCursorsAcrossRollback();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsOpenStatementsAcrossCommit()}.
	 */
	public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
		return parent.supportsOpenStatementsAcrossCommit();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsOpenStatementsAcrossRollback()}.
	 */
	public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
		return parent.supportsOpenStatementsAcrossRollback();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsOrderByUnrelated()}.
	 */
	public boolean supportsOrderByUnrelated() throws SQLException {
		return parent.supportsOrderByUnrelated();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsOuterJoins()}.
	 */
	public boolean supportsOuterJoins() throws SQLException {
		return parent.supportsOuterJoins();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsPositionedDelete()}.
	 */
	public boolean supportsPositionedDelete() throws SQLException {
		return parent.supportsPositionedDelete();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsPositionedUpdate()}.
	 */
	public boolean supportsPositionedUpdate() throws SQLException {
		return parent.supportsPositionedUpdate();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsResultSetConcurrency(int, int)}.
	 */
	public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
		return parent.supportsResultSetConcurrency(type, concurrency);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsResultSetHoldability(int)}.
	 */
	public boolean supportsResultSetHoldability(int holdability) throws SQLException {
		return parent.supportsResultSetHoldability(holdability);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsResultSetType(int)}.
	 */
	public boolean supportsResultSetType(int type) throws SQLException {
		return parent.supportsResultSetType(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSavepoints()}.
	 */
	public boolean supportsSavepoints() throws SQLException {
		return parent.supportsSavepoints();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSchemasInDataManipulation()}.
	 */
	public boolean supportsSchemasInDataManipulation() throws SQLException {
		return parent.supportsSchemasInDataManipulation();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSchemasInIndexDefinitions()}.
	 */
	public boolean supportsSchemasInIndexDefinitions() throws SQLException {
		return parent.supportsSchemasInIndexDefinitions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSchemasInPrivilegeDefinitions()}.
	 */
	public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
		return parent.supportsSchemasInPrivilegeDefinitions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSchemasInProcedureCalls()}.
	 */
	public boolean supportsSchemasInProcedureCalls() throws SQLException {
		return parent.supportsSchemasInProcedureCalls();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSchemasInTableDefinitions()}.
	 */
	public boolean supportsSchemasInTableDefinitions() throws SQLException {
		return parent.supportsSchemasInTableDefinitions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSelectForUpdate()}.
	 */
	public boolean supportsSelectForUpdate() throws SQLException {
		return parent.supportsSelectForUpdate();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsStatementPooling()}.
	 */
	public boolean supportsStatementPooling() throws SQLException {
		return parent.supportsStatementPooling();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsStoredProcedures()}.
	 */
	public boolean supportsStoredProcedures() throws SQLException {
		return parent.supportsStoredProcedures();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSubqueriesInComparisons()}.
	 */
	public boolean supportsSubqueriesInComparisons() throws SQLException {
		return parent.supportsSubqueriesInComparisons();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSubqueriesInExists()}.
	 */
	public boolean supportsSubqueriesInExists() throws SQLException {
		return parent.supportsSubqueriesInExists();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSubqueriesInIns()}.
	 */
	public boolean supportsSubqueriesInIns() throws SQLException {
		return parent.supportsSubqueriesInIns();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsSubqueriesInQuantifieds()}.
	 */
	public boolean supportsSubqueriesInQuantifieds() throws SQLException {
		return parent.supportsSubqueriesInQuantifieds();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsTableCorrelationNames()}.
	 */
	public boolean supportsTableCorrelationNames() throws SQLException {
		return parent.supportsTableCorrelationNames();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsTransactionIsolationLevel(int)}.
	 */
	public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
		return parent.supportsTransactionIsolationLevel(level);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsTransactions()}.
	 */
	public boolean supportsTransactions() throws SQLException {
		return parent.supportsTransactions();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsUnion()}.
	 */
	public boolean supportsUnion() throws SQLException {
		return parent.supportsUnion();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#supportsUnionAll()}.
	 */
	public boolean supportsUnionAll() throws SQLException {
		return parent.supportsUnionAll();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#updatesAreDetected(int)}.
	 */
	public boolean updatesAreDetected(int type) throws SQLException {
		return parent.updatesAreDetected(type);
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#usesLocalFilePerTable()}.
	 */
	public boolean usesLocalFilePerTable() throws SQLException {
		return parent.usesLocalFilePerTable();
	}
	
	/**
	 * Delegate method for {@link DatabaseMetaData#usesLocalFiles()}.
	 */
	public boolean usesLocalFiles() throws SQLException {
		return parent.usesLocalFiles();
	}
}
