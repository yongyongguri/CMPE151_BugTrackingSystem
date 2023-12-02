package application;

public class NewBugData {
	String bugID;
	String newProjectName;
	String bugTitle;
	String bugDescription;

	// Getters for associate with the bug.
	public String getBugID() {
		return bugID;
	}
	
	public void setBugID(String bugID) {
		this.bugID = bugID;
	}
	
	public String getNewProjectName() {
		return newProjectName;
	}

	public void setNewProjectName(String newProjectName) {
		this.newProjectName = newProjectName;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	// Setters for associated with the bug.
	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public String getBugDescription() {
		return bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}

	// Constructs a NewBugData objects with the project name, bug title, and bug description
	public NewBugData(String bugID, String newProjectName, String bugTitle, String bugDescription) {
		super();
		this.bugID = bugID;
		this.newProjectName = newProjectName;
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
	}

}
