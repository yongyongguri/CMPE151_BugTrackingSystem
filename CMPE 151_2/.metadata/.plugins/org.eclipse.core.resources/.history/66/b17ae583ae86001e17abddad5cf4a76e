package application;

import java.sql.*;
import java.sql.SQLException;

/**
 * The DataModel class manages/checks the status of database connection. It
 * determines whether the connection is opened or not
 */

public class DataModel {

	Connection connection;

	// A constructor of DataModel object to attempt for connection to database
	// It will print error message when it's not connected.
	public DataModel() {
		connection = DataBaseConnection.Connector();
		if (connection == null)
			System.out.println("No DataModel");
	}

	// Checks if the database connection is open or not
	public boolean isDatabaseConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
