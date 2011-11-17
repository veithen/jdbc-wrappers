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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import net.sf.jdbcwrappers.trim.TrimmingWrapperFactory;

import org.apache.commons.io.FileUtils;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.derby.tools.ij;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrimmingTest {
    private static EmbeddedDataSource rawDataSource;
    private static DataSource dataSource;
    
    private Connection connection;
    
    @BeforeClass
    public static void createDataSource() throws Exception {
        FileUtils.deleteDirectory(new File("target/testDB"));
        rawDataSource = new EmbeddedDataSource();
        rawDataSource.setDatabaseName("target/testDB");
        rawDataSource.setUser("test");
        rawDataSource.setCreateDatabase("create");
        Connection connection = rawDataSource.getConnection();
        try {
            if (ij.runScript(connection, TrimmingTest.class.getResourceAsStream("schema.sql"), "UTF-8", System.out, "UTF-8") > 0) {
                fail("Failed to initialize database");
            }
        } finally {
            connection.close();
        }
        dataSource = new TrimmingWrapperFactory().wrapDataSource(rawDataSource);
    }
    
    @AfterClass
    public static void destroyDataSource() throws Exception {
        rawDataSource.setShutdownDatabase("shutdown");
        try {
            rawDataSource.getConnection();
        } catch (SQLException ex) {
            // This always throws an exception; just continue
        }
        FileUtils.deleteDirectory(new File("target/testDB"));
    }
    
    @Before
    public void createConnection() throws SQLException {
        connection = dataSource.getConnection();
    }
    
    @After
    public void closeConnection() throws SQLException {
        connection.close();
    }
    
    @Test
    public void testStatement() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM TEST");
            rs.next();
            assertEquals("test", rs.getString(2));
            assertEquals("test", rs.getString("CHAR_COL"));
            assertEquals("test", rs.getObject(2));
            assertEquals("test", rs.getObject("CHAR_COL"));
            assertEquals("test2", rs.getString(4));
            assertEquals("test2", rs.getString("CHAR_COL2"));
            assertEquals("test2", rs.getObject(4));
            assertEquals("test2", rs.getObject("CHAR_COL2"));
        }
        finally {
            statement.close();
        }
    }
    
    @Test
    public void testPreparedStatement() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM TEST WHERE INT_COL=?");
        try {
            statement.setInt(1, 12);
            ResultSet rs = statement.executeQuery();
            rs.next();
            assertEquals("test", rs.getString(2));
            assertEquals("test", rs.getString("CHAR_COL"));
        }
        finally {
            statement.close();
        }
    }
    
    public static void testProc(ResultSet[] resultSet) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:default:connection");
        resultSet[0] = connection.createStatement().executeQuery("SELECT * FROM TEST");
    }
    
    @Test
    public void testCallableStatement() throws SQLException {
        CallableStatement statement = connection.prepareCall("{ call TESTPROC() }");
        try {
            ResultSet rs = statement.executeQuery();
            rs.next();
            assertEquals("test", rs.getString(2));
            assertEquals("test", rs.getString("CHAR_COL"));
        }
        finally {
            statement.close();
        }
    }
}
