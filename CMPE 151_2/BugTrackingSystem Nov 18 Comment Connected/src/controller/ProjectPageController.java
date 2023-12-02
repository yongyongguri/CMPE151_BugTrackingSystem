package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import application.DataModel;
import application.NewProjectData;
import application.DataBaseConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * This class provides the controls for the add project page of the application.
 */
public class ProjectPageController implements Initializable {
	public DataModel dataModel = new DataModel();

	@FXML
	private Label isDatabaseConnected;

	/**
	 * Initialize to display the stored projects using UpdateTable() and the current
	 * date in the DatePicker using showDate().
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (dataModel.isDatabaseConnected()) {
			isDatabaseConnected.setText("Database is Connected");
			UpdateTable();
		} else {
			isDatabaseConnected.setText("Database isn't connected!");
		}
		showDate();
	}

	@FXML
	private AnchorPane createNewProject_page;

	@FXML
	private TextField addProjectName;

	@FXML
	private DatePicker addProjectDate;

	@FXML
	private TextArea addProjectDescription;

	@FXML
	private TextField addProjectLevel;

	@FXML
	private Button addProject_saveButton;

	@FXML
	private Button addProject_cancelButton;

	@FXML
	private TableView<NewProjectData> project_tableView;

	@FXML
	private TableColumn<NewProjectData, String> add_projectName_column;

	@FXML
	private TableColumn<NewProjectData, String> add_ProjectDate_Column;

	@FXML
	private TableColumn<NewProjectData, String> add_ProjectDescription_Column;

	@FXML
	private TableColumn<NewProjectData, String> add_ProjectLevel_Column;
	
    @FXML
    private TableColumn<NewProjectData, String> ProjectID_TableColumn;

	@FXML
	private Button addProject_clearButton;

	ObservableList<NewProjectData> listP;

	Connection conn = null;
	ResultSet result = null;
	PreparedStatement pst = null;

	/**
	 * Add New Project with name, staring date, description, level then update table
	 * view.
	 */
	public void addProject_saveButton() {
		conn = DataBaseConnection.Connector();
		String sql = "INSERT INTO Project_table (ProjectName,StartDate,Prjt_Description,Prjt_Level)"
				+ " VALUES(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, addProjectName.getText());
			pst.setString(2, addProjectDate.getValue().toString());
			pst.setString(3, addProjectDescription.getText());
			pst.setString(4, addProjectLevel.getText());
			pst.execute();
			pst.close();

			UpdateTable();
		} catch (Exception e) {	
			
		}
	}

	/**
	 * Update the table to display the stored project.
	 */
	public void UpdateTable() {
		add_projectName_column.setCellValueFactory(new PropertyValueFactory<NewProjectData, String>("newProjectName"));
		add_ProjectDate_Column.setCellValueFactory(new PropertyValueFactory<NewProjectData, String>("projectDate"));
		add_ProjectDescription_Column
				.setCellValueFactory(new PropertyValueFactory<NewProjectData, String>("projectDescription"));
		add_ProjectLevel_Column.setCellValueFactory(new PropertyValueFactory<NewProjectData, String>("projectLevel"));
		ProjectID_TableColumn.setCellValueFactory(new PropertyValueFactory<NewProjectData, String>("projectID"));
		
		listP = DataBaseConnection.getDataProject();
		project_tableView.setItems(listP);
	}

	/**
	 * Populate the date-picker field with the current date.
	 * 
	 * @return the current date inside the addProjectDate DatePicker.
	 */
	public DatePicker showDate() {
		addProjectDate.setValue(LocalDate.now());
		return addProjectDate;
	}

}
