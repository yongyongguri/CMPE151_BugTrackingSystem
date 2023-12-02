package application;

import java.sql.*;

import org.sqlite.SQLiteConfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To establishing a connection to SQLite database
 * It utilizes the JDBC API to connect database 
 */
public class DataBaseConnection {

	// To call the Connector method for database connection.
	public static void main(String[] args) {
		Connector();
	}

	/*
	 * Building a connection to a SQLite database. A connection object shows the
	 * status of database connection Or null if it is error.
	 * Configuration of foreign key added
	 * -> BEFORE
	 *		Connection conn = DriverManager.getConnection("jdbc:sqlite:TrackBugDB.db");
	 * -> NOW(Nov18,2023)
	 * 		SQLiteConfig config = new SQLiteConfig();
	 * 		config.enforceForeignKeys(true);
	 * 		Connection conn = DriverManager.getConnection("jdbc:sqlite:TrackBugDB.db",config.toProperties());
	 */
	public static Connection Connector() {
		try {
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			Connection conn = DriverManager.getConnection("jdbc:sqlite:TrackBugDB.db",config.toProperties());
			return conn;

		} catch (SQLException e) {
			return null;
		}
	}

	// Get stored data from the Project_table
	public static ObservableList<NewProjectData> getDataProject() {
		Connection conn1 = DataBaseConnection.Connector();
		ObservableList<NewProjectData> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn1.prepareStatement("SELECT * FROM Project_table " + "ORDER BY Project_ID DESC");
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				list.add(new NewProjectData(result.getString("ProjectName"), result.getString("StartDate"),
						result.getString("Prjt_Description"), result.getString("Prjt_Level"), result.getString("Project_ID")));
			}

		} catch (Exception e) {
		}
		return list;
	}

	// Get stored data from the Bug_table
	public static ObservableList<NewBugData> getDataBug() {
		Connection conn1 = DataBaseConnection.Connector();
		ObservableList<NewBugData> list = FXCollections.observableArrayList();
		try {
			PreparedStatement psBug = conn1.prepareStatement("SELECT * FROM Ticket_table " + "ORDER BY BugID DESC");
			ResultSet result = psBug.executeQuery();

			while (result.next()) {
				list.add(new NewBugData(result.getString("BugID"), result.getString("ProjectName"), result.getString("BugTitle"),
						result.getString("BugDescription")));
			}

		} catch (Exception e) {
		}
		return list;
	}

	// Get stored data from the Comment_table
	public static ObservableList<NewCommentData> getDataComment() {
		Connection conn1 = DataBaseConnection.Connector();
		ObservableList<NewCommentData> list = FXCollections.observableArrayList();
		try {
			//PreparedStatement psComment = conn1
			//		.prepareStatement("SELECT * FROM Comment_table " + "INNER JOIN Ticket_table ORDER BY BugID DESC LIMIT 1 ");
			PreparedStatement psComment = conn1
					.prepareStatement("SELECT * FROM Comment_table " + "ORDER BY Comment_ID DESC");
			ResultSet result = psComment.executeQuery();

			while (result.next()) {
				list.add(new NewCommentData(result.getString("Comment_ID"), result.getString("Comment_Description"),
						result.getString("TimeStamp")));
			}	

		} catch (Exception e) {
		}
		return list;
	}

	// Get stored data from the Bug_table and display the latest value on the top
	public static ObservableList<NewBugData> getDataBug_lastEntry() {
		Connection conn1 = DataBaseConnection.Connector();
		ObservableList<NewBugData> list = FXCollections.observableArrayList();
		try {
			PreparedStatement psBug = conn1
					.prepareStatement("SELECT * FROM Ticket_table " + "ORDER BY BugID DESC LIMIT 1");
			ResultSet result = psBug.executeQuery();

			while (result.next()) {
				list.add(new NewBugData(result.getString("BugID"), result.getString("ProjectName"), result.getString("BugTitle"),
						result.getString("BugDescription")));
			}

		} catch (Exception e) {
		}
		return list;
	}

}