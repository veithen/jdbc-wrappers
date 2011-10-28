package net.sf.jdbcwrappers.nulltype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import net.sf.jdbcwrappers.nulltype.NullTypeWrapperFactory;
import net.sf.springderby.DeleteDatabaseAction;
import net.sf.springderby.EmbeddedDataSourceFactory;
import net.sf.springderby.ExecuteSqlScriptsAction;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class NullTypeTest {
	private static EmbeddedDataSourceFactory factory;
	private static EmbeddedDataSource rawDataSource;
	private static DataSource dataSource;
	
	@BeforeClass
	public static void createDataSource() throws Exception {
		factory = new EmbeddedDataSourceFactory();
		factory.setDatabaseName("target/testDB");
		factory.setUser("test");
		factory.setCreate(true);
		factory.setBeforeStartupAction(new DeleteDatabaseAction());
		ExecuteSqlScriptsAction afterCreationAction = new ExecuteSqlScriptsAction();
		afterCreationAction.setScript(new ClassPathResource("/net/sf/jwrappers/jdbc/nulltype/schema.sql"));
		factory.setAfterCreationAction(afterCreationAction);
		factory.setAfterShutdownAction(new DeleteDatabaseAction());
		factory.afterPropertiesSet();
		rawDataSource = (EmbeddedDataSource)factory.getObject();
		dataSource = new NullTypeWrapperFactory().wrapDataSource(rawDataSource);
	}
	
	@AfterClass
	public static void destroyDataSource() throws Exception {
		factory.destroy();
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
