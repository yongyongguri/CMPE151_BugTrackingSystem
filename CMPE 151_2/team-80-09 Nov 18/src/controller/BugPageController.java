package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import application.CommonObjs;
import application.DataBaseConnection;
import application.DataModel;
import application.NewBugData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This class provides the controls for the bug page of the application.
 */
public class BugPageController implements Initializable {

	@FXML private Label isDatabaseConnected;
	@FXML private ComboBox<String> nameList;
	@FXML private TextField addBugTitle;
	@FXML private TextArea addBugDescription;
	@FXML private TableView<NewBugData> Bug_tableView;
	@FXML private TableColumn<NewBugData, String> add_projectName_column;
	@FXML private TableColumn<NewBugData, String> add_BugTitle_Column; 
	@FXML private TableColumn<NewBugData, String> add_BugDescription_Column;
	@FXML private TableColumn<NewBugData, String> add_BugComment_Column;
	
	public DataModel dataModel = new DataModel();
	
	/**
	 * Initialize to display the stored bugs using UpdateTable_Bug() and 
	 * populate the drop down list with projects in alphabetical order using projectNameList().
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (dataModel.isDatabaseConnected()) {
			isDatabaseConnected.setText(" ");
			UpdateTable_Bug(); // Bug table
		} else {
			isDatabaseConnected.setText(" ");
		}
		nameList.setItems(FXCollections.observableArrayList(projectNameList()));
	}

	private CommonObjs commonObjs = CommonObjs.getInstance();
	
	/**
	 * Shows NewCommentPage.fxml when user clicks on the "Add Comments" button.
	 */
	public void addComments_Button() {

		URL url = getClass().getClassLoader().getResource("view/NewCommentPage.fxml");
		try {
			AnchorPane pane3 = (AnchorPane) FXMLLoader.load(url);

			VBox mainBox = commonObjs.getMainBox();

			if (mainBox.getChildren().size() > 2)
				mainBox.getChildren().remove(2);

			mainBox.getChildren().add(pane3);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	ObservableList<NewBugData> listB;

	Connection conn = null;
	ResultSet resultB = null;
	PreparedStatement pst_Bug = null;
	
	/**
	 * Adds a New Bug with project name, bug title, and bug description. 
	 * It then updates table view using UpdateTable().
	 */
	public void addBug_saveButton() {
		conn = DataBaseConnection.Connector();
		int index = nameList.getSelectionModel().getSelectedItem().toString().indexOf("|");
		String sql = "INSERT INTO Ticket_table (ProjectName,BugTitle,BugDescription,ProjectID)" + " VALUES(?,?,?,?)";
		try {
			pst_Bug = conn.prepareStatement(sql);
			pst_Bug.setString(1, nameList.getSelectionModel().getSelectedItem().toString().substring(0, index-1));
			pst_Bug.setString(2, addBugTitle.getText());
			pst_Bug.setString(3, addBugDescription.getText());
			pst_Bug.setString(4, nameList.getSelectionModel().getSelectedItem().toString().substring(index+1));
			pst_Bug.execute();
			pst_Bug.close();

			UpdateTable_Bug();
		} catch (Exception e) {
			
		}
	}

	/**
	 * Update the table to display the stored Bug.
	 */
	public void UpdateTable_Bug() {
		add_projectName_column.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("newProjectName"));
		add_BugTitle_Column.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugTitle"));
		add_BugDescription_Column.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugDescription"));

		listB = DataBaseConnection.getDataBug();
		Bug_tableView.setItems(listB);
	}
	
	/**
	 * Adds all the available projects into a list and sorts them by alphabetical order.
	 * 
	 * @return ObservableList<String> list of projects.
	 */
	public ObservableList<String> projectNameList() {
		Connection conn1 = DataBaseConnection.Connector();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn1.prepareStatement("SELECT * FROM Project_table");
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				list.add(result.getString("ProjectName") + " | " + result.getString("Project_ID"));
			}	
		} catch (Exception e) {
		}
		FXCollections.sort(list);
		return list;
	}
	
}
