package application;

public enum Action {

	ADD,
	DELETE,
	EDIT;
	
	public String toString() {
		if (this == ADD) {
			return "Add";
		} else if (this == DELETE) {
			return "Delete";
		} else if (this == EDIT) {
			return "Edit";
		} 
		
		return null;
	}
	
}
