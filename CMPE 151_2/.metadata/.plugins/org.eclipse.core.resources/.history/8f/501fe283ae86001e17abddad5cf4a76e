
package controller;

import java.net.URL;
import java.sql.Connection;

import java.util.ResourceBundle;
import application.DataBaseConnection;
import application.DataModel;
import application.NewBugData;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class SearchBugPageController implements Initializable {
	
	@FXML private TableView<NewBugData> result_bug_tableView;
	@FXML private TableColumn<NewBugData, String> BugName_tableColumn1;
	@FXML private TableColumn<NewBugData, String> BugDescription_TableColumn1;
	@FXML private TableColumn<NewBugData, String> ProjectDescription_TableColumn1;
	@FXML private TextField search_bug_input_column;
	@FXML private TextField edit_projectName;
    @FXML private TextField edit_Date;
    @FXML private TextField edit_projectDescription;
	@FXML private TextField edit_projectLevel;
    @FXML private TextField edit_BugName;
    @FXML private TextField edit_BugDescription;

	public DataModel dataModel = new DataModel();

	Connection conn = null;
	//PreparedStatement pst_Edit = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		UpdateTable_Bug();
		search_Bug();
	}

	ObservableList<NewBugData> listB_search;

	/**
	 * Display the existing data of Bug
	 */
	public void UpdateTable_Bug() {
		BugName_tableColumn1.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugTitle"));
		BugDescription_TableColumn1.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("bugDescription"));
		ProjectDescription_TableColumn1
				.setCellValueFactory(new PropertyValueFactory<NewBugData, String>("newProjectName"));

		listB_search = DataBaseConnection.getDataBug();
		result_bug_tableView.setItems(listB_search);
		
	}

	/**
	 * Filtering the name of project or Bug 
	 * Display all existing data while there is no input from user 
	 * If there is a match with project's name or Bug's name, returns true when it's matched 
	 * Or returns false when there is no match
	 */
	public void search_Bug() {

		FilteredList<NewBugData> filteredBugData = new FilteredList<>(listB_search, b -> true);

		search_bug_input_column.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredBugData.setPredicate(bugSearchModel -> {

				if (newValue.isEmpty() || newValue == null) {
					return true;
				}

				String nameSearch = newValue.toLowerCase();

				if (bugSearchModel.getBugTitle().toLowerCase().indexOf(nameSearch) > -1
						|| bugSearchModel.getNewProjectName().toLowerCase().indexOf(nameSearch) > -1) {
					return true;
				} else {
					return false;
				}
			});
		});

		// Create an instance of a SortedList and initialize with data of NewBugData objects
		SortedList<NewBugData> sortedBugName = new SortedList<>(filteredBugData);

		// Sorting out the tableView by Bug's name
		sortedBugName.comparatorProperty().bind(result_bug_tableView.comparatorProperty());

		result_bug_tableView.setItems(sortedBugName);
	}
	
}
