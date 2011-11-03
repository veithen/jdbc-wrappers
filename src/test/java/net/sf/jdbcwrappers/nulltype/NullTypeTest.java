package net.sf.jdbcwrappers.nulltype;

import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import net.sf.jdbcwrappers.nulltype.NullTypeWrapperFactory;

import org.apache.commons.io.FileUtils;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.derby.tools.ij;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class NullTypeTest {
    private static EmbeddedDataSource rawDataSource;
    private static DataSource dataSource;
    
    @BeforeClass
    public static void createDataSource() throws Exception {
        FileUtils.deleteDirectory(new File("target/testDB"));
        rawDataSource = new EmbeddedDataSource();
        rawDataSource.setDatabaseName("target/testDB");
        rawDataSource.setUser("test");
        rawDataSource.setCreateDatabase("create");
        Connection connection = rawDataSource.getConnection();
        try {
            if (ij.runScript(connection, NullTypeTest.class.getResourceAsStream("schema.sql"), "UTF-8", System.out, "UTF-8") > 0) {
                fail("Failed to initialize database");
            }
        } finally {
            connection.close();
        }
        dataSource = new NullTypeWrapperFactory().wrapDataSource(rawDataSource);
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
    
    @Test(expected=SQLException.class)
    public void crossCheckDerbyBehaviour() throws SQLException {
        Connection rawConnection = rawDataSource.getConnection();
        try {
            PreparedStatement statement = rawConnection.prepareStatement("UPDATE TEST SET CHAR_COL=?");
            try {
                statement.setNull(1, Types.NULL);
                statement.executeUpdate();
            }
            finally {
                statement.close();
            }
        }
        finally {
            rawConnection.close();
        }
    }
    
    @Test
    public void testSetNull() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE TEST SET CHAR_COL=?");
            try {
                statement.setNull(1, Types.NULL);
                statement.executeUpdate();
            }
            finally {
                statement.close();
            }
        }
        finally {
            connection.close();
        }
    }
}
