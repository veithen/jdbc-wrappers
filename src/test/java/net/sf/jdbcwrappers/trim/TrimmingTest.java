package net.sf.jdbcwrappers.trim;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import net.sf.jdbcwrappers.DataSourceWrapper;
import net.sf.springderby.DeleteDatabaseAction;
import net.sf.springderby.EmbeddedDataSourceFactory;
import net.sf.springderby.ExecuteSqlScriptsAction;

import org.springframework.core.io.ClassPathResource;

import junit.framework.TestCase;

public class TrimmingTest extends TestCase {
	public void test() throws Exception {
		EmbeddedDataSourceFactory factory = new EmbeddedDataSourceFactory();
		factory.setDatabaseName("target/testDB");
		factory.setUser("test");
		factory.setCreate(true);
		factory.setBeforeStartupAction(new DeleteDatabaseAction());
		ExecuteSqlScriptsAction action = new ExecuteSqlScriptsAction();
		action.setScript(new ClassPathResource("/net/sf/jdbcwrappers/trim/trimmingTest.sql"));
		factory.setAfterCreationAction(action);
		factory.setAfterShutdownAction(new DeleteDatabaseAction());
		factory.afterPropertiesSet();
		try {
			DataSource dataSource = new DataSourceWrapper(new TrimmingWrapperFactory(), (DataSource)factory.getObject());
			Connection connection = dataSource.getConnection();
			try {
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
			finally {
				connection.close();
			}
		}
		finally {
			factory.destroy();
		}
	}
}
