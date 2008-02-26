package net.sf.jdbcwrappers.trim;

import static org.junit.Assert.assertEquals;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import net.sf.jdbcwrappers.DataSourceWrapper;
import net.sf.springderby.DeleteDatabaseAction;
import net.sf.springderby.EmbeddedDataSourceFactory;
import net.sf.springderby.ExecuteSqlScriptsAction;
import net.sf.springderby.OnlineAction;
import net.sf.springderby.proc.DeclareProceduresAction;
import net.sf.springderby.proc.annotation.DataAccessLevel;
import net.sf.springderby.proc.annotation.Procedure;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class TrimmingTest {
	private static EmbeddedDataSourceFactory factory;
	private static DataSource dataSource;
	
	private Connection connection;
	
	@BeforeClass
	public static void createDataSource() throws Exception {
		factory = new EmbeddedDataSourceFactory();
		factory.setDatabaseName("target/testDB");
		factory.setUser("test");
		factory.setCreate(true);
		factory.setBeforeStartupAction(new DeleteDatabaseAction());
		List<OnlineAction> afterCreationActions = new LinkedList<OnlineAction>();
		{
			ExecuteSqlScriptsAction action = new ExecuteSqlScriptsAction();
			action.setScript(new ClassPathResource("/net/sf/jdbcwrappers/trim/trimmingTest.sql"));
			afterCreationActions.add(action);
		}
		{
			DeclareProceduresAction action = new DeclareProceduresAction();
			action.setClassName(TrimmingTest.class.getName());
			afterCreationActions.add(action);
		}
		factory.setAfterCreationActions(afterCreationActions);
		factory.setAfterShutdownAction(new DeleteDatabaseAction());
		factory.afterPropertiesSet();
		dataSource = new DataSourceWrapper(new TrimmingWrapperFactory(), (DataSource)factory.getObject());
	}
	
	@AfterClass
	public static void destroyDataSource() throws Exception {
		factory.destroy();
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
	
	@Procedure(name="TESTPROC", dataAccessLevel=DataAccessLevel.READS_SQL_DATA)
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
