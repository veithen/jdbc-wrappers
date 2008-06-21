package net.sf.jwrappers.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface HasConnection {
	// TODO: actually, none of the implementing methods ever throws a SQLException
	Connection getConnection() throws SQLException;
}
