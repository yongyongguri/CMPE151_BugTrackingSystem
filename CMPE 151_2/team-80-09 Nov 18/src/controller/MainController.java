package controller;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainController {
	@FXML
	VBox mainBox;

	/**
	 * Shows NewProjectPage.fxml when user clicks on "Add New Project" button.
	 */
	@FXML
	public void showNewProjectPageOP() {
		URL url = getClass().getClassLoader().getResource("view/NewProjectPage.fxml");
		try {
			AnchorPane pane1 = (AnchorPane) FXMLLoader.load(url);
			if (mainBox.getChildren().size() > 2) {
				mainBox.getChildren().remove(2);
			}
			mainBox.getChildren().add(pane1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Shows NewBugPage.fxml when user clicks on "Add New Bug" button.
	@FXML
	public void showNewBugPageOp() {
		URL url = getClass().getClassLoader().getResource("view/NewBugPage.fxml");
		try {
			AnchorPane pane2 = (AnchorPane) FXMLLoader.load(url);
			if (mainBox.getChildren().size() > 2) {
				mainBox.getChildren().remove(2);
			}
			mainBox.getChildren().add(pane2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Show SearchProjectPage.fxml when user clicks on "SearchProject" Button
	@FXML
	public void showSearchProjectPageOp() {
		URL url = getClass().getClassLoader().getResource("view/SearchProjectPage.fxml");
		try {
			AnchorPane pane6 = (AnchorPane) FXMLLoader.load(url);
			if (mainBox.getChildren().size() > 2) {
				mainBox.getChildren().remove(2);
			}
			mainBox.getChildren().add(pane6);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Show SearchBugPage.fxml when user clicks on "SearchBug" Button
		@FXML
		public void showSearchBugPageOp() {
			URL url = getClass().getClassLoader().getResource("view/SearchBugPage.fxml");
			try {
				AnchorPane pane7 = (AnchorPane) FXMLLoader.load(url);
				if (mainBox.getChildren().size() > 2) {
					mainBox.getChildren().remove(2);
				}
				mainBox.getChildren().add(pane7);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
