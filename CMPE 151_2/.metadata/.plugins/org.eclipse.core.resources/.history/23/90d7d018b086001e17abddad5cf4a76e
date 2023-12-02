package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import application.DataBaseConnection;
import application.DataModel;
import application.NewBugData;
import application.NewCommentData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class provides the controls for the comment page of the application.
 */
public class CommentsController implements Initializable {

	@FXML private Label isDatabaseConnected;
	@FXML private TextArea addNewComment;
	@FXML private TableView<NewCommentData> Comment_tableView;
	@FXML private TableColumn<NewCommentData, String> add_CommentNumber_column;
	@FXML private TableColumn<NewCommentData, String> add_CommentDescription_Column;
	@FXML private TableColumn<NewCommentData, String> add_CommentTime_Column;
	//@FXML private ComboBox<String> titleList;
	@FXML private TableView<NewBugData> Bug_tableView1;
	@FXML private TableColumn<NewBugData, String> add_projectName_column1;
	@FXML private TableColumn<NewBugData, String> add_BugTitle_Column1;
	@FXML private TableColumn<NewBugData, String> add_BugDescription_Column1;

	public DataModel dataModel = new DataModel();
	
	/**
	 * Initialize to display the stored projects and bugs using UpdateTable_Bug()
	 * and the stored comments using UpdateTable_Comment().
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (dataModel.isDatabaseConnected()) {
			isDatabaseConnected.setText(" ");
			UpdateTable_Comment();
			UpdateTable_Bug();
			//titleList.setItems(FXCollections.observableArrayList(BugTitleList()));
		} else {
			isDatabaseConnected.setText(" ");
		}
	}

	ObservableList<NewBugData> listB_LastValue;
	Connection conn = null;
	PreparedStatement pst_Bug = null;

	private ObservableList<NewCommentData> listC;
	Connection connC = null;
	PreparedStatement pst_Comment = null;

	/**
	 *  Adds new Comment with its description and current date/time.
	 *  It then updates table view using UpdateTable_Comment(). 
	 */
	public void addComment_saveButton() {
		conn = DataBaseConnection.Connector();
		String sql = "INSERT INTO Comment_table (Comment_Description, TimeStamp)" + " VALUES(?,?)";
		try {
			pst_Comment = conn.prepareStatement(sql);
			pst_Comment.setString(1, addNewComment.getText());
			pst_Comment.setString(2, LocalDate.now().toString() + " - "
					+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());
			pst_Comment.execute();
			pst_Comment.close();

			UpdateTable_Comment();
		} catch (Exception e) {
		}
	}

	/**
	 * Updates the table to displays the stored Comments.
	 */
	public void UpdateTable_Comment() {
		add_CommentNumber_column.setCellValueFactory(new PropertyValueFactory<NewCommentData, String>("comments_ID"));
		add_CommentDescription_Column
				.setCellValueFactory(new PropertyValueFactory<NewCommentData, String>("commentDescription"));
		add_CommentTime_Column.setCellValueFactory(new PropertyValueFactory<NewCommentData, String>("commentDateTime"));

		listC = DataBaseConnection.getDataComment();
		Comment_tableView.setItems(listC);
	}

	/*public ObservableList<String> BugTitleList() {
		Connection conn1 = DataBaseConnection.Connector();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn1.prepareStatement("SELECT * FROM Ticket_table");
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				list.add(result.getString("BugTitle") + " | " + result.getString("BugID"));
			}	
		} catch (Exception e) {
		}
		FXCollections.sort(list);
		return list;
	}*/
	
	/**
	 * Updates the table to display the stored Bug - the latest value
	 */
	public void UpdateTable_Bug() {
		add_projectName_column1.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("newProjectName"));
		add_BugTitle_Column1.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugTitle"));
		add_BugDescription_Column1.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugDescription"));

		listB_LastValue = DataBaseConnection.getDataBug_lastEntry();
		Bug_tableView1.setItems(listB_LastValue);
	}
}
