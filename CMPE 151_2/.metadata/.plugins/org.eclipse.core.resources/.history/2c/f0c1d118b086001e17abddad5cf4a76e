package application;

import javafx.scene.layout.VBox;

// Collecting all references that sharing among controllers
public class CommonObjs {
	private static CommonObjs commonObjs = new CommonObjs();
	
	private VBox mainBox;
	
	private CommonObjs() {}
		
	public static CommonObjs getInstance() {
		return commonObjs; 	
	}

	public VBox getMainBox() {
		return mainBox;
	}

	public void setMainBox(VBox mainBox) {
		this.mainBox = mainBox;
	}
	

}
