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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class BugPageController implements Initializable {

	public DataModel dataModel = new DataModel();

	@FXML private ComboBox<String> nameList;

	/**
	 * Initialize to display the stored projects using _Bug()
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		UpdateTable_Bug(); // Bug table	
		nameList.setItems(FXCollections.observableArrayList(projectNameList()));
	}

	private CommonObjs commonObjs = CommonObjs.getInstance();

	
	// Click the "Add Comments" and navigate to NewCommentPage
	@FXML public void addComments_Button() {

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
	
	//@FXML private TextField addProjectName2;

	@FXML
	private TextField addBugTitle;

	@FXML
	private TextArea addBugDescription;

	@FXML
	private TableView<NewBugData> Bug_tableView;

	@FXML
	private TableColumn<NewBugData, String> add_BugID_column;
	
	@FXML
	private TableColumn<NewBugData, String> add_projectName_column;

	@FXML
	private TableColumn<NewBugData, String> add_BugTitle_Column;

	@FXML
	private TableColumn<NewBugData, String> add_BugDescription_Column;

	@FXML
	private TableColumn<NewBugData, String> add_BugComment_Column;

	ObservableList<NewBugData> listB;

	Connection conn = null;
	ResultSet resultB = null;
	PreparedStatement pst_Bug = null;

	@FXML
	public void addBug_saveButton() {
		conn = DataBaseConnection.Connector();
		int index = nameList.getSelectionModel().getSelectedItem().toString().indexOf("\t");
		String sql = "INSERT INTO Ticket_table (ProjectName,BugTitle,BugDescription,ProjectID)" + " VALUES(?,?,?,?)";
		try {
			pst_Bug = conn.prepareStatement(sql);
			pst_Bug.setString(1, nameList.getSelectionModel().getSelectedItem().toString().substring(index+1));
			pst_Bug.setString(2, addBugTitle.getText());
			pst_Bug.setString(3, addBugDescription.getText());
			pst_Bug.setString(4, nameList.getSelectionModel().getSelectedItem().toString().substring(0,index));
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
		add_BugID_column.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugID"));
		add_projectName_column.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("newProjectName"));
		add_BugTitle_Column.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugTitle"));
		add_BugDescription_Column.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugDescription"));
		// add_BugComment_Column.setCellValueFactory(new
		// PropertyValueFactory<NewBugData, String>("CommentDescription"));

		listB = DataBaseConnection.getDataBug();
		Bug_tableView.setItems(listB);
	}
	
	public ObservableList<String> projectNameList() {
		Connection conn1 = DataBaseConnection.Connector();
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn1.prepareStatement("SELECT * FROM Project_table");
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				list.add(result.getString("Project_ID")+"\t"+result.getString("ProjectName"));
			}
			FXCollections.sort(list);

		} catch (Exception e) {
		}
		return list;
	}
	
}
